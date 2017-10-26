package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Author;
import org.library.db.models.AuthorBook;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class AuthorDAO extends BaseDAO<Author> implements DAOable<Author> {

    public AuthorDAO(EntityManagerFactory factory ) {
        super( factory );
    }

    /**
     * Gets book by author
     *
     * @param author book author
     * @return book
     */
    public Optional<Author> getAuthor(String author) throws PersistenceException
    {
        TypedQuery<Author> namedQuery = getEntityManager()
                .createNamedQuery( "Author.getAuthor", Author.class );
        namedQuery.setParameter( "author", author );


        try
        {
            return Optional.of(namedQuery.getSingleResult() );
        } catch( Exception ex ){}
        return Optional.empty();
    }
}
