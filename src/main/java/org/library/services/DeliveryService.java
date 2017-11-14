package org.library.services;

import org.library.db.domain.BookItem;
import org.library.db.domain.BookOrder;
import org.library.db.domain.Delivery;
import org.library.db.domain.Reader;
import org.library.db.repo.BookItemRepository;
import org.library.db.repo.BookOrderRepository;
import org.library.db.repo.DeliveryRepository;
import org.library.db.repo.ItemStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DeliveryService {

    //todo add delivery filter

    private final static Logger logger = LoggerFactory.getLogger(DeliveryService.class);
    public static final String SUCC_DELIVERY_CREATED = "succDeliveryCreated";
    public static final String ERROR_DELIVERY_CREATED = "errorDeliveryCreated";
    public static final String SUCC_RETURN_BOOK = "succReturnBook";
    public static final String ERROR_RETURN_BOOK = "errorReturnBook";

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    BookOrderRepository bookOrderRepository;

    @Autowired
    BookItemRepository bookItemRepository;

    @Autowired
    ItemStatusRepository itemStatusRepository;

    //todo refactor
    //todo use status enum
    private int freeBook(BookOrder bookOrder) {
        int bookId = bookOrder.getBook().getId();
        List<BookItem> bookItems = bookItemRepository.findByBookId(bookId);
        for (BookItem item : bookItems) {
            if (item.getStatus().getName().equals("free")) {
                return item.getId();
            }
        }
        return -1;
    }

    //todo refactor
    public String addDeliveryByBookOrder(Integer bookOrderId) {
        BookOrder bookOrder = bookOrderRepository.getOne(bookOrderId);
        boolean toHand = bookOrder.getOnHands();
        int freeBookItem = freeBook(bookOrder);
        if (freeBookItem == -1) {

            return ERROR_DELIVERY_CREATED;
        }

        boolean wasAdd = addDelivery(bookOrder.getReader(), bookItemRepository.getOne(freeBook(bookOrder)));
        if (wasAdd) {
            bookOrderRepository.delete(bookOrder);
            bookItemRepository.getOne(freeBookItem)
                    .setStatus(toHand ? itemStatusRepository.getOne(2) : itemStatusRepository.getOne(3));
        }
        return SUCC_DELIVERY_CREATED;
    }

    //todo refactor
    private boolean addDelivery(Reader reader, BookItem bookItem) {
        Delivery delivery = new Delivery(reader, bookItem);
        deliveryRepository.save(delivery);
        logger.info("Add delivery {}", delivery.getId());
        return true;
    }

    /**
     * Return book item to library and delete delivery
     *
     * @param deliveryId - delivery id
     * @return - true if delivery deleted successfully
     */
    public String returnDelivery(int deliveryId) {
        //todo replace status with enum
        try {
            Delivery delivery = deliveryRepository.getOne(deliveryId);
            BookItem bookItem = delivery.getBookItem();
            bookItem.setStatus(itemStatusRepository.getOne(1));
            deliveryRepository.delete(deliveryId);
            return SUCC_RETURN_BOOK;
        } catch (EntityNotFoundException e) {
            logger.info("Return delivery error {}", deliveryId);
        }
        return ERROR_RETURN_BOOK;
    }

    public List<Delivery> getDeliveriesByComplexCondition() {
        return deliveryRepository.findByComplexQuery();
    }
}
