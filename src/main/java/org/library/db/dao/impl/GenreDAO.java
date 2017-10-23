package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Genre;

import javax.persistence.EntityManagerFactory;

public class GenreDAO extends BaseDAO<Genre> implements DAOable<Genre> {

    public GenreDAO(EntityManagerFactory factory ) {
        super( factory );
    }
}
