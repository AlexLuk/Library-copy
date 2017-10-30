package org.library.db.dao.impl;

import org.library.db.dao.DAOable;
import org.library.db.models.BookOrder;
import org.library.db.models.Delivery;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class BookOrderDAO extends BaseDAO<BookOrder> implements DAOable<BookOrder> {

    public BookOrderDAO(EntityManagerFactory factory ) {
        super( factory );
    }

    /**Get list of orders for specifig user
     * @param readerID user id
     * @param status order status
     * @return list of orderedBooksForSpecificUser
     * @throws PersistenceException
     */
    public Optional<List<BookOrder>> getBookOrdersByReaderAndStatus(int readerID, String status) throws PersistenceException
    {
        Optional<List<BookOrder>> bookOrders = null;
        return bookOrders;
    }
}
