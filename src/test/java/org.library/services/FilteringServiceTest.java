package org.library.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.library.db.domain.Author;
import org.library.db.domain.AuthorBook;
import org.library.db.domain.Book;
import org.library.db.domain.Genre;
import org.library.db.repo.AuthorBookRepository;
import org.library.db.repo.AuthorRepository;
import org.library.db.repo.BookRepository;
import org.library.db.repo.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FilteringServiceTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorBookRepository authorBookRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    BookService bookService;
    @Autowired
    FilteringService filteringService;

    List<Book> testBooks;
    List<Book> resultBooks;
    List<Author> testAuthors;
    List<AuthorBook> testAuthorBooks;
    List<Genre> testGenres;

    @Before
    public void prepareForTests() throws Exception {
        prepareTestData();
    }

    private void prepareTestData() {
        genresSetup();
        authorsSetup();
        booksSetup();
        authorBooksSetup();
    }

    private void saveTestData() {
        genreRepository.save(testGenres);
        authorRepository.save(testAuthors);
        bookRepository.save(testBooks);
        authorBookRepository.save(testAuthorBooks);
    }

    private void genresSetup() {
        testGenres = new LinkedList<>();
        testGenres.add(new Genre("aaa"));
        testGenres.add(new Genre("aaababb"));
        testGenres.add(new Genre("bbb bbb"));
    }

    private void authorsSetup() {
        testAuthors = new LinkedList<>();
        testAuthors.add(new Author("AAA", "BBB", "CCC", 1731));
        testAuthors.add(new Author("AAA", "BBB", "AAA", 1732));
        testAuthors.add(new Author("CCC", "BBB", "BBB", 1732));
    }

    private void booksSetup() {
        testBooks = new LinkedList<>();
        resultBooks = new LinkedList<>();
        testBooks.add(new Book("l1", testGenres.get(0), "BOok book1", "RU", 2189, (short) 6, false));
        testBooks.add(new Book("l1", testGenres.get(1), "book BOok2", "RU", 2189, (short) 6, false));
        testBooks.add(new Book("l1", testGenres.get(2), "bookbook", "RU", 2178, (short) 6, false));
    }

    private void authorBooksSetup() {
        testAuthorBooks = new LinkedList<>();
        testAuthorBooks.add(new AuthorBook(testAuthors.get(0), testBooks.get(0)));
        testAuthorBooks.add(new AuthorBook(testAuthors.get(0), testBooks.get(1)));
        testAuthorBooks.add(new AuthorBook(testAuthors.get(2), testBooks.get(2)));
    }

    private void prepareBooksResults() {
        resultBooks.clear();
        resultBooks.addAll(testBooks);
    }

    @Test
    public void filterBooksByTitle() throws Exception {
        saveTestData();
        prepareBooksResults();
        assertThat(filteringService.filterBooksByTitle("ok bo", resultBooks),
                containsInAnyOrder(testBooks.get(0), testBooks.get(1)));
        prepareBooksResults();
        assertThat(filteringService.filterBooksByTitle("okb", resultBooks), containsInAnyOrder(testBooks.get(2)));
        prepareBooksResults();
        assertThat(filteringService.filterBooksByTitle("adad", resultBooks), empty());
        prepareBooksResults();
        assertThat(filteringService.filterBooksByTitle("", resultBooks),
                containsInAnyOrder(testBooks.get(0), testBooks.get(1), testBooks.get(2)));
    }

    @Test
    public void filterBooksByAuthor() throws Exception {
        saveTestData();
        prepareBooksResults();
        assertThat(filteringService.filterBooksByAuthor("", resultBooks)
                , containsInAnyOrder(testBooks.get(0), testBooks.get(1), testBooks.get(2)));
        prepareBooksResults();
        assertThat(filteringService.filterBooksByAuthor("---author---", resultBooks), empty());
        prepareBooksResults();
        assertThat(filteringService.filterBooksByAuthor("AAA BBB", resultBooks)
                , containsInAnyOrder(testBooks.get(0), testBooks.get(1)));
        prepareBooksResults();
        assertThat(filteringService.filterBooksByAuthor("BBB", resultBooks)
                , containsInAnyOrder(testBooks.get(0), testBooks.get(1), testBooks.get(2)));
        prepareBooksResults();
        assertThat(filteringService.filterBooksByAuthor("CCC BBB", resultBooks)
                , containsInAnyOrder(testBooks.get(2)));
    }

    @Test
    public void filterBooksByYear() throws Exception {
        saveTestData();
        prepareBooksResults();
        assertThat(filteringService.filterBooksByYear("2189", resultBooks),
                containsInAnyOrder(testBooks.get(0), testBooks.get(1)));
        prepareBooksResults();
        assertThat(filteringService.filterBooksByYear("", resultBooks),
                containsInAnyOrder(testBooks.get(0), testBooks.get(1), testBooks.get(2)));
        prepareBooksResults();
        assertThat(filteringService.filterBooksByYear("21 78", resultBooks),
                containsInAnyOrder(testBooks.get(0), testBooks.get(1), testBooks.get(2)));
        prepareBooksResults();
        assertThat(filteringService.filterBooksByYear("2178", resultBooks),
                containsInAnyOrder(testBooks.get(2)));
        prepareBooksResults();
        assertThat(filteringService.filterBooksByYear("3000", resultBooks), empty());
    }

    @Test
    public void filterBooksByGenre() throws Exception {
        saveTestData();
        prepareBooksResults();
        assertThat(filteringService.filterBooksByGenre("bbb", resultBooks),
                containsInAnyOrder(testBooks.get(2)));
        prepareBooksResults();
        assertThat(filteringService.filterBooksByGenre("-not-exist-", resultBooks), empty());
        prepareBooksResults();
        assertThat(filteringService.filterBooksByGenre("", resultBooks),
                containsInAnyOrder(testBooks.get(0), testBooks.get(1), testBooks.get(2)));
        prepareBooksResults();
        assertThat(filteringService.filterBooksByGenre("aa", resultBooks),
                containsInAnyOrder(testBooks.get(0), testBooks.get(1)));
    }

    @Test
    public void getAuthorsByName() throws Exception {
        saveTestData();
        assertThat(filteringService.getAuthorsByName(""),
                hasItems(testAuthors.get(0), testAuthors.get(1), testAuthors.get(2)));
        assertThat(filteringService.getAuthorsByName("xxxxxx"), empty());
        assertThat(filteringService.getAuthorsByName("bbb b"),
                containsInAnyOrder(testAuthors.get(2)));
        assertThat(filteringService.getAuthorsByName("bbb"),
                containsInAnyOrder(testAuthors.get(0), testAuthors.get(1), testAuthors.get(2)));
        assertThat(filteringService.getAuthorsByName("aaa bbb"),
                containsInAnyOrder(testAuthors.get(0), testAuthors.get(1)));
    }

    @Test
    public void getGenresByName() throws Exception {
        saveTestData();

        assertThat(filteringService.getGenresByName("aaa"),
                containsInAnyOrder(testGenres.get(0), testGenres.get(1)));
        assertThat(filteringService.getGenresByName("abab"),
                containsInAnyOrder(testGenres.get(1)));
        assertThat(filteringService.getGenresByName("bbb b"),
                containsInAnyOrder(testGenres.get(2)));
        assertThat(filteringService.getGenresByName(""),
                hasItems(testGenres.get(0), testGenres.get(1), testGenres.get(2)));
        assertThat(filteringService.getGenresByName("xxxxxx"), empty());
    }
}