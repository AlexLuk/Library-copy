package org.library.db.repo;

import org.library.db.domain.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemStatusRepository extends JpaRepository<ItemStatus, Integer> {

}
