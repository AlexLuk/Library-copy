package org.library.controllers;

import org.library.db.domain.Reader;
import org.library.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LibraryOrderController {


    @Autowired
    OrderService orderService;

    /**
     * Add book to current user orders
     *
     * @param bookId - id off book to add
     * @param toHand - true if book is ordered on hand
     * @return - return 0, if it was not possible to add book to order book
     */
    @RequestMapping(value = {"/addOrder"}, method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    int addOrder(int bookId, boolean toHand) {
        Reader reader = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.addOrder(reader, bookId, toHand);
    }

    /**
     * Cancel order by orderId
     *
     * @param bookOrderId - order id
     * @return - true if order deleted
     */
    @RequestMapping(value = {"/cancelOrder"}, method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    boolean cancelOrder(int bookOrderId) {
        return orderService.cancelOrder(bookOrderId);
    }

}
