package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Author;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class AuthorDAO extends BaseDAO<Author> implements DAOable<Author> {

    public AuthorDAO(EntityManagerFactory factory ) {
        super( factory );
    }

    /**
     * Gets book by author
     *
     * @param author book author author
     * @return author
     */
    public Optional<List<Author>> getAuthorBySurname(String author) throws PersistenceException
    {
        return getResults(Author.GET_AUTHOR, new String[]{"author"}, new String[]{author});
    }
}
