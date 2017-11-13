package org.library.controllers;

import org.library.services.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LibraryDeliveryController {


    @Autowired
    DeliveryService deliveryService;

    /**
     * Add book to current user deliveries
     *
     * @param orderId - id of order
     * @return - false, if it was not possible to add delivery
     */
    @RequestMapping(value = {"/addDelivery"}, method = RequestMethod.POST, produces = "application/json")
    public boolean addDelivery(Integer orderId) {
        return deliveryService.addDeliveryByBookOrder(orderId);
    }
}
