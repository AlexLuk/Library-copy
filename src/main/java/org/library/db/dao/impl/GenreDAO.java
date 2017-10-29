package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Genre;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class GenreDAO extends BaseDAO<Genre> implements DAOable<Genre> {

    public GenreDAO(EntityManagerFactory factory ) {
        super( factory );
    }

    /**
     * Gets book by genre
     *
     * @param genre book's genre
     * @return genre
     */
    public Optional<List<Genre>> getGenre(String genre) throws PersistenceException
    {
        return getResults("Genre.getByGenre", new String[]{"genre"}, new String[]{genre});
    }
}
