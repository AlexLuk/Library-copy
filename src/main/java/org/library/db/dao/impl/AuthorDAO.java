package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Author;

import javax.persistence.EntityManagerFactory;

public class AuthorDAO extends BaseDAO<Author> implements DAOable<Author> {

    public AuthorDAO(EntityManagerFactory factory ) {
        super( factory );
    }
}
