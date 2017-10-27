package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.*;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class BookDAO extends BaseDAO<Book> implements DAOable<Book> {

    public BookDAO(EntityManagerFactory factory ) {
        super( factory );
    }

    /**
     * Gets book by title
     *
     * @param title book title
     * @return book
     */
    public Optional<List<Book>> getByTitle(String title) throws PersistenceException
    {
        return getResults("Book.getByTitle", new String[]{"title"}, new String[]{title});
    }
}
