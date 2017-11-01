package org.library.controllers;

import org.library.db.domain.Reader;
import org.library.db.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LibraryController {
    @Autowired
    ReaderRepository readerRepository;

    public LibraryController() {
    }

/*  @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(Model model, HttpServletResponse response, String error, String logout) {
        System.out.println(response.getStatus());
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
        return "loginForm";
    }
*/
//    @RequestMapping(value = {"/login "})
//    public String login(HttpSession session, Map<String, Object> model) {
//        DefaultSavedRequest savedRequest = (DefaultSavedRequest)
//                session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
//        //System.out.println(savedRequest);
//        model.put("req_url", savedRequest);
//        return "login";
//    }

    @RequestMapping(value = {"/login", "/", "/home"}, method = RequestMethod.GET)
    public String welcome(Model model, HttpServletResponse response, String error, String logout) {
        model.addAttribute("reader",new Reader());
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been successfully logged out.");
        return "login";
    }

    @RequestMapping(value = {"/account"})
    public String readerAcc(Model model, HttpServletResponse response) {
        return "account";
    }

//    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    @PostMapping("/")
    public String registration(@ModelAttribute Reader reader){
        System.out.println("---------------------------");
        System.out.println(reader.getEmail());
        System.out.println(reader.getPassword());
        System.out.println(reader.getName());
        System.out.println(reader.getPatronymic());
        System.out.println(reader.getSurname());
        System.out.println("---------------------------");
        return "/login";
    }
}
