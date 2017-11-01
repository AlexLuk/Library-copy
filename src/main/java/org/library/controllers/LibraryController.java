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

/*    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(Model model, HttpServletResponse response, String error, String logout) {
        System.out.println(response.getStatus());
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
        return "loginForm";
    }
*/
    @RequestMapping(value = {"/login"})
    public String login() {
        return "loginForm";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String welcome() {
        return "index";
    }

    @RequestMapping(value = {"/reader"})
    public String readerAcc(Model model, HttpServletResponse response) {
        return "reader";
    }

    @RequestMapping(value = {"/admin"})
    public String reader(Model model) {
        return "admin";
    }
}
