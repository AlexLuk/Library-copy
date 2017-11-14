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
        assertThat(orderService.cancelOrder(1), is(OrderService.SUCC_ORDER_CANCELED));
        assertThat(orderService.cancelOrder(555), is(OrderService.ERROR_ORDER_CANCELED));
    }

    @Test
    public void editOrder() throws Exception{
        assertThat(orderService.setOrderStatus(1,true), is(OrderService.SUCC_STATUS_CHANGE));
        assertThat(orderService.setOrderStatus(999,true), is(OrderService.ERROR_STATUS_CHANGE));
    }

    @Test
    public void countByReaderIdAndBookId() {
        assertThat(deliveryRepository.countByReaderIdAndBookId(3, 2), is(0));
        assertThat(deliveryRepository.countByReaderIdAndBookId(3, 1), is(1));
    }

    @Test
    public void countBy() {
        assertThat(bookOrderRepository.countByReaderIdAndBookId(2, 4), is(1));
        assertThat(bookOrderRepository.countByReaderIdAndBookId(2, 3), is(0));
    }
}