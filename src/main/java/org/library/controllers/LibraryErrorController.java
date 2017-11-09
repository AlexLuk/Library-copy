package org.library.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LibraryErrorController implements ErrorController {
    private final static Logger logger = LoggerFactory.getLogger(LibraryErrorController.class);

    private static final String URL = "/error";
    private static final String JSP_PATH = "/httperrors/errorPage";

    private static final String error404 = "error404";
    private static final String error500 = "error500";

    /**
     * Processes http error statuses
     *
     * @param response - HttpServlet response
     * @return - jsp view name
     */
    @RequestMapping(value = URL)
    public String error(Model model, HttpServletResponse response) {
        logger.warn(response.getStatus() + "");
        if (response.getStatus() == 404) {
            model.addAttribute("errorCode", error404);
        } else {
            model.addAttribute("errorCode", error500);
        }
        return JSP_PATH;
    }

    @Override
    public String getErrorPath() {
        return URL;
    }
}
