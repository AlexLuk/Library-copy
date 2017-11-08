package org.library.db.repo;

import org.library.db.domain.Book;
import org.library.db.domain.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookItemRepository extends JpaRepository<BookItem, Integer> {
    List<BookItem> findByBookId(Integer bookId);
}
