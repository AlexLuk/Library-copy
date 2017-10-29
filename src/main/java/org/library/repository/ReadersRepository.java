package org.library.repository;

import org.library.db.models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReadersRepository extends JpaRepository<Reader, Integer> {
    Optional<Reader> findReaderByEmail(String readerEmail);
}
