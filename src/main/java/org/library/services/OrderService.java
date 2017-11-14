package org.library.services;

import com.google.gson.Gson;
import org.library.db.domain.BookOrder;
import org.library.db.domain.Reader;
import org.library.db.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {

    private final static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    BookOrderRepository bookOrderRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    BookItemRepository bookItemRepository;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BookRepository bookRepository;

    //todo add order filter

    /**
     * Add book to reader orders, perform check for book in reader's orders and deliveries
     *
     * @param reader    - reader
     * @param bookId    - bookId
     * @param isOnHands - type of order
     * @return - 0 - order added
     * 1 - reader has book in deliveries     *
     * 2 - reader has book in orders
     */
    public int addOrder(Reader reader, int bookId, boolean isOnHands) {
        int checkReaderForBook = checkReaderForBook(reader.getId(), bookId);
        if (checkReaderForBook == 0) {
            BookOrder bookOrder = new BookOrder(reader, bookRepository.findOne(bookId), isOnHands);
            bookOrderRepository.save(bookOrder);
            logger.info("add order {}" + bookOrder);
            return checkReaderForBook;
        } else {
            return checkReaderForBook;
        }
    }

    /**
     * @param readerId - reader id
     * @param bookId   - book id
     * @return 0 - no book in orders and deliveries     *
     * 1 - book  in deliveries
     * 2 - book  in orders
     */
    public int checkReaderForBook(int readerId, int bookId) {
        if (bookOrderRepository.countByReaderIdAndBookId(readerId, bookId) != 0) {
            return 2;
        } else if (deliveryRepository.countByReaderIdAndBookId(readerId, bookId) != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public String jsonBookOrder(List<BookOrder> bookOrders) {
        List<BookOrderJson> bookOrdersJsons = new LinkedList<>();
        bookOrders.forEach(bookOrder -> bookOrdersJsons.add(new BookOrderJson(bookOrder)));
        Gson gson = new Gson();
        return gson.toJson(bookOrders);
    }

    public boolean setOrderStatus(int bookOrderId, boolean status) {
        try {
            BookOrder order = bookOrderRepository.findOne(bookOrderId);
            order.setOnHands(status);
            bookOrderRepository.save(order);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private class BookOrderJson {
        String userFullName;
        String title;
        String shelfId;
        int readerId;
        int bookId;
        boolean isOnHands;

        public BookOrderJson(BookOrder bookOrder) {
            userFullName = bookOrder.getReader().getFullName();
            title = bookOrder.getBook().getTitle();
            shelfId = bookOrder.getBook().getShelfCode();
            readerId = bookOrder.getReader().getId();
            bookId = bookOrder.getBook().getId();
            isOnHands = bookOrder.getOnHands();
        }
    }

    /**
     * Cancel order by bookOrderId
     *
     * @param bookOrderId - book order id
     * @return true - order successfully deleted
     */
    public boolean cancelOrder(int bookOrderId) {
        try {
            bookOrderRepository.delete(bookOrderId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("cancel order error", e);
            return false;
        }
        logger.info("delete order by orderId {}", bookOrderId);
        return true;
    }

}
