package org.library.controllers;

import org.library.db.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LibraryController {
    @Autowired
    ReaderRepository readerRepository;

    public LibraryController() {}

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
        return "loginForm";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String welcome() {
        return "index";
    }

    @RequestMapping(value = {"/readerAcc"}, method = RequestMethod.GET)
    public String readerAcc(Model model, HttpServletResponse response) {
        return "reader";
    }

    @RequestMapping(value = {"/adminAcc"}, method = RequestMethod.GET)
    public String reader(Model model) {
        return "admin";
    }
}
