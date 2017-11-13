package org.library.db.repo;

import org.library.db.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
    Optional<Reader> findByEmail(String email);
}
