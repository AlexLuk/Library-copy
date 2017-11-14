package org.library.services;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.library.db.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LibraryServiceTest extends LibraryTest {
    @Test
    public void jsonBooks() throws Exception {
        List<Book> books = new LinkedList<>();
        assertThat(libraryService.jsonBooks(books), is("[]"));
        books.add(bookRepository.getOne(8));
        assertThat(libraryService.jsonBooks(books), is("[{\"title\":\"Несколько авторов\",\"authors\":\"Иванов Иван Иванович </br>Петров Петр Петрович </br>Сергеев Сергей Сергеевич \",\"year\":2000,\"genre\":\"fantasy\",\"isRare\":false,\"amount\":1,\"book_id\":8}]"));

    }

    @Autowired
    LibraryService libraryService;

    @Test
    public void parseAuthorString() throws Exception {
        assertThat(libraryService.parseAuthorString("d a s v"), containsInAnyOrder("d", "a", "s"));
        assertThat(libraryService.parseAuthorString("d a s"), containsInAnyOrder("d", "a", "s"));
        assertThat(libraryService.parseAuthorString("d a"), containsInAnyOrder("d", "a", ""));
        assertThat(libraryService.parseAuthorString(""), containsInAnyOrder("", "", ""));
    }

    @Test
    public void findByComplexQuery() throws Exception {
        assertThat(bookRepository.findByComplexQuery("zzzzzzzzz", null, null, "", "", ""), empty());
        bookRepository.findByComplexQuery("у", null, null, "", "", "").forEach(book -> System.err.println(book.getTitle()));
        System.err.println("//");
        bookRepository.findByComplexQuery("", null, null, "", "", "").forEach(book -> System.err.println(book.getTitle()));
        assertThat(bookRepository.findByComplexQuery("", 9999, null, "", "", ""), empty());
        assertThat(bookRepository.findByComplexQuery("", null, null, "zzzzzzz", "", ""), empty());
        assertThat(bookRepository.findByComplexQuery("", null, null, "", "", ""), hasItems(bookRepository.findOne(1)));
        assertThat(bookRepository.findByComplexQuery("", null, null, "оН", "", ""), hasItems(bookRepository.findOne(1)));
        assertThat(bookRepository.findByComplexQuery("", null, null, "", "дж", ""), hasItems(bookRepository.findOne(3)));
        assertThat(bookRepository.findByComplexQuery("оро", null, null, "", "", ""), hasItems(bookRepository.findOne(1)));
        assertThat(bookRepository.findByComplexQuery("", 1782, null, "", "", ""), hasItems(bookRepository.findOne(1)));
        assertThat(bookRepository.findByComplexQuery("", null, 1, "", "", ""), hasItems(bookRepository.findOne(1)));
    }
}
