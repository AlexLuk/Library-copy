package org.library.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.library.db.domain.Reader;
import org.library.db.repo.ReaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    //todo move logic to accountService

    /**
     * Check email for presence in database
     *
     * @param email - email from user input
     * @return true if there is no such mail in database
     */
    @RequestMapping(value = {"/checks/email"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Boolean isEmailUnique(String email) {
        return !readerRepository.findByEmail(email.toLowerCase()).isPresent();
    }

    //todo move logic to accountService

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
        if (email.length() == 0) {
            return false;
        }
        String userLogin;
        if (email.contains("@")) {
            userLogin = email.substring(0, email.indexOf("@"));
        } else {
            return false;
        }
        if (userLogin.length() == 0) {
            return false;
        }
        return password.matches(passwordREGEXP) && !password.toLowerCase().matches(".*?" + userLogin + ".*?");
    }

    //todo move logic to accountService

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

    /**
     * "/checks/oldpassword"}
     *
     * @param oldPassword - passwoar which need to be checkes as existing in the database for current user
     * @return if password is right
     */
    @RequestMapping(value = {"/checks/oldpassword"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    boolean checkOldPassword(String oldPassword) {
        Reader curReader = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return curReader.getPassword().equals(DigestUtils.md5Hex(oldPassword));
    }

    //todo move logic to accountService

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
        Reader curReader = (Reader) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String newPassword = reader.getPassword();
        try {
            if (newPassword.equals("")) {
                setFields(reader, curReader);
                return 0;
            }
            if (isPasswordComplicate(newPassword, curReader.getEmail())) {
                if (checkOldPassword(oldPassword)) {
                    curReader.setPassword(DigestUtils.md5Hex(newPassword));
                    setFields(reader, curReader);
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 2;
            }
        } catch (Exception e) {
            return 3;
        }
    }

    private void setFields(@ModelAttribute Reader reader, Reader curReader) {
        curReader.setLastName(reader.getLastName());
        curReader.setFirstName(reader.getFirstName());
        curReader.setPatronymic(reader.getPatronymic());
        readerRepository.save(new Reader(curReader));
    }

    @RequestMapping(value = {"/setFines"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    boolean setFines(int readerId, double fines) {
        System.out.println("setFines");
        System.out.println(readerId);
        System.out.println(fines);
        try {
            Reader reader = readerRepository.getOne(readerId);
            reader.setFines(fines);
            readerRepository.save(reader);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
