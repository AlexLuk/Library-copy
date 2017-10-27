package org.library;

import org.library.db.dao.DAOFactory;
import org.library.db.dao.impl.*;
import org.library.db.models.*;

import java.util.Optional;

public class Tests {
    public static void main(String[] args) {
        DAOFactory daoFactory = DAOFactory.getInstance();

        if (daoFactory.isConnected()) {
            Optional<BookDAO> dao = daoFactory.getModel(BookDAO.class);
            dao.ifPresent(daoObj -> {
                daoObj.getByTitle("Керри").ifPresent(obj -> {
                    for(Book book : obj) {
                        System.out.println(book.getTitle());
                        System.out.println(book.getGenre().getName());
                        System.out.println(book.getIsRare());
                    }
                });
            });
        }
        daoFactory.closeConn();
    }
}
