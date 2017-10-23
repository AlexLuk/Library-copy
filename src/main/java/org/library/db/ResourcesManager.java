package org.library.db;

import org.library.db.dao.impl.*;
import org.library.db.models.*;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;

public class ResourcesManager
{

    private static AuthorDAO _authorDAO = null;
    private static AuthorBookDAO _authorBookDAO = null;
    private static BookDAO _bookDAO = null;
    private static DeliveryDAO _deliveryDAO = null;
    private static GenreDAO _genreDAO = null;
    private static OrderDAO _orderDAO = null;
    private static ReaderDAO _readerDAO = null;

    private static DBController _dbController = null;

    public ResourcesManager() {}

    public static void setDbController( DBController dbController )
    {
        _dbController = dbController;
    }

    public static void init()
    {
        if( _dbController != null )
        {
            try
            {
                _authorDAO = ( AuthorDAO ) _dbController.getModelFactory().getModel( Author.class ).orElse( null );
                _authorBookDAO = ( AuthorBookDAO ) _dbController.getModelFactory().getModel( AuthorBook.class ).orElse( null );
                _bookDAO =
                    ( BookDAO ) _dbController.getModelFactory().getModel( Book.class ).orElse( null );
                _deliveryDAO =
                    ( DeliveryDAO ) _dbController.getModelFactory().getModel( Delivery.class ).orElse( null );
                _genreDAO =
                    ( GenreDAO ) _dbController.getModelFactory().getModel( Genre.class ).orElse( null );
                _orderDAO =
                    ( OrderDAO ) _dbController.getModelFactory().getModel( Order.class ).orElse( null );
                _readerDAO =
                    ( ReaderDAO ) _dbController.getModelFactory().getModel( Reader.class ).orElse( null );
            }
            catch( Exception ex ){}
        }
    }

    public static Optional<AuthorDAO> getAuthorDAO() { return Optional.of( _authorDAO ); }
    public static Optional<AuthorBookDAO> getAuthorBookDAO() { return Optional.of( _authorBookDAO ); }
    public static Optional<BookDAO> getBookDAO() { return Optional.of( _bookDAO ); }
    public static Optional<DeliveryDAO> getDeliveryDAO() { return Optional.of( _deliveryDAO ); }
    public static Optional<GenreDAO> getGenreDAO() { return Optional.of( _genreDAO ); }
    public static Optional<OrderDAO> getOrderDAO() { return Optional.of( _orderDAO ); }
    public static Optional<ReaderDAO> getReaderDAO() { return Optional.of( _readerDAO ); }

    public static String getDate()
    {
        LocalDate date = LocalDate.now();
        return date.getDayOfMonth() + " " +
                date.getMonth().getDisplayName( TextStyle.SHORT, Locale.UK ) + ", " + date.getYear();
    }

    public static void closeResources()
    {
        if( _authorDAO != null )
            _authorDAO.close();

        if( _authorBookDAO != null )
            _authorBookDAO.close();

        if( _bookDAO != null )
            _bookDAO.close();

        if( _deliveryDAO != null )
            _deliveryDAO.close();

        if( _genreDAO != null )
            _genreDAO.close();

        if( _orderDAO != null )
            _orderDAO.close();

        if( _readerDAO != null )
            _readerDAO.close();
    }
}
