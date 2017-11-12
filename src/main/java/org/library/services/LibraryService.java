package org.library.services;

import com.google.gson.Gson;
import org.library.db.domain.*;
import org.library.db.repo.*;
import org.library.misc.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
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
        return bookRepo.findByComplexQuery(title, year, genre, author);
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
        List<BookJson> bookJsons = new LinkedList<>();
        books.forEach(book -> bookJsons.add(new BookJson(book)));
        Gson gson = new Gson();
        return gson.toJson(bookJsons);
    }

    /**
     * Delete current user from database
     *
     * @return true if delete successful
     */
    public boolean deleteAccount() {
        Reader reader = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return deleteReaderById(reader.getId());
    }

    /**
     * Delete reader from database by readerId
     *
     * @param readerId - id of reader to delete
     * @return true if delete successful
     */
    public boolean deleteReaderById(int readerId) {
        try {
            readerRepo.delete(readerId);
            return readerRepo.findOne(readerId) == null;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    /**
     * Wrapper class for book user interface
     */
    private class BookJson {
        String title;
        List<String> authors = new LinkedList<>();
        int year;
        String genre;
        int book_id;

        BookJson(Book book) {
            title = book.getTitle();
            year = book.getYear();
            book_id = book.getId();
            genre = book.getGenre().getName();
            getAllAuthors(book.getId()).forEach(author -> authors.add(author.getFullName()));
        }
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
     * Gets all dates
     *
     * @return - list of delivery dates
     */
    public List<LocalDate> getAllDates(List<Delivery> list) {
        List<LocalDate> res = new ArrayList<>();

        for (Delivery delivery : list) {
            res.add(Utils.convertLocalDate(delivery.getTime()));
        }
        return res;
    }
}
