package org.library.controllers;

import org.library.db.domain.Book;
import org.library.db.domain.BookOrder;
import org.library.db.domain.Reader;
import org.library.services.DeliveryService;
import org.library.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LibraryOrderController {

    private final static Logger logger = LoggerFactory.getLogger(LibraryOrderController.class);

    @Autowired
    OrderService orderService;

    @RequestMapping(value = {"/addOrder"}, method = RequestMethod.POST)
    public boolean addOrder(int bookId, boolean toHand) {
        Reader reader = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.addOrder(reader, bookId, toHand);
    }
}
