package org.library.db.repo;

import org.library.db.domain.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookOrderRepository extends JpaRepository<BookOrder, Integer> {
    List<BookOrder> findByReaderIdAndOnHands(int readerId, boolean status);
    List<BookOrder> findByReaderId(int readerId);
}
