package org.library.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.library.db.domain.Author;
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
public class LibraryServiceTest extends LibraryTest {
    @Autowired
    LibraryService libraryService;

    @Test
    public void deleteReader() throws Exception {
        saveTestData();
        assertThat(readerRepository.getOne(testReaders.get(0).getId()),is(readerRepository.getOne(testReaders.get(0).getId())));
        libraryService.deleteReaderById(testReaders.get(0).getId());
        assertThat(readerRepository.findOne(testReaders.get(0).getId()),nullValue());
    }

    @Test
    public void getByTitleContainingAndYearAndGenreInAndIdIn() throws Exception {
        saveTestData();
        assertThat(libraryService.getByTitleContainingAndYearAndGenreInAndIdIn("", 2178, testGenresEmpty, testBookIdsEmpty), empty());
        assertThat(libraryService.getByTitleContainingAndYearAndGenreInAndIdIn("", 2178, testGenresEmpty, testBookIdsAll), empty());
        assertThat(libraryService.getByTitleContainingAndYearAndGenreInAndIdIn("", 2178, testGenres, testBookIdsEmpty), empty());
        assertThat(libraryService.getByTitleContainingAndYearAndGenreInAndIdIn("book", null, testGenres, testBookIdsAll), hasItems(testBooks.get(0), testBooks.get(1), testBooks.get(2)));
        assertThat(libraryService.getByTitleContainingAndYearAndGenreInAndIdIn("ok Bo", null, testGenres, testBookIdsAll), containsInAnyOrder(testBooks.get(0),testBooks.get(1)));
        assertThat(libraryService.getByTitleContainingAndYearAndGenreInAndIdIn("bookbook", 2178, testGenres, testBookIdsAll), contains(testBooks.get(2)));
    }

    @Test
    public void getByTitleContainingAndGenreInAndIdIn() throws Exception {
        saveTestData();
        assertThat(libraryService.getByTitleContainingAndGenreInAndIdIn("", testGenresEmpty, testBookIdsEmpty), empty());
        assertThat(libraryService.getByTitleContainingAndGenreInAndIdIn("", testGenresEmpty, testBookIdsAll), empty());
        assertThat(libraryService.getByTitleContainingAndGenreInAndIdIn("", testGenres, testBookIdsEmpty), empty());
        assertThat(libraryService.getByTitleContainingAndGenreInAndIdIn("book", testGenres, testBookIdsAll), hasItems(testBooks.get(0), testBooks.get(1), testBooks.get(2)));
        assertThat(libraryService.getByTitleContainingAndGenreInAndIdIn("ok bo", testGenres, testBookIdsAll), containsInAnyOrder(testBooks.get(0),testBooks.get(1)));
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
        assertThat(libraryService.getAuthorIdsByLastName("Cc"), containsInAnyOrder(testAuthors.get(0).getId()));
        assertThat(libraryService.getAuthorIdsByLastName("AaA"), containsInAnyOrder(testAuthors.get(1).getId()));
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
        assertThat(libraryService.getBooksByComplexCondition("", "", 2189, "aaa"), containsInAnyOrder(testBooks.get(0), testBooks.get(1)));
        //by genre
        assertThat(libraryService.getBooksByComplexCondition("", "", null, "bbb"), containsInAnyOrder(testBooks.get(2)));
        //by title
        assertThat(libraryService.getBooksByComplexCondition("bookbook", "", null, ""), containsInAnyOrder(testBooks.get(2)));
        //assertThat(libraryService.getBooksByComplexCondition("book", "", null, ""), containsInAnyOrder
        // (testBooks));
        //by author
        assertThat(libraryService.getBooksByComplexCondition("", "BB", null, ""), containsInAnyOrder(testBooks.get(2)));
        assertThat(libraryService.getBooksByComplexCondition("", "AAA", null, ""), empty());
        assertThat(libraryService.getBooksByComplexCondition("", "", 2189, "aaa"), containsInAnyOrder(testBooks.get(0), testBooks.get(1)));
        assertThat(libraryService.getBooksByComplexCondition("aaaaaa", "", null, ""), empty());
        assertThat(libraryService.getBooksByComplexCondition("", "aaaaaaa", null, ""), empty());
        assertThat(libraryService.getBooksByComplexCondition("", "", null, "aaaaaaa"), empty());
    }
}
