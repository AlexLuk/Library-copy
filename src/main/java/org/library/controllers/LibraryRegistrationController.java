package org.library.controllers;

import org.library.db.domain.Reader;
import org.library.db.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LibraryRegistrationController {

    public static final String passwordREGEXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?.,/@#$%^&+=])(?=\\S+$).{8,}$";
//    ^                 # start-of-string
//            (?=.*[0-9])       # a digit must occur at least once
//            (?=.*[a-z])       # a lower case letter must occur at least once
//            (?=.*[A-Z])       # an upper case letter must occur at least once
//            (?=.*[@#$%^&+=])  # a special character must occur at least once
//            (?=\S+$)          # no whitespace allowed in the entire string
//            .{8,}             # anything, at least eight places though
//    $                 # end-of-string

    public LibraryRegistrationController() {
    }

    @Autowired
    ReaderRepository readerRepository;

    @RequestMapping(value = {"/checks/email"}, method = RequestMethod.POST)
    public boolean isEmailUnique(String email) {
        return !readerRepository.findByEmail(email.toLowerCase()).isPresent();
    }

    @RequestMapping(value = {"/checks/password"}, method = RequestMethod.POST)
    public boolean isPasswordComplicate(String password, String email) {
        if (email.length() == 0) return false;
        String userLogin;
        if (email.contains("@")){
            userLogin = email.substring(0, email.indexOf("@"));
        }else return false;
        if (userLogin.length() == 0) return false;
        return password.matches(passwordREGEXP) && !password.matches(".*?" + userLogin + ".*?");
    }


    //    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute Reader reader) {
        reader.setEmail(reader.getEmail().toLowerCase());
        readerRepository.save(reader);
        return "";
    }
}


