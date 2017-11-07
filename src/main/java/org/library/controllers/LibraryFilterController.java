package org.library.controllers;

import org.library.db.domain.Book;
import org.library.services.FilteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class LibraryFilterController {
    @Autowired FilteringService filteringService;

    @RequestMapping(value = {"/filters"}, method = RequestMethod.POST)
    public List<Book> filterBooks(String title, String author, String year, String genre) {
//todo year int проверка на клиенте
       return filteringService.getBooksByComplexCondition(title, author, year, genre);
    }

}
