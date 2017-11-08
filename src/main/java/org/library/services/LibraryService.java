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
}
