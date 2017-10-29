package org.library.resource;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/library")
@RestController
public class LibraryResource {
    @GetMapping("/all")
    public String hello() {
        return "Главная страница";
    }

    //    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/secured/all")
    public String securedHello() {
        return "После входа - личный кабинет";
    }
}
