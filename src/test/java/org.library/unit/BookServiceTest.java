package org.library.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.library.db.domain.Author;
import org.library.db.repo.AuthorRepository;
import org.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class BookServiceTest {
    @Autowired
    BookService bookService;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void getBookAuthors() throws Exception {
        List<Author> authors = new ArrayList<>();
        authors.add(authorRepository.findOne(106));
        authors.add(authorRepository.findOne(107));
        authors.add(authorRepository.findOne(108));

        assertThat(bookService.getAllAuthors(8), is(authors));
    }
}
