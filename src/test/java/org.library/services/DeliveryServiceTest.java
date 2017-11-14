package org.library.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeliveryServiceTest extends LibraryTest {
    @Test
    public void returnDelivery() throws Exception {
        assertThat(deliveryService.returnDelivery(1),is(true));
        assertThat(bookItemRepository.getOne(2).getStatus(),is(itemStatusRepository.getOne(1)));
        assertThat(deliveryService.returnDelivery(5555),is(false));
    }

    @Test
    public void getDeliveriesByComplexCondition() throws Exception {

    }

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