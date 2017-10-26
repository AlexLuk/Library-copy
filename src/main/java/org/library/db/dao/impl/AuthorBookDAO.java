package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Author;
import org.library.db.models.AuthorBook;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class AuthorBookDAO extends BaseDAO<AuthorBook> implements DAOable<AuthorBook> {

    public AuthorBookDAO(EntityManagerFactory factory ) {
        super( factory );
    }

    public Optional<Author> getByAuthorId(int authorID) throws PersistenceException
    {
        TypedQuery<Author> namedQuery = getEntityManager()
                .createNamedQuery( "AuthorBook.getByAuthorId", Author.class );
        namedQuery.setParameter( "authorID", authorID );

        try
        {
            return Optional.of(namedQuery.getSingleResult() );
        } catch( Exception ex ){}
        return Optional.empty();
    }

}
