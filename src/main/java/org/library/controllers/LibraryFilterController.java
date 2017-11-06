package org.library.controllers;

import org.library.db.domain.Book;
import org.library.services.FilteringService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class LibraryFilterController {


    @RequestMapping(value = {"/filters"}, method = RequestMethod.POST)
    public List<Book> filterBooks(String title, String author, String year, String genre) {
        return new FilteringService().getBooksByComplexCondition(title, author, year, genre);
    }

}
