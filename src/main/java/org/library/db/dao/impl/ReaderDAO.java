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
        TypedQuery<Reader> namedQuery = getEntityManager()
                .createNamedQuery("Reader.getByLogin", Reader.class);
        namedQuery.setParameter("login", login);

        try {
            return Optional.of(namedQuery.getSingleResult());
        } catch (Exception ex) {
        }
        return Optional.empty();
    }

}
