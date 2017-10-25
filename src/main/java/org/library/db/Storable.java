package org.library.db;

import org.library.db.dao.impl.BaseDAO;

import java.util.Optional;

public interface Storable
{
    /*
     * Gets a particular DAO implementation
     *
     * @param daoClass    model class
     * @return	Optional with DAO impl
     */
    Optional<? extends BaseDAO> getModel(Class<? extends BaseDAO> daoClass);

    /*
     * Checks whether the connection is established
     *
     * @return	flag of success
     */
    boolean isConnected();

    /*
     * Closes connection to the storage
     */
    void closeConn();
}
