package org.library.controllers;

import org.library.services.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    public LibraryController() {
    }

    /**
     * Processes requests to login, home and root pages
     *
     * @param model  - data model
     * @param error  - error attribute in the url
     * @param logout - logout attribute in the url
     * @return - jsp view name
     */
    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String welcome(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been successfully logged out.");
        return "login";
    }

    /**
     * Processes requests to account page
     *
     * @param model - data model
     * @return - jsp view name
     */
    @RequestMapping(value = {"/account"})
    public String showAccount(Model model) {
        model.addAttribute("lib_service", libraryService);
        return "account";
    }

    /**
     * Process call for account deleting
     *
     * @return true if account is deleted successfully
     */
    @RequestMapping(value = {"/deleteAccount"}, method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    //todo test work with interface
    boolean deleteAccount() {
        return libraryService.deleteAccount();
    }

    /**
     * Process call for account reader deleting
     *
     * @return true if account is deleted successfully
     */
    @RequestMapping(value = {"/deleteReader"}, method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    boolean deleteReader(int readerId) {
        return libraryService.deleteReaderById(readerId);
    }
}

