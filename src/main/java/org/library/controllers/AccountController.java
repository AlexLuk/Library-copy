package org.library.controllers;

import org.library.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * Process call for account reader deleting
     *
     * @return 0 - successful delete
     * 1- delete rejected, user is admin
     * 2- delete rejected, user has fines
     * 3- delete rejected, user has orders on hand
     * 4- delete rejected, user has deliveries on hand
     * 5- delete rejected, delete error
     */
    @RequestMapping(value = {"/deleteReader"}, method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    int deleteReader(int readerId) {
        return accountService.deleteReaderById(readerId);
    }
}
