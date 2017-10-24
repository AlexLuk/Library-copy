package org.library.db.dao;

import org.library.db.dao.impl.BaseDAO;

import javax.persistence.*;
import java.util.Optional;

public class DAOFactory
{
    private static final DAOFactory _instance = new DAOFactory();

    @PersistenceUnit
    private static EntityManagerFactory factory = null;

    private DAOFactory(){}

    /**
     * Provides reference to DAOFactory.
     *
     * @return Instance of DAOFactory.
     */
    public static DAOFactory getInstance() throws PersistenceException
    {
        if( factory == null ) {
            factory = Persistence.createEntityManagerFactory( "library" );
        }
        return _instance;
    }

    /**
     * Gets the necessary DAO model.
     *
     * @param entityType    class of the entity
     * @return Ref to the specific BaseDAO.
     */
    public Optional<BaseDAO> getModel(Class entityType )
    {
        return Optional.empty();
    }

    /**
     * Closes DB connection.
     */
    public static void closeConnection()
    {
        if( factory != null )
            factory.close();
    }
}
