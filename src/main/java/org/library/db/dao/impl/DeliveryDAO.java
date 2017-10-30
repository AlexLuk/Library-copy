package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Book;
import org.library.db.models.Delivery;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class DeliveryDAO extends BaseDAO<Delivery> implements DAOable<Delivery> {

    public DeliveryDAO(EntityManagerFactory factory ) {
        super( factory );
    }

    /**Get list of deliveries for specific user
     * and of specific item status
     * @param readerID - specific user id
     * @return list of user deliverise;
     * @throws PersistenceException
     */
    public Optional<List<Delivery>> getDeliveryByReaderAndStatus(int readerID, String status) throws PersistenceException
    {
        Optional<List<Delivery>> deliveries = null;

        return deliveries;
    }

}
