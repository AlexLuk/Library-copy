package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Reader;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class ReaderDAO extends BaseDAO<Reader> implements DAOable<Reader> {

    public ReaderDAO(EntityManagerFactory factory) {
        super(factory);
    }

    /**
     * Gets reader by login
     *
     * @param login reader login
     * @return reader
     */
    public Optional<Reader> getByLogin(String login) throws PersistenceException {
        return getSingleResult("Reader.getByLogin", new String[]{"login"}, new String[]{login});
    }
}
