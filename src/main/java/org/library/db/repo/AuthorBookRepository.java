package org.library.db.repo;

import org.library.db.domain.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {
    List<AuthorBook> findAllByAuthorId(int authorID);
    List<AuthorBook> findAllByBookId(int bookID);
    List<AuthorBook> findAllByAuthorIdIn(List<Integer> authorIDs);
}
