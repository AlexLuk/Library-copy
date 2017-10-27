package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class BaseDAO<T extends Base> implements DAOable<T>
{
    private final static Logger logger = LoggerFactory.getLogger( BaseDAO.class );

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

    public <M> Optional<List<M>> getResults(String methodName, String[] args, Object[] vals) {
        TypedQuery<M> namedQuery = getEntityManager()
                .createNamedQuery(methodName, (Class<M>) ((ParameterizedType) getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[0]  );

        for(int i = 0; i < args.length; i++) {
            namedQuery.setParameter(args[i], vals[i]);
        }

        try
        {
            List<M> res = namedQuery.getResultList();
            return Optional.of(res);
        } catch( Exception ex ) {
            logger.warn("Error while retrieving results: " + ex.getMessage());
        }
        return Optional.empty();
    }

    public <M> Optional<M> getSingleResult(String methodName, String[] args, Object[] vals) {
        TypedQuery<M> namedQuery = getEntityManager()
                .createNamedQuery(methodName, (Class<M>) ((ParameterizedType) getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[0]);

        for(int i = 0; i < args.length; i++) {
            namedQuery.setParameter(args[i], vals[i]);
        }

        try
        {
            return Optional.of(namedQuery.getSingleResult() );
        } catch( Exception ex ) {
            logger.warn("Error while retrieving a single result: " + ex.getMessage());
        }
        return Optional.empty();
    }
}
