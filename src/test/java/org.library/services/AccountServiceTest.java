package org.library.services;

import cucumber.api.java.en_old.Ac;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AccountServiceTest extends LibraryTest{

    @Autowired
    AccountService accountService;

    @Test
    public void deleteReaderById() throws Exception {
        saveTestData();
        assertThat(accountService.deleteReaderById(110),is(0));
        assertThat(accountService.deleteReaderById(1),is(1));
        assertThat(accountService.deleteReaderById(10),is(2));
        assertThat(accountService.deleteReaderById(2),is(3));
        assertThat(accountService.deleteReaderById(3),is(4));
        assertThat(accountService.deleteReaderById(9999),is(5));
    }
}