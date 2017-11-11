package org.library.controllers;

import org.library.services.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LibraryFilterController {
    private final static Logger logger = LoggerFactory.getLogger(LibraryFilterController.class);

    @Autowired
    LibraryService libraryService;

    @RequestMapping(value = {"/filters"}, method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    String filterBooks(String title, String author, Integer year, Integer genre) {
        //todo adjust ajax for int year and int genre
        //todo convert year to int on client
        return libraryService.jsonBooks(libraryService.getBooksByComplexCondition(title, author, year, genre));
    }
}
