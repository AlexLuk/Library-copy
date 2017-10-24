package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.BookItem;

import javax.persistence.EntityManagerFactory;

public class BookItemDAO extends BaseDAO<BookItem> implements DAOable<BookItem> {

    public BookItemDAO(EntityManagerFactory factory ) {
        super( factory );
    }
}
