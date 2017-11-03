package org.library.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.library.db.domain.Reader;
import org.library.db.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LibraryRegistrationControllerTest {
    LibraryRegistrationController libraryRegistrationController;

    @Before
    public void setTests() {
        libraryRegistrationController = new LibraryRegistrationController();
    }

    @Test
    public void isPasswordComplicate() throws Exception {
        assertThat(libraryRegistrationController.isPasswordComplicate("", "login@mail.ru"), is(false));
        assertThat(libraryRegistrationController.isPasswordComplicate("", "@mail.ru"), is(false));
        assertThat(libraryRegistrationController.isPasswordComplicate("", "mail.ru"), is(false));
        //spaces
        assertThat(libraryRegistrationController.isPasswordComplicate("1qQ31 qQ3a@", "login@mail.ru"), is(false));
        //lenght < 8
        assertThat(libraryRegistrationController.isPasswordComplicate("1qQ31qQ", "login@mail.ru"), is(false));
        // only digits
        assertThat(libraryRegistrationController.isPasswordComplicate("12345678", "login@mail.ru"), is(false));
        // only chars
        assertThat(libraryRegistrationController.isPasswordComplicate("abcdefgh", "login@mail.ru"), is(false));
        assertThat(libraryRegistrationController.isPasswordComplicate("ABCDEFGH", "login@mail.ru"), is(false));
        // only specials
        assertThat(libraryRegistrationController.isPasswordComplicate("@@#$#%*", "login@mail.ru"), is(false));
        //no specials
        assertThat(libraryRegistrationController.isPasswordComplicate("12345qwerQWER", "login@mail.ru"), is(false));
        //no lover chars
        assertThat(libraryRegistrationController.isPasswordComplicate("1234!@#$ABC", "login@mail.ru"), is(false));
        //no upper chars
        assertThat(libraryRegistrationController.isPasswordComplicate("1234!@#$abc", "login@mail.ru"), is(false));
        //no digits
        assertThat(libraryRegistrationController.isPasswordComplicate("ABCabc@@@", "login@mail.ru"), is(false));
        // valid
        assertThat(libraryRegistrationController.isPasswordComplicate("1qQ31qQ3a@", "login@mail.ru"), is(true));
    }

    @Test
    public void isLoginInPassword() throws Exception {
        assertThat(libraryRegistrationController.isPasswordComplicate("1qQ31qQ3a@login", "login@mail.ru"), is(false));
        assertThat(libraryRegistrationController.isPasswordComplicate("1qQ31loginqQ3a@", "login@mail.ru"), is(false));
        assertThat(libraryRegistrationController.isPasswordComplicate("login1qQ31qQ3a@", "login@mail.ru"), is(false));
        assertThat(libraryRegistrationController.isPasswordComplicate("1qQ31qQ3a@logiN", "login@mail.ru"), is(true));
        assertThat(libraryRegistrationController.isPasswordComplicate("1qQ31logiNqQ3a@", "login@mail.ru"), is(true));
        assertThat(libraryRegistrationController.isPasswordComplicate("logiN1qQ31qQ3a@", "login@mail.ru"), is(true));
    }
}