package org.library;

import org.library.db.DBController;
import org.library.db.ResourcesManager;
import org.library.db.dao.impl.BookDAO;
import org.library.db.models.Book;

import java.util.Optional;

public class Tests {
    public static void main(String[] args) {
        DBController dbController = new DBController();
        dbController.openConn();

        if( dbController.isConnected() ) {
            Optional<BookDAO> bookDAO = ResourcesManager.getBookDAO();
            if (bookDAO.isPresent()) {
                Optional<Book> book = bookDAO.get().getByTitle("Eden");

                book.ifPresent(bookObj -> System.out.println(bookObj.getGenre()));
            }
        }
    }
}
