package org.library.services;

import org.library.db.domain.Book;
import org.library.db.domain.BookOrder;
import org.library.db.domain.Delivery;
import org.library.db.domain.Reader;
import org.library.db.repo.BookOrderRepository;
import org.library.db.repo.BookRepository;
import org.library.db.repo.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    BookOrderRepository bookOrderRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    public List<BookOrder> getByReaderByStatus(Reader reader, boolean isOnHands) {
        return bookOrderRepository.findByReaderIdAndOnHands(reader.getId(), isOnHands);
    }

    public List<BookOrder> getByReader(Reader reader) {
        return bookOrderRepository.findByReaderId(reader.getId());
    }

    //todo  check that reader does not have this book in orders and open deliveries
    public boolean addOrder(Reader reader, Book book, boolean isOnHands) {
        //check that reader does not have this book in orders and open deliveries
        BookOrder bookOrder = new BookOrder(reader, book, isOnHands);
        bookOrderRepository.save(bookOrder);
        return true;
    }

}
