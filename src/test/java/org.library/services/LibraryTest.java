package org.library.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.library.db.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LibraryTest {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReaderRepository readerRepository;
    @Autowired
    BookOrderRepository bookOrderRepository;
    @Autowired
    BookItemRepository bookItemRepository;
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    ItemStatusRepository itemStatusRepository;

    @Test
    public void initialTest(){}
}
