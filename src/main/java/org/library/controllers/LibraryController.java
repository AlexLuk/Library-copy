package org.library.controllers;

import org.library.Application;
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
    private final static Logger logger = LoggerFactory.getLogger(LibraryController.class);

    private static final String READER_URL = "/account/reader/";
    private static final String READER_PATH = "/reader/";
    private static final String ADMIN_URL = "/account/admin/";
    private static final String ADMIN_PATH = "/admin/";

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
    @RequestMapping(value = {"/login", "/", "/home"}, method = RequestMethod.GET)
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
     * Processes requests to reader find page
     *
     * @param model - data model
     * @return - jsp view name
     */
    @RequestMapping(value = {READER_URL + "find"})
    public String showReaderFind(Model model) {
        model.addAttribute("lib_service", libraryService);
        return READER_PATH + "find";
    }

    /**
     * Processes requests to reader delivered orders page
     *
     * @param model - data model
     * @return - jsp view name
     */
    @RequestMapping(value = {READER_URL + "delivered"})
    public String showReaderDeliveredOrders(Model model) {
        model.addAttribute("lib_service", libraryService);
        return READER_PATH + "delivered";
    }

    /**
     * Processes requests to reader wishlist page
     *
     * @param model - data model
     * @return - jsp view name
     */
    @RequestMapping(value = {READER_URL + "wishlist"})
    public String showReaderWishlist(Model model) {
        model.addAttribute("lib_service", libraryService);
        return READER_PATH + "wishlist";
    }

    /**
     * Processes requests to reader profile page
     *
     * @param model - data model
     * @return - jsp view name
     */
    @RequestMapping(value = {READER_URL + "profile"})
    public String showReaderProfile(Model model) {
        model.addAttribute("lib_service", libraryService);
        return READER_PATH + "profile";
    }

    /**
     * Processes requests to admin pending orders page
     *
     * @param model - data model
     * @return - jsp view name
     */
    @RequestMapping(value = {ADMIN_URL + "orders"})
    public String showAdminPendingOrders(Model model) {
        model.addAttribute("lib_service", libraryService);
        return ADMIN_PATH + "orders";
    }

    /**
     * Processes requests to admin delivered orders page
     *
     * @param model - data model
     * @return - jsp view name
     */
    @RequestMapping(value = {ADMIN_URL + "delivered"})
    public String showAdminDeliveredOrders(Model model) {
        model.addAttribute("lib_service", libraryService);
        return ADMIN_PATH + "delivered";
    }

    /**
     * Processes requests to admin all readers page
     *
     * @param model - data model
     * @return - jsp view name
     */
    @RequestMapping(value = {ADMIN_URL + "readers"})
    public String showAdminAllReaders(Model model) {
        model.addAttribute("lib_service", libraryService);
        return ADMIN_PATH + "readers";
    }

    /**
     * Processes requests to admin add books page
     *
     * @param model - data model
     * @return - jsp view name
     */
    @RequestMapping(value = {ADMIN_URL + "add/books"})
    public String showAdminAddBooks(Model model) {
        model.addAttribute("lib_service", libraryService);
        return ADMIN_PATH + "books";
    }

    /**
     * Processes requests to admin add authors page
     *
     * @param model - data model
     * @return - jsp view name
     */
    @RequestMapping(value = {ADMIN_URL + "add/authors"})
    public String showAdminAddAuthors(Model model) {
        model.addAttribute("lib_service", libraryService);
        return ADMIN_PATH + "authors";
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

