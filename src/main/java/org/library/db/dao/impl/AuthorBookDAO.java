package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.*;
import javax.persistence.*;
import java.util.Optional;

public class AuthorBookDAO extends BaseDAO<AuthorBook> implements DAOable<AuthorBook> {

    public AuthorBookDAO(EntityManagerFactory factory ) {
        super( factory );
    }

    public Optional<Author> getByAuthorId(int authorID) throws PersistenceException
    {
        return getSingleResult("AuthorBook.getByAuthorId", new String[]{"authorID"}, new Integer[]{authorID});
    }
}
