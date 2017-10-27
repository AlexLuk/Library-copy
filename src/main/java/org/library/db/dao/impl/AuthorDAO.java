package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.*;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class AuthorDAO extends BaseDAO<Author> implements DAOable<Author> {

    public AuthorDAO(EntityManagerFactory factory ) {
        super( factory );
    }

    /**
     * Gets book by author
     *
     * @param surname book author surname
     * @return book
     */
    public Optional<List<Author>> getAuthorBySurname(String surname) throws PersistenceException
    {
        return getResults("Author.getAuthor", new String[]{"surname"}, new String[]{surname});
    }
}
