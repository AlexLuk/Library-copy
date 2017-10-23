package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Reader;

import javax.persistence.EntityManagerFactory;

public class ReaderDAO extends BaseDAO<Reader> implements DAOable<Reader> {

    public ReaderDAO(EntityManagerFactory factory ) {
        super( factory );
    }
}
