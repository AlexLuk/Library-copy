package org.library.controllers;

import org.library.db.domain.BookOrder;
import org.library.services.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LibraryDeliveryController {

    private final static Logger logger = LoggerFactory.getLogger(LibraryFilterController.class);

    @Autowired
    DeliveryService deliveryService;

    @RequestMapping(value = {"/addDelivery"}, method = RequestMethod.POST)
    public boolean addDelivery(BookOrder order, boolean toHand) {
        deliveryService.addDeliveryByBookOrder(order, toHand);
        return true;
    }
}
