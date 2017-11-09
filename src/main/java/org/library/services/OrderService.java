package org.library.services;

import com.google.gson.Gson;
import org.library.controllers.LibraryOrderController;
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

    /**
     * Search for list of orders for specific book and for specific reader
     *
     * @param reader - reader for search
     * @param book   - book for search
     * @return - list of book orders
     */
    List<BookOrder> getByReaderAndBook(Reader reader, Book book) {
        return bookOrderRepository.findByReaderIdAndBookId(reader.getId(), book.getId());
    }

    /**
     * Search for deliveries for specific reader and for list of specific book items
     *
     * @param reader    - reader for search
     * @param bookItems - items
     * @return - list of deliveries
     */
    List<Delivery> getByReaderIdAndBookItemIdIn(Reader reader, List<BookItem> bookItems) {
        List<Integer> bookItemIds = new LinkedList<>();
        bookItems.forEach(bookItem -> bookItemIds.add(bookItem.getId()));
        return deliveryRepository.findByReaderIdAndBookItemIdIn(reader.getId(), bookItemIds);
    }

    /**
     * Search for list of book items for specific book
     *
     * @param book - book for search
     * @return - list of book items
     */
    List<BookItem> getByBookId(Book book) {
        return bookItemRepository.findByBookId(book.getId());
    }

    public boolean addOrder(Reader reader, int bookId, boolean isOnHands) {
        Book book = bookRepository.findOne(bookId);
        List<BookOrder> readerExactBook = getByReaderAndBook(reader, book);
        List<BookItem> bookItems = getByBookId(book);
        List<Delivery> readerDeliveries = getByReaderIdAndBookItemIdIn(reader, bookItems);
        if (readerExactBook.size() == 0 && readerDeliveries.size() == 0) {
            BookOrder bookOrder = new BookOrder(reader, book, isOnHands);
            bookOrderRepository.save(bookOrder);
            return true;
        } else {
            return false;
        }
    }

    public String jsonBookOrder(List<BookOrder> bookOrders) {
        List<BookOrderJson> bookOrdersJsons = new LinkedList<>();
        bookOrders.forEach(bookOrder -> bookOrdersJsons.add(new BookOrderJson(bookOrder)));
        Gson gson = new Gson();
        return gson.toJson(bookOrders);
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
            shelfId = bookOrder.getBook().getShelfId();
            readerId = bookOrder.getReader().getId();
            bookId = bookOrder.getBook().getId();
            isOnHands = bookOrder.getOnHands();
        }
    }

}
