package org.library.db.dao;

import org.library.db.Storable;
import org.library.db.dao.impl.BaseDAO;
import org.library.db.models.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Optional;

public class DAOFactory implements Storable
{
    private final static Logger logger = LoggerFactory.getLogger( DAOFactory.class );
    private static final DAOFactory _instance = new DAOFactory();

    @PersistenceUnit
    private static EntityManagerFactory factory = null;

    private static boolean _connected = false;

    private DAOFactory(){}

    /**
     * Provides reference to DAOFactory.
     *
     * @return Instance of DAOFactory.
     */
    public static DAOFactory getInstance()
    {
        if( factory == null ) {
            try {
                factory = Persistence.createEntityManagerFactory("library");
                _connected = true;
            } catch (PersistenceException ex) {
                logger.warn(ex.getMessage());
                System.exit(0);
            }
        }
        return _instance;
    }

    @Override
    public <T extends BaseDAO> Optional<T> getModel(Class<T> daoClass) {
        try {
            return Optional.of(daoClass.getConstructor(EntityManagerFactory.class).newInstance(factory));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public boolean isConnected()
    {
        return _connected;
    }

    @Override
    public void closeConn()
    {
        if( factory != null ) {
            factory.close();
            _connected = false;
        }
    }
}
