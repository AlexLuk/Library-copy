package org.library.services;

import org.library.db.dao.DAOFactory;
import org.library.db.dao.impl.BookDAO;
import org.library.db.models.Book;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController
public class LibraryController {
    public LibraryController() {}

    @RequestMapping("/")
    public String echo () {
        DAOFactory daoFactory = DAOFactory.getInstance();

        String[] res = new String[3];
        if (daoFactory.isConnected()) {
            Optional<BookDAO> dao = daoFactory.getModel(BookDAO.class);
            dao.ifPresent(daoObj -> {
                daoObj.getByTitle("Керри").ifPresent(obj -> {
                    for(Book book : obj) {
                        System.out.println(book.getTitle());
                        res[0] = book.getTitle();
                        System.out.println(book.getGenre().getName());
                        res[1] = book.getGenre().getName();
                        System.out.println(book.getIsRare());
                        res[2] = "" + book.getIsRare();
                    }
                });
            });
        }
        daoFactory.closeConn();

        return Arrays.toString(res);
    }
}
