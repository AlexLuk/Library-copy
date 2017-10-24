package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.ItemStatus;

import javax.persistence.EntityManagerFactory;

public class ItemStatusDAO extends BaseDAO<ItemStatus> implements DAOable<ItemStatus> {

    public ItemStatusDAO(EntityManagerFactory factory ) {
        super( factory );
    }
}
