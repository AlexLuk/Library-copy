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
     * Gets reader by email
     *
     * @param email reader email
     * @return reader
     */
    public Optional<Reader> getByEmail(String email) throws PersistenceException {
        return getSingleResult("Reader.getByEmail", new String[]{"email"}, new String[]{email});
    }
}
