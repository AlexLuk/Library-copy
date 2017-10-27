package org.library.services;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryController {
    public LibraryController() {}

    @RequestMapping("/")
    public String echo () {
        return "Hello World!";
    }
}
