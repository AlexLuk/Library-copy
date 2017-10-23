package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Delivery;

import javax.persistence.EntityManagerFactory;

public class DeliveryDAO extends BaseDAO<Delivery> implements DAOable<Delivery> {

    public DeliveryDAO(EntityManagerFactory factory ) {
        super( factory );
    }
}
