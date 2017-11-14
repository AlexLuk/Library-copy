package org.library.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.library.db.domain.*;
import org.library.db.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {

    private final static Logger logger = LoggerFactory.getLogger(LibraryService.class);

    @Autowired
    BookRepository bookRepo;

    @Autowired
    AuthorRepository authorRepo;

    @Autowired
    GenreRepository genreRepo;

    @Autowired
    ReaderRepository readerRepo;

    @Autowired
    BookOrderRepository bookOrderRepo;

    @Autowired
    DeliveryRepository deliveryRepo;

    @Autowired
    ItemStatusRepository itemStatusRepository;

    //todo add user filter

    /**
     * Search for books by list of params
     *
     * @param title  - part of book title
     * @param author - part of author last name
     * @param year   - year of book publishing
     * @param genre  - genre id of the book
     * @return
     */
    public List<Book> getBooksByComplexCondition(String title, String author, Integer year, Integer genre) {
        List<String> authorCredentials = parseAuthorString(author);
        return bookRepo.findByComplexQuery(title, year, genre,
                authorCredentials.get(0), authorCredentials.get(1), authorCredentials.get(2));
    }

    /**
     * Gets all books from DB
     *
     * @return - list of books
     */
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    /**
     * Wrap list of books in form of json
     *
     * @param books - books to be wrapped
     * @return - json string
     */
    public String jsonBooks(List<Book> books) {
        String booksJson = "";
        try {
            booksJson = new ObjectMapper().writeValueAsString(books);
        } catch (JsonProcessingException e) {
            logger.error("json processing error", e);
        }
        return booksJson;
    }

    /**
     * Gets all book's authors from DB
     *
     * @param bookId - book id
     * @return - list of authors
     */
    public List<Author> getAllAuthors(int bookId) {
        Book book = bookRepo.getOne(bookId);
        return book.getAuthors();
    }

    /**
     * Gets genre by its id
     *
     * @param genreId - genre id
     * @return - genre name
     */

    public String getGenre(int genreId) {
        return genreRepo.findOne(genreId).getName();
    }

    /**
     * Gets all genres
     *
     * @return - list of genres
     */
    public List<Genre> getAllGenres() {
        return genreRepo.findAll();
    }

    /**
     * Gets all readers
     *
     * @return - list of readers
     */
    public List<Reader> getAllReaders() {
        return readerRepo.findAll();
    }

    /**
     * Gets all authors
     *
     * @return - list of authors
     */
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    /**
     * Gets all orders
     *
     * @return - list of orders
     */
    public List<BookOrder> getAllOrders() {
        return bookOrderRepo.findAll();
    }

    /**
     * Gets all orders by reader id
     *
     * @param id - user id
     * @return - list of orders
     */
    public List<BookOrder> getAllOrdersByReader(int id) {
        return bookOrderRepo.findByReaderId(id);
    }

    /**
     * Gets all delivered items
     *
     * @return - list of items
     */
    public List<Delivery> getAllDeliveryItems() {
        return deliveryRepo.findAll();
    }

    /**
     * Gets all delivered items by reader id
     *
     * @param id - user id
     * @return - list of delivered items
     */
    public List<Delivery> getAllDeliveryItemsByReader(int id) {
        return deliveryRepo.findByReaderId(id);
    }

    /**
     * Parse author full name string to list of author credentials, split it by spaces and fill into credentials
     *
     * @param author - author full name string
     * @return list of author last name, first name, patronymic name
     */
    public List<String> parseAuthorString(String author) {
        List<String> authorCredentials = new ArrayList<>();
        String[] authorArray = author.split(" ");
        for (int i = 0; i < 3; i++) {
            if (authorArray.length > i) {
                authorCredentials.add(authorArray[i]);
            } else {
                authorCredentials.add("");
            }
        }
        return authorCredentials;
    }
}
