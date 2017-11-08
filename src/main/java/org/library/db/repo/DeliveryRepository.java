package org.library.db.repo;

import org.library.db.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    List<Delivery> findByReaderId(int readerId);
}
