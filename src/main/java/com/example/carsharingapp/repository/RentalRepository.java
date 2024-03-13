package com.example.carsharingapp.repository;

import com.example.carsharingapp.model.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface RentalRepository extends JpaRepository<Rental, Long>,
        JpaSpecificationExecutor<Rental> {
    @Query("FROM Rental r WHERE r.user.id = :userId AND r.isActive = :isActive")
    Page<Rental> findAllByUserIdAndActive(Long userId, Boolean isActive, Pageable pageable);

    Page<Rental> findAllByIsActive(Boolean isActive, Pageable pageable);
}
