package org.library.db;

import javax.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.library.db.dao.DAOFactory;

public class DBController implements Storable
{
    private final static Logger logger = LoggerFactory.getLogger( DBController.class );
    private boolean _connected = false;

    public DBController(){}

    public boolean openConn()
    {
        try
        {
            DAOFactory.getInstance();
            _connected = true;
        } catch( Exception ex ) { logger.warn( ex.getMessage() ); return false; }
        return true;
    }

    public DAOFactory getModelFactory() throws PersistenceException
    {
        return DAOFactory.getInstance();
    }

    public boolean isConnected()
    {
        return _connected;
    }

    public void closeConn()
    {
        DAOFactory.closeConnection();
        _connected = false;
    }
}
