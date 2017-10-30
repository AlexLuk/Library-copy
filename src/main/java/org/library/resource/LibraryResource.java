package org.library.resource;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class LibraryResource {
    @GetMapping("/")
    public String hello() {
        return "Главная страница";
    }

    @GetMapping("/account")
    public String userLogin() {
        return "После входа - личный кабинет";
    }

    @GetMapping("/admin")
    public String adminLogin() {
        return "После входа - кабинет админа";
    }
}
