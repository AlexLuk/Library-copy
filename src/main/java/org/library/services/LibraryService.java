package org.library.services;

import org.library.db.domain.*;
import org.library.db.repo.*;
import org.library.misc.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class LibraryService {
    @Autowired
    BookRepository bookRepo;

    @Autowired
    AuthorRepository authorRepo;

    @Autowired
    AuthorBookRepository authorBookRepo;

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

    public List<Book> getByTitleContainingAndYearAndGenreInAndIdIn(String title, Integer year,
                                                                   List<Genre> genres, List<Integer> ids) {
        if (year == null) {
            return getByTitleContainingAndGenreInAndIdIn(title, genres, ids);
        }
        return bookRepo.findByTitleContainingAndYearAndGenreInAndIdIn(title, year, genres, ids);
    }

    public List<Book> getByTitleContainingAndGenreInAndIdIn(String title, List<Genre> genres, List<Integer> ids) {
        return bookRepo.findByTitleContainingAndGenreInAndIdIn(title, genres, ids);
    }

    public List<Genre> getGenresByName(String genreName) {
        return genreRepo.findByNameContaining(genreName);
    }

    public List<Integer> getAuthorIdsByLastName(String authorName) {
        LinkedList<Integer> resultAuthorIds = new LinkedList<>();
        authorRepo.findByLastNameContaining(authorName).forEach(author -> resultAuthorIds.add(author.getId()));
        return resultAuthorIds;
    }

    public List<Integer> getBooksIdsByAuthorName(String authorName) {
        LinkedList<Integer> resultBookIds = new LinkedList<>();
        authorBookRepo.findAllByAuthorIdIn(getAuthorIdsByLastName(authorName)).forEach(authorBook -> resultBookIds.add(authorBook.getBook().getId()));
        return resultBookIds;
    }

    public List<Book> getBooksByComplexCondition(String title, String author, Integer year, String genre) {
        List<Genre> genres = getGenresByName(genre);
        List<Integer> bookIds = getBooksIdsByAuthorName(author);
        return getByTitleContainingAndYearAndGenreInAndIdIn(title, year, genres, bookIds);
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
     * Gets all book's authors from DB
     *
     * @param bookId - book id
     * @return - list of authors
     */
    public List<Author> getAllAuthors(int bookId) {
        List<Author> authors = new ArrayList<>();
        List<AuthorBook> authorBook = authorBookRepo.findAllByBookId(bookId);

        authorBook.forEach(obj ->
                authors.add(authorRepo.findOne(obj.getAuthor().getId()))
        );
        return authors;
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

        for(Delivery delivery : list) {
            res.add(Utils.convertLocalDate(delivery.getTime()));
        }
        return res;
    }
}
