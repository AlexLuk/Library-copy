package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.BookOrder;

import javax.persistence.EntityManagerFactory;

public class BookOrderDAO extends BaseDAO<BookOrder> implements DAOable<BookOrder> {

    public BookOrderDAO(EntityManagerFactory factory ) {
        super( factory );
    }
}
