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
    public static final String SUCC_STATUS_CHANGE = "succStatusChange";
    public static final String ERROR_STATUS_CHANGE = "errorStatusChange";
    public static final String SUCC_ORDER_CANCELED = "succOrderCanceled";
    public static final String ERROR_ORDER_CANCELED = "errorOrderCanceled";
    public static final String SUCC_ORDER_CREATED = "succOrderCreated";
    public static final String ERROR_ORDER_CREATE_ORDERED = "errorOrderCreateOrdered";
    public static final String ERROR_ORDER_CREATE_DELIVERED = "errorOrderCreateDelivered";
    public static final String SUCC_ORDER_CREATED1 = "succOrderCreated";

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
    public String addOrder(Reader reader, int bookId, boolean isOnHands) {
        int checkReaderForBook = checkReaderForBook(reader.getId(), bookId);
        switch (checkReaderForBook) {
            case 0:
                BookOrder bookOrder = new BookOrder(reader, bookRepository.findOne(bookId), isOnHands);
                bookOrderRepository.save(bookOrder);
                logger.info("Add order {}" + bookOrder.getId());
                return SUCC_ORDER_CREATED;
            case 1:
                return ERROR_ORDER_CREATE_ORDERED;
            case 2:
                return ERROR_ORDER_CREATE_DELIVERED;
            default:
                return SUCC_ORDER_CREATED1;
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

    public String setOrderStatus(int bookOrderId, boolean status) {
        try {
            BookOrder order = bookOrderRepository.findOne(bookOrderId);
            order.setOnHands(status);
            bookOrderRepository.save(order);
            logger.info("Order status changed {}", bookOrderId);
            return SUCC_STATUS_CHANGE;
        } catch (Exception e) {
            logger.info("Error during order edit {}", bookOrderId);
        }
        return ERROR_STATUS_CHANGE;
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
     * @return return message param
     */
    public String cancelOrder(int bookOrderId) {
        try {
            bookOrderRepository.delete(bookOrderId);
            logger.info("delete order by orderId {}", bookOrderId);
            return SUCC_ORDER_CANCELED;
        } catch (EmptyResultDataAccessException e) {
            logger.error("Cancel order error", e);
        }
        return ERROR_ORDER_CANCELED;
    }

}
