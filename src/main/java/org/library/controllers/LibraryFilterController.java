package org.library.controllers;

import org.library.db.domain.Book;
import org.library.services.FilteringService;
import org.library.services.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class LibraryFilterController {
    private final static Logger logger = LoggerFactory.getLogger(LibraryFilterController.class);
    @Autowired
    LibraryService libraryService;

    @RequestMapping(value = {"/filters"}, method = RequestMethod.POST)
    public String filterBooks(String title, String author, String year, String genre) {
        //todo year int check on client
        //todo year = null if year = "" on client
        try {
            Integer intYear = Integer.parseInt(year);
            return libraryService.jsonBooks(libraryService.getBooksByComplexCondition(title, author, intYear, genre));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return libraryService.jsonBooks(libraryService.getBooksByComplexCondition(title, author, null, genre));
    }
}
