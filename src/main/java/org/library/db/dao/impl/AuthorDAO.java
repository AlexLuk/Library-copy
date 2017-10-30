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
     * Gets author by surname
     *
     * @param surname book's author surname
     * @return surname
     */
    public Optional<List<Author>> getAuthorBySurname(String surname) throws PersistenceException
    {
        return getResults(Author.GET_BY_SURNAME, new String[]{"surname"}, new String[]{surname});
    }

    /**
     * Gets author by name
     *
     * @param name book's author name
     * @return name
     */
    public Optional<List<Author>> getAuthorByName(String name) throws PersistenceException
    {
        return getResults(Author.GET_BY_NAME, new String[]{"name"}, new String[]{name});
    }

    /**
     * Gets author by patronymic
     *
     * @param patronymic book's author patronymic
     * @return patronymic
     */
    public Optional<List<Author>> getAuthorByPatronymic(String patronymic) throws PersistenceException
    {
        return getResults(Author.GET_BY_PATRONYMIC, new String[]{"patronymic"}, new String[]{patronymic});
    }

    /**
     * Gets author by year
     *
     * @param year book's author year
     * @return year
     */
    public Optional<List<Author>> getAuthorByYear(int year) throws PersistenceException
    {
        return getResults(Author.GET_BY_YEAR, new String[]{"year"}, new Integer[]{year});
    }
}
