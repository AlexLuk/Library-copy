package org.library.services;

import org.library.db.domain.Author;
import org.library.db.domain.AuthorBook;
import org.library.db.domain.Book;
import org.library.db.domain.Genre;
import org.library.db.repo.AuthorBookRepository;
import org.library.db.repo.AuthorRepository;
import org.library.db.repo.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FilteringService {

    private static final String REGEXP_MULTI_SYMBOLS = ".*?";

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    LibraryService libraryService;

    @Autowired
    AuthorBookRepository authorBookRepository;

    /**
     * Get list of books based on complex filtering condition
     *
     * @param title  - part of book title
     * @param author - part of author name
     * @param year   - books year
     * @param genre  - part of books genre name
     * @return - filtered list of books
     */
    public List<Book> getBooksByComplexCondition(String title, String author, String year, String genre) { //todo

        List<Book> resultBookList = libraryService.getAllBooks();
        resultBookList = filterBooksByTitle(title, resultBookList);
        resultBookList = filterBooksByAuthor(author, resultBookList);
        resultBookList = filterBooksByYear(year, resultBookList);
        resultBookList = filterBooksByGenre(genre, resultBookList);
        return resultBookList;
    }

    /**
     * Filter input list with part of title
     *
     * @param title         - part of title for search
     * @param inputBookList - input list of books
     * @return - filtered list of books
     */
    public List<Book> filterBooksByTitle(String title, List<Book> inputBookList) {
        if (title.length() == 0) return inputBookList;
        final String titleMatcher = REGEXP_MULTI_SYMBOLS + title.toLowerCase() + REGEXP_MULTI_SYMBOLS;
        inputBookList.removeIf(book -> !book.getTitle().toLowerCase().matches(titleMatcher));
        return inputBookList;
    }

    /**
     * Filter input book list with exact author
     *
     * @param authorName    - author filter
     * @param inputBookList - input list of books
     * @return - filtered book list
     */
    public List<Book> filterBooksByAuthor(String authorName, List<Book> inputBookList) {
        if (authorName.length() == 0) return inputBookList;
        List<Author> authors = getAuthorsByName(authorName);
        List<AuthorBook> authorBooks = new LinkedList<>();
        authors.forEach(author -> authorBooks.addAll(authorBookRepository.findAllByAuthorId(author.getId())));
        List<Book> resultBooks = new LinkedList<>();
        authorBooks.forEach(authorBook -> {
            if (inputBookList.contains(authorBook.getBook())) {
                resultBooks.add(authorBook.getBook());
            }
        });
        return resultBooks;
    }

    /**
     * Filter input book list with exact year
     *
     * @param year          - year filter
     * @param inputBookList - input list of books
     * @return - filtered book list
     */
    public List<Book> filterBooksByYear(String year, List<Book> inputBookList) {
        if (year.length() == 0) return inputBookList;
        try {
            int yearDigits;
            yearDigits = Integer.parseInt(year);
            int finalYearDigits = yearDigits;
            inputBookList.removeIf(book -> book.getYear() != finalYearDigits);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return inputBookList;
    }

    /**
     * Filter input book list with part of genre name
     *
     * @param genre         - part of genre name for filter
     * @param inputBookList - input book list
     * @return - filtered book list
     */
    public List<Book> filterBooksByGenre(String genre, List<Book> inputBookList) {
        if (genre.length() == 0) return inputBookList;
        List<Genre> genres = getGenresByName(genre);
        List<Book> resultBooks = new LinkedList<>();
        inputBookList.forEach(book -> {
            if (genres.contains(book.getGenre())) resultBooks.add(book);
        });
        return resultBooks;
    }

    /**
     * Get list of authors based on part of author name
     *
     * @param name - part of author name
     * @return - list of authors
     */
    public List<Author> getAuthorsByName(String name) {
        List<Author> authors = authorRepository.findAll();
        final String authorMatcher = REGEXP_MULTI_SYMBOLS + name.toLowerCase() + REGEXP_MULTI_SYMBOLS;
        authors.removeIf(author -> !author.getFullName().toLowerCase().matches(authorMatcher));
        return authors;
    }

    /**
     * Get list of genres based of part of genre name
     *
     * @param genreName - part of genre name
     * @return - list of genres
     */
    public List<Genre> getGenresByName(String genreName) {
        List<Genre> genres = genreRepository.findAll();
        final String genreMatcher = REGEXP_MULTI_SYMBOLS + genreName.toLowerCase() + REGEXP_MULTI_SYMBOLS;
        genres.removeIf(genre -> !genre.getName().toLowerCase().matches(genreMatcher));
        return genres;
    }
}
