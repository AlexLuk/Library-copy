package org.library.services;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountServiceTest extends LibraryTest{

    @Autowired
    AccountService accountService;


    @Test
    public void deleteReaderById() throws Exception {
        assertThat(accountService.deleteReaderById(110),is(accountService.SUCC_ACCOUNT_DELETED));
        assertThat(accountService.deleteReaderById(110),is(accountService.ERR_DELETE_ACCOUNT));
        assertThat(accountService.deleteReaderById(1),is(accountService.ERR_DELETE_ACCOUNT_ADMIN));
        assertThat(accountService.deleteReaderById(10),is(accountService.ERR_DELETE_ACCOUNT_FINES));
        assertThat(accountService.deleteReaderById(2),is(accountService.ERR_DELETE_ACCOUNT_ORDER));
        assertThat(accountService.deleteReaderById(3),is(accountService.ERR_DELETE_ACCOUNT_DELIVERY));
        assertThat(accountService.deleteReaderById(9999),is(accountService.ERR_DELETE_ACCOUNT));
    }

    @Test
    public void isPasswordComplicate() throws Exception {
        assertThat(accountService.isPasswordComplicate("", "login@mail.ru"), CoreMatchers.is(false));
        assertThat(accountService.isPasswordComplicate("", "@mail.ru"), CoreMatchers.is(false));
        assertThat(accountService.isPasswordComplicate("", "mail.ru"), CoreMatchers.is(false));
        //spaces
        assertThat(accountService.isPasswordComplicate("1qQ31 qQ3a@", "login@mail.ru"), CoreMatchers.is(false));
        //lenght < 8
        assertThat(accountService.isPasswordComplicate("1qQ31qQ", "login@mail.ru"), CoreMatchers.is(false));
        // only digits
        assertThat(accountService.isPasswordComplicate("12345678", "login@mail.ru"), CoreMatchers.is(false));
        // only chars
        assertThat(accountService.isPasswordComplicate("abcdefgh", "login@mail.ru"), CoreMatchers.is(false));
        assertThat(accountService.isPasswordComplicate("ABCDEFGH", "login@mail.ru"), CoreMatchers.is(false));
        // only specials
        assertThat(accountService.isPasswordComplicate("@@#$#%*", "login@mail.ru"), CoreMatchers.is(false));
        //no specials
        assertThat(accountService.isPasswordComplicate("12345qwerQWER", "login@mail.ru"), CoreMatchers.is(false));
        //no lover chars
        assertThat(accountService.isPasswordComplicate("1234!@#$ABC", "login@mail.ru"), CoreMatchers.is(false));
        //no upper chars
        assertThat(accountService.isPasswordComplicate("1234!@#$abc", "login@mail.ru"), CoreMatchers.is(false));
        //no digits
        assertThat(accountService.isPasswordComplicate("ABCabc@@@", "login@mail.ru"), CoreMatchers.is(false));
        // valid
        assertThat(accountService.isPasswordComplicate("1qQ31qQ3a@", "login@mail.ru"), CoreMatchers.is(true));

        assertThat(accountService.isPasswordComplicate("@1qQ31qQ3a@", "login@mail.ru"), CoreMatchers.is(true));
    }

    @Test
    public void isLoginInPassword() throws Exception {
        assertThat(accountService.isPasswordComplicate("1qQ31qQ3a@login", "login@mail.ru"), CoreMatchers.is(false));
        assertThat(accountService.isPasswordComplicate("1qQ31loginqQ3a@", "login@mail.ru"), CoreMatchers.is(false));
        assertThat(accountService.isPasswordComplicate("login1qQ31qQ3a@", "login@mail.ru"), CoreMatchers.is(false));
        assertThat(accountService.isPasswordComplicate("LOGIN1qQ31qQ3a@", "login@mail.ru"), CoreMatchers.is(false));
        assertThat(accountService.isPasswordComplicate("Login1qQ31qQ3a@", "login@mail.ru"), CoreMatchers.is(false));
        assertThat(accountService.isPasswordComplicate("1qQ31qQ3a@logiN", "login@mail.ru"), CoreMatchers.is(false));
        assertThat(accountService.isPasswordComplicate("1qQ31logiNqQ3a@", "login@mail.ru"), CoreMatchers.is(false));
        assertThat(accountService.isPasswordComplicate("login1qQ31qQ3a@", "login@mail.ru"), CoreMatchers.is(false));
    }
}