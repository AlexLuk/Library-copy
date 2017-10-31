package org.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LibraryErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error(HttpServletResponse response) {
        System.out.println(response.getStatus());
        if (response.getStatus() == 404) {
            return "/errorPages/error404";
        }
        else if (response.getStatus() == 403) {
            return "/errorPages/error403";
        }
        else return "/errorPages/error500";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
