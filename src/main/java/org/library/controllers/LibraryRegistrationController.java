package org.library.controllers;

import org.apache.commons.codec.digest.DigestUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Controller
public class LibraryRegistrationController {
    private final static Logger logger = LoggerFactory.getLogger(LibraryRegistrationController.class);


    public static final String passwordREGEXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?.,/@#$%^&+=])(?=\\S+$).{8,}$";

    @Autowired
    ReaderRepository readerRepository;


    /**
     * Check email for presence in database
     *
     * @param email - email from user input
     * @return true if there is no such mail in database
     */
    @RequestMapping(value = {"/checks/email"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Boolean isEmailUnique(String email) {
        logger.warn(email);
        return !readerRepository.findByEmail(email.toLowerCase()).isPresent();
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
     * login should not be in password in any case
     *
     * @param password password from user input
     * @param email    email from user input
     * @return true if password valid for our
     */
    @RequestMapping(value = {"/checks/password"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Boolean isPasswordComplicate(String password, String email) { //todo name
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
     * @param reader  - reader object filled with user input
     * @param request
     * @return - string for RequestMapping
     */
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Boolean registerUser(@ModelAttribute Reader reader, HttpServletRequest request) {
        String password = reader.getPassword();
        String email = reader.getEmail();

        if (isEmailUnique(email) && isPasswordComplicate(password, email)) {
            reader.setEmail(email.toLowerCase());
            reader.setPassword(DigestUtils.md5Hex(password));
            reader.setFines(0.0);
            reader.setIsAdmin(false);
            reader.setRegistrationDate(new Timestamp(System.currentTimeMillis()));

            readerRepository.save(reader);

            return loginInSystem(request, password, email);
        } else {
            return false;
        }
    }

    private boolean loginInSystem(HttpServletRequest request, String password, String email) {
        try {
            request.getSession();
            request.login(email, password);
        } catch (Exception se) {
            logger.warn("Authorization error " + se);
            return false;
        }
        return true;
    }
}
