package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Base;

import javax.persistence.*;

public class BaseDAO<T extends Base> implements DAOable<T>
{
    @PersistenceContext
    private EntityManager entityManager = null;

    BaseDAO(EntityManagerFactory factory )
    {
        if( entityManager == null )
            entityManager = factory.createEntityManager();
    }

    EntityManager getEntityManager()
    {
        return entityManager;
    }

    /**
     * Closes the session with the EntityManager.
    */
    public void close()
    {
        entityManager.close();
        entityManager = null;
    }

    public T add( T object )
    {
        EntityManager entityManager = getEntityManager();
        try
        {
            // starting the transaction
            entityManager.getTransaction().begin();

            // adding new object to the corresponding table
            T addedObj = entityManager.merge( object );

            // committing the transaction
            entityManager.getTransaction().commit();

            return addedObj;
        } catch( PersistenceException ex )
        {
            if( entityManager.getTransaction().isActive() )
                entityManager.getTransaction().rollback();
        }
        return null;
    }

    public void update( T object )
    {
        EntityManager entityManager = getEntityManager();
        try
        {
            // starting the transaction
            entityManager.getTransaction().begin();

            // updating object in the corresponding table
            entityManager.merge( object );

            // committing the transaction
            entityManager.getTransaction().commit();
        } catch( PersistenceException ex )
        {
            if( entityManager.getTransaction().isActive() )
                entityManager.getTransaction().rollback();
        }
    }

    public T getById( int id, Class objClass )
    {
        return ( T ) getEntityManager().find( objClass, id );
    }

    public void delete( T obj, Class objClass )
    {
        EntityManager entityManager = getEntityManager();
        try
        {
            // starting the transaction
            entityManager.getTransaction().begin();

            // removing the object
            entityManager.remove( getById( obj.getId(), objClass ) );

            // committing the transaction
            entityManager.getTransaction().commit();
        } catch( PersistenceException ex )
        {
            if( entityManager.getTransaction().isActive() )
                entityManager.getTransaction().rollback();
        }
    }
}
