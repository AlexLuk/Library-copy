package org.library.services;

import org.junit.Before;
import org.library.db.domain.Author;
import org.library.db.domain.AuthorBook;
import org.library.db.domain.Book;
import org.library.db.domain.Genre;
import org.library.db.repo.AuthorBookRepository;
import org.library.db.repo.AuthorRepository;
import org.library.db.repo.BookRepository;
import org.library.db.repo.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LibraryTest {
    protected List<Book> testBooks;
    protected List<Author> testAuthors;
    protected List<Genre> testGenres;
    protected List<Genre> testGenresEmpty;
    protected List<Integer> testBookIdsAll;
    protected List<Integer> testBookIdsEmpty;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorBookRepository authorBookRepository;
    private List<AuthorBook> testAuthorBooks;

    @Before
    public void prepareForTests() throws Exception {
        prepareTestData();
    }

    private void prepareTestData() {
        genresSetup();
        authorsSetup();
        booksSetup();
        bookIdsSetup();
        authorBooksSetup();
    }

    private void genresSetup() {
        testGenres = new LinkedList<>();
        testGenres.add(new Genre("aaa"));
        testGenres.add(new Genre("aaababb"));
        testGenres.add(new Genre("bbb bbb"));
        testGenresEmpty = new LinkedList<>();
    }

    private void authorsSetup() {
        testAuthors = new LinkedList<>();
        testAuthors.add(new Author("AAA", "BBB", "CCC", 1731));
        testAuthors.add(new Author("AAA", "BBB", "AAA", 1732));
        testAuthors.add(new Author("CCC", "BBB", "BBB", 1732));
    }

    private void booksSetup() {
        testBooks = new ArrayList<>();
        testBooks.add(new Book("l1", testGenres.get(0), "BOok book1", "RU", 2189, (short) 6, false));
        testBooks.add(new Book("l1", testGenres.get(1), "book BOok2", "RU", 2189, (short) 6, false));
        testBooks.add(new Book("l1", testGenres.get(2), "bookbook", "RU", 2178, (short) 6, false));
        testBookIdsEmpty = new LinkedList<>();
    }

    private void bookIdsSetup() {
        saveTestData();
        testBookIdsAll = new LinkedList<>();
        testBookIdsAll.add(bookRepository.findByTitleContaining(testBooks.get(0).getTitle()).get(0).getId());
        testBookIdsAll.add(bookRepository.findByTitleContaining(testBooks.get(1).getTitle()).get(0).getId());
        testBookIdsAll.add(bookRepository.findByTitleContaining(testBooks.get(2).getTitle()).get(0).getId());
    }

    private void authorBooksSetup() {
        testAuthorBooks = new LinkedList<>();
        testAuthorBooks.add(new AuthorBook(testAuthors.get(0), testBooks.get(0)));
        testAuthorBooks.add(new AuthorBook(testAuthors.get(0), testBooks.get(1)));
        testAuthorBooks.add(new AuthorBook(testAuthors.get(2), testBooks.get(2)));
    }

    protected void saveTestData() {
        genreRepository.save(testGenres);
        authorRepository.save(testAuthors);
        bookRepository.save(testBooks);
        authorBookRepository.save(testAuthorBooks);
    }
}
