package org.library.controllers;

import org.library.db.domain.Reader;
import org.library.db.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LibraryRegistrationController {
    public LibraryRegistrationController() {
    }

    @Autowired
    ReaderRepository readerRepository;

    @RequestMapping(value = {"/checks/email"}, method = RequestMethod.POST)
    public boolean isEmailUnique(@ModelAttribute Reader reader) {
        if (readerRepository.findByEmail(reader.getEmail().toLowerCase()).isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping(value = {"/checks/email"}, method = RequestMethod.POST)
    public boolean isPasswordCommplicate(String password) {
        return false;
    }

//    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute Reader reader) {
        reader.setEmail(reader.getEmail().toLowerCase());
        readerRepository.save(reader);
        return "";
    }
}


