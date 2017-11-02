import org.junit.Test;
import org.library.db.domain.Author;
import org.library.db.repo.AuthorRepository;
import org.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Tests {
    @Autowired
    BookService bookService;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void getBookAuthors() throws Exception {
        /*List<Author> authors = new ArrayList<>();
        List<Author> a = authorRepository.findByName("Денис");
        if (a.size() > 0)
            authors.add(a.get(0));
        authors.add(authorRepository.findOne(107));
        authors.add(authorRepository.findOne(108));

        assertThat(bookService.getAllAuthors(8), is(authors));
        */
    }
}
