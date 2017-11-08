package org.library.db.repo;

import org.library.db.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByName(String name);
    List<Genre> findByNameContaining(String name);
}
