package org.library.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void simpleTest() throws Exception {
        saveTestData();
        assertThat(bookRepository.finaByComplexQuery("zzzzzzzzz", null, null, ""), empty());
        assertThat(bookRepository.finaByComplexQuery("", 9999, null, ""), empty());
        assertThat(bookRepository.finaByComplexQuery("", null, null, "zzzzzzz"), empty());
        assertThat(bookRepository.finaByComplexQuery("", null, null, ""), hasItems(bookRepository.findOne(1)));
        assertThat(bookRepository.finaByComplexQuery("", null, null, "оН"), hasItems(bookRepository.findOne(1)));
        assertThat(bookRepository.finaByComplexQuery("оро", null, null, ""), hasItems(bookRepository.findOne(1)));
        assertThat(bookRepository.finaByComplexQuery("", 1782, null, ""), hasItems(bookRepository.findOne(1)));
        assertThat(bookRepository.finaByComplexQuery("", null, 1, ""), hasItems(bookRepository.findOne(1)));
    }

    @Test
    public void deleteReader() throws Exception {
        saveTestData();
        assertThat(readerRepository.getOne(testReaders.get(0).getId()), is(readerRepository.getOne(testReaders.get(0).getId())));
        libraryService.deleteReaderById(testReaders.get(0).getId());
        assertThat(readerRepository.findOne(testReaders.get(0).getId()), nullValue());
    }
}
