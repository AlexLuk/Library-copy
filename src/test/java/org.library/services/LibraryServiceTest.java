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

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LibraryServiceTest {
    @Autowired
    LibraryService libraryService;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorBookRepository authorBookRepository;

    private List<Book> testBooks;

    private List<Author> testAuthors;

    private List<AuthorBook> testAuthorBooks;

    private List<Genre> testGenres;

    private List<Genre> testGenresEmpty;
    private List<Integer> testBookIdsAll;
    private List<Integer> testBookIdsEmpty;
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

    private void saveTestData() {
        genreRepository.save(testGenres);
        authorRepository.save(testAuthors);
        bookRepository.save(testBooks);
        authorBookRepository.save(testAuthorBooks);
    }

    @Test
    public void getByTitleContainingAndYearAndGenreInAndIdIn() throws Exception {
        saveTestData();
        assertThat(libraryService.getByTitleContainingAndYearAndGenreInAndIdIn("", 2178, testGenresEmpty, testBookIdsEmpty), empty());
        assertThat(libraryService.getByTitleContainingAndYearAndGenreInAndIdIn("", 2178, testGenresEmpty, testBookIdsAll), empty());
        assertThat(libraryService.getByTitleContainingAndYearAndGenreInAndIdIn("", 2178, testGenres, testBookIdsEmpty), empty());
        assertThat(libraryService.getByTitleContainingAndYearAndGenreInAndIdIn("book", null, testGenres, testBookIdsAll), hasItems(testBooks.get(0), testBooks.get(1), testBooks.get(2)));
        assertThat(libraryService.getByTitleContainingAndYearAndGenreInAndIdIn("bookbook", 2178, testGenres, testBookIdsAll), contains(testBooks.get(2)));

    }

    @Test
    public void getByTitleContainingAndGenreInAndIdIn() throws Exception {
        saveTestData();
        assertThat(libraryService.getByTitleContainingAndGenreInAndIdIn("", testGenresEmpty, testBookIdsEmpty), empty());
        assertThat(libraryService.getByTitleContainingAndGenreInAndIdIn("", testGenresEmpty, testBookIdsAll), empty());
        assertThat(libraryService.getByTitleContainingAndGenreInAndIdIn("", testGenres, testBookIdsEmpty), empty());
        assertThat(libraryService.getByTitleContainingAndGenreInAndIdIn("book", testGenres, testBookIdsAll), hasItems(testBooks.get(0), testBooks.get(1), testBooks.get(2)));
        assertThat(libraryService.getByTitleContainingAndGenreInAndIdIn("bookbook", testGenres, testBookIdsAll), contains(testBooks.get(2)));
    }

    @Test
    public void getGenresByName() throws Exception {
        saveTestData();
        assertThat(libraryService.getGenresByName("aaa"), containsInAnyOrder(testGenres.get(0), testGenres.get(1)));
        assertThat(libraryService.getGenresByName(""), hasItems(testGenres.get(0), testGenres.get(1), testGenres.get(2)));
        assertThat(libraryService.getGenresByName("zzzzzzz"), empty());
    }

    @Test
    public void getAuthorIdsByLastName() throws Exception {
        saveTestData();
        assertThat(libraryService.getAuthorIdsByLastName("CC"), containsInAnyOrder(testAuthors.get(0).getId()));
        assertThat(libraryService.getAuthorIdsByLastName("AAA"), containsInAnyOrder(testAuthors.get(1).getId()));
        assertThat(libraryService.getAuthorIdsByLastName(""), hasItems(testAuthors.get(0).getId(), testAuthors.get(1).getId(), testAuthors.get(2).getId()));
        assertThat(libraryService.getAuthorIdsByLastName("zzzzzzz"), empty());
    }

    @Test
    public void getBooksIdsByAuthorName() throws Exception {
        saveTestData();
        assertThat(libraryService.getBooksIdsByAuthorName("CC"), containsInAnyOrder(testBooks.get(0).getId(), testBooks.get(1).getId()));
        assertThat(libraryService.getBooksIdsByAuthorName("BB"), containsInAnyOrder(testBooks.get(2).getId()));
        assertThat(libraryService.getBooksIdsByAuthorName("zzzzzzz"), empty());
        assertThat(libraryService.getBooksIdsByAuthorName(""), hasItems(testBooks.get(0).getId(), testBooks.get(1).getId(), testBooks.get(2).getId()));
    }

    @Test
    public void getBooksByComplexCondition() throws Exception {
        saveTestData();
        //by year, genre
        assertThat(libraryService.getBooksByComplexCondition("","",2189,"aaa"),containsInAnyOrder(testBooks.get(0),testBooks.get(1)));
        //by genre
        assertThat(libraryService.getBooksByComplexCondition("","",null,"bbb"),containsInAnyOrder(testBooks.get(2)));
        //by title
        assertThat(libraryService.getBooksByComplexCondition("bookbook","",null,""),containsInAnyOrder(testBooks.get(2)));
        assertThat(libraryService.getBooksByComplexCondition("book","",null,""),is(testBooks));
        //by author
        assertThat(libraryService.getBooksByComplexCondition("","BB",null,""),containsInAnyOrder(testBooks.get(2)));
        assertThat(libraryService.getBooksByComplexCondition("","AAA",null,""),empty());
        assertThat(libraryService.getBooksByComplexCondition("","",2189,"aaa"),containsInAnyOrder(testBooks.get(0),testBooks.get(1)));
        assertThat(libraryService.getBooksByComplexCondition("aaaaaa","",null,""),empty());
        assertThat(libraryService.getBooksByComplexCondition("","aaaaaaa",null,""),empty());
        assertThat(libraryService.getBooksByComplexCondition("","",null,"aaaaaaa"),empty());

    }

    @Test
    public void getBookAuthors() throws Exception {
        List<Author> authors = new ArrayList<>();
        authors.add(authorRepository.findOne(106));
        authors.add(authorRepository.findOne(107));
        authors.add(authorRepository.findOne(108));

        assertThat(libraryService.getAllAuthors(8), is(authors));
        libraryService.getAllBooks().forEach(book -> System.err.println(book.getTitle()));
    }
}
