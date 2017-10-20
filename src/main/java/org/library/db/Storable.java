package org.library.db;

public interface Storable
{
    /*
     * Opens connection to the storage
     *
     * @return	flag of success
     */
    boolean openConn();

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
