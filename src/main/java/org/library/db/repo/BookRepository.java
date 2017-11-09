package org.library.db.repo;

import org.library.db.domain.Book;
import org.library.db.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContaining(String title);

    List<Book> findByTitleContainingIgnoreCaseAndYearAndGenreInAndIdIn(String title, Integer year,
                                                                       List<Genre> genres, List<Integer> ids);

    List<Book> findByTitleContainingIgnoreCaseAndGenreInAndIdIn(String title, List<Genre> genres, List<Integer> ids);

}
