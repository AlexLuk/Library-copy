package org.library.services;

import org.library.db.domain.*;
import org.library.db.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class LibraryService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorBookRepository authorBookRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    ReaderRepository readerRepository;

    public List<Book> getByTitleContainingAndYearAndGenreInAndIdIn(String title, Integer year,
                                                                   List<Genre> genres, List<Integer> ids) {
        if (year == null) {
            return getByTitleContainingAndGenreInAndIdIn(title, genres, ids);
        }
        return bookRepository.findByTitleContainingAndYearAndGenreInAndIdIn(title, year, genres, ids);
    }

    public List<Book> getByTitleContainingAndGenreInAndIdIn(String title, List<Genre> genres, List<Integer> ids) {
        return bookRepository.findByTitleContainingAndGenreInAndIdIn(title, genres, ids);
    }

    public List<Genre> getGenresByName(String genreName) {
        return genreRepository.findByNameContaining(genreName);
    }

    public List<Integer> getAuthorIdsByLastName(String authorName) {
        LinkedList<Integer> resultAuthorIds = new LinkedList<>();
        authorRepository.findByLastNameContaining(authorName).forEach(author -> resultAuthorIds.add(author.getId()));
        return resultAuthorIds;
    }

    public List<Integer> getBooksIdsByAuthorName(String authorName) {
        LinkedList<Integer> resultBookIds = new LinkedList<>();
        authorBookRepository.findAllByAuthorIdIn(getAuthorIdsByLastName(authorName)).forEach(authorBook -> resultBookIds.add(authorBook.getBook().getId()));
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
        return bookRepository.findAll();
    }

    /**
     * Gets all book's authors from DB
     *
     * @param bookId - book id
     * @return - list of authors
     */
    public List<Author> getAllAuthors(int bookId) {
        List<Author> authors = new ArrayList<>();
        List<AuthorBook> authorBook = authorBookRepository.findAllByBookId(bookId);

        authorBook.forEach(obj ->
                authors.add(authorRepository.findOne(obj.getAuthor().getId()))
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
        return genreRepository.findOne(genreId).getName();
    }

    /**
     * Gets all genres
     *
     * @return - list of genres
     */
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    /**
     * Gets all readers
     *
     * @return - list of readers
     */
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    /**
     * Gets all authors
     *
     * @return - list of authors
     */
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
