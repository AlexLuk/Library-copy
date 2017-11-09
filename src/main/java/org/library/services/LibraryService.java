package org.library.services;

import com.google.gson.Gson;
import org.library.db.domain.*;
import org.library.db.repo.*;
import org.library.misc.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    List<Book> getByTitleContainingAndYearAndGenreInAndIdIn(String title, Integer year,
                                                            List<Genre> genres, List<Integer> ids) {
        if (year == null) {
            return getByTitleContainingAndGenreInAndIdIn(title, genres, ids);
        }
        return bookRepo.findByTitleContainingIgnoreCaseAndYearAndGenreInAndIdIn(title, year, genres, ids);
    }

    List<Book> getByTitleContainingAndGenreInAndIdIn(String title, List<Genre> genres, List<Integer> ids) {
        return bookRepo.findByTitleContainingIgnoreCaseAndGenreInAndIdIn(title, genres, ids);
    }


    List<Genre> getGenresByName(String genreName) {
        return genreRepo.findByNameContaining(genreName);
    }

    List<Integer> getAuthorIdsByLastName(String authorName) {
        LinkedList<Integer> resultAuthorIds = new LinkedList<>();
        authorRepo.findByLastNameContainingIgnoreCase(authorName).forEach(author -> resultAuthorIds.add(author.getId()));
        return resultAuthorIds;
    }

    List<Integer> getBooksIdsByAuthorName(String authorName) {
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

    public String jsonBooks(List<Book> books) {
        List<BookJson> bookJsons = new LinkedList<>();
        books.forEach(book -> bookJsons.add(new BookJson(book)));
        Gson gson = new Gson();
        return gson.toJson(bookJsons);
    }

    public boolean deleteAccount() {
        Reader reader = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        readerRepo.delete(reader);
        return readerRepo.findOne(reader.getId()) == null;
    }

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
    List<Author> getAllAuthors(int bookId) {
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

        for (Delivery delivery : list) {
            res.add(Utils.convertLocalDate(delivery.getTime()));
        }
        return res;
    }
}
