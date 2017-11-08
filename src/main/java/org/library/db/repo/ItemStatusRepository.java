package org.library.db.repo;

import org.library.db.domain.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemStatusRepository extends JpaRepository<ItemStatus, Integer> {
    List<ItemStatus> findByName(String name);
}
