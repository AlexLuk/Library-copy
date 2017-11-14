package org.library.db.repo;

import org.library.db.domain.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookOrderRepository extends JpaRepository<BookOrder, Integer> {
    List<BookOrder> findByReaderId(int readerId);

    /**
     * Count orders of book for reader
     *
     * @param readerId - reader id
     * @param bookId   - book id
     * @return - count of orders fof this reader
     */
    int countByReaderIdAndBookId(Integer readerId, Integer bookId);

    int countByReaderId(Integer readerId);
}
