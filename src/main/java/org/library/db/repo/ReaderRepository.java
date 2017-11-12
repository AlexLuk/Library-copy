package org.library.db.repo;

import org.library.db.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
    Optional<Reader> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Reader r set r.firstName = ?1, r.lastName = ?2, r.patronymic = ?3, r.password = ?4 where r.email = ?5")
    void setReaderInfoById(String firstName, String lastName, String patronymic, String password, String email);
}
