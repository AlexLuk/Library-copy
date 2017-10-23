package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.AuthorBook;

import javax.persistence.EntityManagerFactory;

public class AuthorBookDAO extends BaseDAO<AuthorBook>
        implements DAOable<AuthorBook> {

    public AuthorBookDAO(EntityManagerFactory factory ) {
        super( factory );
    }

}
