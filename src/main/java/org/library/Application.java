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
    private static final String READER_URL = "/account/reader/";
    private static final String READER_PATH = "/reader/";
    private static final String ADMIN_URL = "/account/admin/";
    private static final String ADMIN_PATH = "/admin/";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addViewControllers (ViewControllerRegistry registry) {
        registry.addViewController(READER_URL + "find").setViewName(READER_PATH + "find");
        registry.addViewController(READER_URL + "delivered").setViewName(READER_PATH + "delivered");
        registry.addViewController(READER_URL + "wishlist").setViewName(READER_PATH + "wishlist");
        registry.addViewController(READER_URL + "profile").setViewName(READER_PATH + "profile");

        registry.addViewController(ADMIN_URL + "orders").setViewName(ADMIN_PATH + "orders");
        registry.addViewController(ADMIN_URL + "delivered").setViewName(ADMIN_PATH + "delivered");
        registry.addViewController(ADMIN_URL + "readers").setViewName(ADMIN_PATH + "readers");
        registry.addViewController(ADMIN_URL + "add/books").setViewName(ADMIN_PATH + "books");
        registry.addViewController(ADMIN_URL + "add/authors").setViewName(ADMIN_PATH + "authors");
    }
}
