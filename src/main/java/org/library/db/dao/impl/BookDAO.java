package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Book;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class BookDAO extends BaseDAO<Book> implements DAOable<Book> {

    public BookDAO(EntityManagerFactory factory ) {
        super( factory );
    }

    /**
     * Gets book by title
     *
     * @param title book's title
     * @return book
     */
    public Optional<List<Book>> getByTitle(String title) throws PersistenceException
    {
        return getResults("Book.getByTitle", new String[]{"title"}, new String[]{title});
    }

    /**
     * Gets book by genreId
     *
     * @param genreId book's genreId
     * @return book
     */
    public Optional<List<Book>> getByGenre(int genreId) throws PersistenceException
    {
        return getResults("Book.getByGenre", new String[]{"genreId"}, new Integer[]{genreId});
    }

    /**
     * Gets book by authorId
     *
     * @param authorId book's authorId
     * @return book
     */
    public Optional<List<Book>> getByAuthorId(int authorId) throws PersistenceException
    {
        return getResults("Book.getByAuthor", new String[]{"authorId"}, new Integer[]{authorId});
    }

}
