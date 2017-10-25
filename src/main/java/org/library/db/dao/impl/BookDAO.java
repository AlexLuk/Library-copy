package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Book;

import javax.persistence.*;
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
    public Optional<Book> getByTitle(String title) throws PersistenceException
    {
        TypedQuery<Book> namedQuery = getEntityManager()
                .createNamedQuery( "Book.getByTitle", Book.class );
        namedQuery.setParameter( "title", title );

        try
        {
            return Optional.of(namedQuery.getSingleResult() );
        } catch( Exception ex ){}
        return Optional.empty();
    }
}
