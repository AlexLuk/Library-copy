package org.library.services;

import org.library.db.dao.DAOFactory;
import org.library.db.dao.impl.BookDAO;
import org.library.db.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Controller
public class LibraryController {
    DAOFactory _daoFactory = DAOFactory.getInstance();

    public LibraryController() {}

    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        //model.put("message", "Hello");
        return "index";
    }

    /*
    @RequestMapping("/error")
    public String error() {
        return "error404";
    }
    */

    @RequestMapping("/search")
    public String auth() {
        String[] res = new String[1];
        if (_daoFactory.isConnected()) {
            Optional<BookDAO> dao = _daoFactory.getModel(BookDAO.class);
            dao.ifPresent(daoObj -> daoObj.getByTitle("Керри").ifPresent(obj -> {
                for(Book book : obj) {
                    res[0] = book.getTitle() + " - " + book.getGenre().getName() + " - " + book.getIsRare();
                }
            }));
        }
        return Arrays.toString(res);
    }
}
