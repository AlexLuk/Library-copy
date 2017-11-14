package org.library.db.repo;

import org.library.db.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    List<Delivery> findByReaderId(int readerId);

    @Query("select delivery from Delivery delivery")
    //todo - write full filter
    List<Delivery> findByComplexQuery();

    /**
     * Count deliveries of exact book for exact reader
     *
     * @param readerId - reader id
     * @param bookId   - book id
     * @return number of entries in delivery table
     * for this reader and this book
     */
    @Query("select count(delivery) from Delivery delivery where delivery.reader.id = :readerId " +
            "and delivery.bookItem.id in(" +
            "select bookItem.id from BookItem bookItem where book.id = :bookId" +
            ")")
    int countByReaderIdAndBookId(@Param("readerId") Integer readerId, @Param("bookId") Integer bookId);
}
