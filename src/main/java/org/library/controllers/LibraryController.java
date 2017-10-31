package org.library.controllers;

import org.library.db.domain.Reader;
import org.library.db.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class LibraryController {
    @Autowired
    ReaderRepository readerRepository;

    public LibraryController() {}

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/account")
    public String userLogin() {
        return "loginForm";
    }

    @GetMapping("/admin")
    public String adminLogin() {
        return "После входа - кабинет админа";
    }
}
