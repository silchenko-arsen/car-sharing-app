package com.example.carsharingapp.repository;

import com.example.carsharingapp.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
