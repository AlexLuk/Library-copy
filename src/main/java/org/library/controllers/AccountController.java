package org.library.controllers;

import org.library.db.domain.Reader;
import org.library.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * Saves reader from user registration form to database
     *
     * @param reader  - reader object filled with user input
     * @param request
     * @return - string for RequestMapping
     */
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    boolean registerUser(@ModelAttribute Reader reader, HttpServletRequest request) {
        return accountService.registerUser(reader, request);
    }

    /**
     * Check email for presence in database
     *
     * @param email - email from user input
     * @return true if there is no such mail in database
     */
    @RequestMapping(value = {"/checks/email"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Boolean isEmailUnique(String email) {
        return accountService.isEmailUnique(email);
    }

    /**
     * Check user input for validity
     *
     * @param password password from user input
     * @param email    email from user input
     * @return true if password valid for our
     */
    @RequestMapping(value = {"/checks/password"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    boolean isPasswordComplicate(String password, String email) {
        return accountService.isPasswordComplicate(password, email);
    }

    /**
     * Change reader info in database for the the info from user profile form
     *
     * @param reader - reader object filled with user input
     * @return - 0 - success, 1 - failure because old password is wrong, 2 - failure because email is in password
     * 3 - exception in process
     */
    @RequestMapping(value = {"/change_profile"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    int changeUser(@ModelAttribute Reader reader, String oldPassword) {
        return accountService.changeReader(reader, oldPassword);
    }

    /**
     * Checks if user insert right current password
     *
     * @param oldPassword - password which need to be checked as existing in the database for current user
     * @return true if password is right
     */
    @RequestMapping(value = {"/checks/oldpassword"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    boolean checkOldPassword(String oldPassword) {
        return accountService.checkOldPassword(oldPassword);
    }

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

    /**
     * Change fines for selected user in the database
     *
     * @param readerId - id of user which need to be modified
     * @param fines    - amount of fines to change
     * @return true if operation was successful
     */
    @RequestMapping(value = {"/setFines"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    boolean setFines(int readerId, double fines) {
        return accountService.setFines(readerId, fines);
    }
}
