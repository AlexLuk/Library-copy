package org.library.services;

import org.library.db.dao.DAOFactory;
import org.library.db.dao.impl.BookDAO;
import org.library.db.models.Book;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

//@RestController
//public class LibraryController {
//    DAOFactory _daoFactory = DAOFactory.getInstance();
//
//    public LibraryController() {
//    }
//
//    @RequestMapping("/")
//    public String echo() {
//        String[] res = new String[1];
//        if (_daoFactory.isConnected()) {
//            Optional<BookDAO> dao = _daoFactory.getModel(BookDAO.class);
//            dao.ifPresent(daoObj -> {
//                daoObj.getByTitle("Керри").ifPresent(obj -> {
//                    for (Book book : obj) {
//                        res[0] = book.getTitle() + " - " + book.getGenre().getName() + " - " + book.getIsRare();
//                    }
//                });
//            });
//        }
//        return Arrays.toString(res);
//    }
//}
