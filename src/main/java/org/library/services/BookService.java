package org.library.services;

import org.library.db.domain.Author;
import org.library.db.domain.AuthorBook;
import org.library.db.domain.Book;
import org.library.db.repo.AuthorBookRepository;
import org.library.db.repo.AuthorRepository;
import org.library.db.repo.BookRepository;
import org.library.db.repo.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorBookRepository authorBookRepository;

    @Autowired
    GenreRepository genreRepository;

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
}
