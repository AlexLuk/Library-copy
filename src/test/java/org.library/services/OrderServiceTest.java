package org.library.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.*;

public class OrderServiceTest extends LibraryTest {

    @Autowired
    OrderService orderService;

    @Test
    public void getByReaderAndBook() throws Exception {
        saveTestData();
        assertThat(orderService.getByReaderAndBook(testReaders.get(0), testBooks.get(0)),contains(testBookOrders.get(0)));
        assertThat(orderService.getByReaderAndBook(testReaders.get(0), testBooks.get(1)),contains(testBookOrders.get(1)));
        assertThat(orderService.getByReaderAndBook(testReaders.get(0), testBooks.get(2)),empty());
    }

    @Test
    public void getByReaderIdAndBookItemIdIn() throws Exception {
        saveTestData();
        assertThat(orderService.);
    }

    @Test
    public void findByBookId() throws Exception {

    }

    @Test
    public void addOrder() throws Exception {

    }

}