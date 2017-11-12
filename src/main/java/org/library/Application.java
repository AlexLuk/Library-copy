package org.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackages = "org.library")
@EnableJpaRepositories(basePackages = "org.library.db.repo")
@EntityScan(basePackages = "org.library.db.domain")
public class Application extends WebMvcConfigurerAdapter {
    private static final String READER = "/reader/";
    private static final String ADMIN = "/admin/";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addViewControllers (ViewControllerRegistry registry) {
        registry.addViewController(READER + "find").setViewName(READER + "find");
        registry.addViewController(READER + "delivered").setViewName(READER + "delivered");
        registry.addViewController(READER + "wishlist").setViewName(READER + "wishlist");
        registry.addViewController(READER + "profile").setViewName(READER + "profile");

        registry.addViewController(ADMIN + "orders").setViewName(ADMIN + "orders");
        registry.addViewController(ADMIN + "delivered").setViewName(ADMIN + "delivered");
        registry.addViewController(ADMIN + "readers").setViewName(ADMIN + "readers");
        registry.addViewController(ADMIN + "add/books").setViewName(ADMIN + "books");
        registry.addViewController(ADMIN + "add/authors").setViewName(ADMIN + "authors");
    }
}
