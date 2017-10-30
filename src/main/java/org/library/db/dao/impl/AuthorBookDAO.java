package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.AuthorBook;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.util.Optional;

public class AuthorBookDAO extends BaseDAO<AuthorBook> implements DAOable<AuthorBook> {

    public AuthorBookDAO(EntityManagerFactory factory ) {
        super( factory );
    }

    /**
     * Gets AuthorBook by authorID
     *
     * @param authorID author's ID
     * @return AuthorBook
     */
    public Optional<AuthorBook> getByAuthorId(int authorID) throws PersistenceException
    {
        return getSingleResult(AuthorBook.GET_AUTHOR_ID, new String[]{"authorID"}, new Integer[]{authorID});
    }
}
