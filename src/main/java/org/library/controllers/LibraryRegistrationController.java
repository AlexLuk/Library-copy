package org.library.controllers;

import org.library.db.domain.Reader;
import org.library.db.repo.ReaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LibraryRegistrationController {
    private final static Logger logger = LoggerFactory.getLogger(LibraryErrorController.class);


    public static final String passwordREGEXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?.,/@#$%^&+=])(?=\\S+$).{8,}$";

    @Autowired
    ReaderRepository readerRepository;

    /**Check email for presence in database
     *
     * @param email - email from user input
     * @return true if there is no such mail in database
     */
    @RequestMapping(value = {"/checks/email"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Boolean isEmailUnique(String email) {
        logger.warn(email + " " + readerRepository.findByEmail(email.toLowerCase()).isPresent());
        return !readerRepository.findByEmail(email.toLowerCase()).isPresent();
    }

    public LibraryRegistrationController() {
    }

    /**
     * Check user input for validity based on REGEXP
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $                 # end-of-string
     *
     * login should not be in password in any case
     *
     * @param password password from user input
     * @param email    email from user input
     * @return true if password valid for our
     */
    @RequestMapping(value = {"/checks/password"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Boolean isPasswordComplicate(String password, String email) {
        if (email.length() == 0) return false;
        String userLogin;
        if (email.contains("@")) {
            userLogin = email.substring(0, email.indexOf("@"));
        } else return false;
        if (userLogin.length() == 0) return false;
        return password.matches(passwordREGEXP) && !password.toLowerCase().matches(".*?" + userLogin + ".*?");
    }


    /**
     * Saves reader from user registration form to database
     *
     * @param reader - reader object filled with user input
     * @return - string for RequestMapping
     */
    //    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Boolean registerUser(@ModelAttribute Reader reader) {
        if (isEmailUnique(reader.getEmail()) && isPasswordComplicate(reader.getPassword(),reader.getEmail())){
            reader.setEmail(reader.getEmail().toLowerCase());
            readerRepository.save(reader);
            return true;
        }else {
            return false;
        }
    }
}
