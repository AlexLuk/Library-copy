package org.library.controllers;

import org.library.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibraryController {
    private final static Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    BookService bookService;

    public LibraryController() {
    }

    @RequestMapping(value = {"/login", "/", "/home"}, method = RequestMethod.GET)
    public String welcome(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been successfully logged out.");
        return "login";
    }

    @RequestMapping(value = {"/account"})
    public String showAccount(Model model) {
        model.addAttribute("book_service", bookService);
        return "account";
    }
}
