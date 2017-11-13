package org.library.services;

import org.junit.Test;
import org.library.db.domain.Book;
import org.library.db.domain.BookItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class OrderServiceTest extends LibraryTest {
    @Autowired
    OrderService orderService;

    @Test
    public void cancelOrder() throws Exception {
        assertThat(orderService.cancelOrder(1), is(true));
        assertThat(orderService.cancelOrder(555), is(false));
    }

    @Test
    public void countByReaderIdAndBookId() {
        assertThat(deliveryRepository.countByReaderIdAndBookId(3, 2), is(1));
        assertThat(deliveryRepository.countByReaderIdAndBookId(3, 1), is(0));
    }

    @Test
    public void countBy() {
        assertThat(bookOrderRepository.countByReaderIdAndBookId(2, 4), is(1));
        assertThat(bookOrderRepository.countByReaderIdAndBookId(2, 3), is(0));
    }
}