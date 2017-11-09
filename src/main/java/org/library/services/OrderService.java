package org.library.services;

import org.library.db.domain.*;
import org.library.db.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {

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

    public List<BookOrder> getByReaderByStatus(Reader reader, boolean isOnHands) {
        return bookOrderRepository.findByReaderIdAndOnHands(reader.getId(), isOnHands);
    }

    public List<BookOrder> getByReader(Reader reader) {
        return bookOrderRepository.findByReaderId(reader.getId());
    }

    public List<BookOrder> getByReaderAndBook(Reader reader, Book book) {
        return bookOrderRepository.findByReaderIdAndBookId(reader.getId(), book.getId());
    }

    public List<Delivery> getByReaderIdAndBookItemIdIn(Reader reader, List<BookItem> bookItems) {
        List<Integer> bookItemIds = new LinkedList<>();
        bookItems.forEach(bookItem -> bookItemIds.add(bookItem.getId()));
        return deliveryRepository.findByReaderIdAndBookItemIdIn(reader.getId(), bookItemIds);
    }

    public List<BookItem> findByBookId(Book book) {
        return bookItemRepository.findByBookId(book.getId());
    }

    public boolean addOrder(Reader reader, int bookId, boolean isOnHands) {
        Book book = bookRepository.findOne(bookId);
        List<BookOrder> readerExactBook = getByReaderAndBook(reader, book);
        List<BookItem> bookItems = findByBookId(book);
        List<Delivery> readerDeliveries = getByReaderIdAndBookItemIdIn(reader, bookItems);
        if (readerExactBook.size() == 0 && readerDeliveries.size() == 0) {
            BookOrder bookOrder = new BookOrder(reader, book, isOnHands);
            bookOrderRepository.save(bookOrder);
            return true;
        } else {
            return false;
        }
    }

}
