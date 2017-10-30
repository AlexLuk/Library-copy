package org.library.db.repo;

import org.library.db.domain.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {
    Optional<AuthorBook> findByAuthorId(int authorID);
}
