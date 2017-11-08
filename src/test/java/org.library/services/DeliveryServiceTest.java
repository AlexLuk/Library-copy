package org.library.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeliveryServiceTest extends LibraryTest {

    @Autowired
    DeliveryService deliveryService;

    @Test
    public void freeBook() throws Exception {

    }

    @Test
    public void addDeliveryByBookOrder() throws Exception {

    }

    @Test
    public void addDelivery() throws Exception {

    }

}