package org.library.db.repo;

import org.library.db.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByLastName(String lastname);
    List<Author> findByFirstName(String name);
    List<Author> findByPatronymic(String patronymic);
    List<Author> findByYearOfBirth(Integer yearOfBirth);
}
