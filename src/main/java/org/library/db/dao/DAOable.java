package org.library.db.dao;

import org.library.db.models.Base;

import javax.persistence.PersistenceException;

public interface DAOable<T extends Base>
{
    /**
     * Adds object.
     *
     * @param obj Object to add
     * @throws PersistenceException
    */
    T add(T obj) throws PersistenceException;

    /**
     * Updates object data.
     *
     * @param obj Object to update
     * @throws PersistenceException
    */
    void update(T obj) throws PersistenceException;

    /**
     * Gets object by its id.
     *
     * @param id object id
     * @param objClass Object class
     * @throws PersistenceException
    */
    T getById(int id, Class<T> objClass) throws PersistenceException;

    /**
     * Removes object.
     *
     * @param obj Obj to delete
     * @param objClass Object class
     * @throws PersistenceException
    */
    void delete(T obj, Class<T> objClass) throws PersistenceException;
}
