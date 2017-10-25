package org.library;

import org.library.db.dao.DAOFactory;
import org.library.db.dao.impl.*;
import org.library.db.models.*;

import java.util.Optional;

public class Tests {
    public static void main(String[] args) {
        DAOFactory daoFactory = DAOFactory.getInstance();

        if (daoFactory.isConnected()) {
            Optional<BookDAO> dao =
                    (Optional<BookDAO>) daoFactory.getModel(BookDAO.class);
            dao.ifPresent(daoObj -> {
                daoObj.getByTitle("Керри").ifPresent(obj -> {
                    System.out.println(obj.getTitle());
                    System.out.println(obj.getGenre().getName());
                    System.out.println(obj.getIsRare());
                });
            });
        }
        daoFactory.closeConn();
    }
}
