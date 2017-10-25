package org.library;

import org.library.db.dao.DAOFactory;
import org.library.db.dao.impl.*;
import org.library.db.models.*;

import java.util.Optional;

public class Tests {
    public static void main(String[] args) {
        DAOFactory daoFactory = DAOFactory.getInstance();

        if (daoFactory.isConnected()) {
            Optional<ReaderDAO> dao =
                    (Optional<ReaderDAO>) daoFactory.getModel(ReaderDAO.class);
            dao.ifPresent(daoObj -> {
                System.out.println(daoObj.getById(2, Reader.class).getName());
                /*daoObj.getByTitle("Керри").ifPresent(obj -> {
                    System.out.println(obj.getTitle());
                    System.out.println(obj.getGenre().getName());
                    System.out.println(obj.getIsRare());
                });
                */
            });
        }
        daoFactory.closeConn();
    }
}
