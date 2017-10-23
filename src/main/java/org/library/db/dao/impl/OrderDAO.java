package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Order;

import javax.persistence.EntityManagerFactory;

public class OrderDAO extends BaseDAO<Order> implements DAOable<Order> {

    public OrderDAO(EntityManagerFactory factory ) {
        super( factory );
    }
}
