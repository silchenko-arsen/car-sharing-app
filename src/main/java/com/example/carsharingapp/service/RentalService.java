package com.example.carsharingapp.service;

import com.example.carsharingapp.dto.rental.CreateRentalRequestDto;
import com.example.carsharingapp.dto.rental.RentalDto;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface RentalService {
    RentalDto add(@Valid CreateRentalRequestDto rentalDto, String email);

    List<RentalDto> findAllByUserIdAndStatus(Long userId, Boolean isActive, Pageable pageable);

    RentalDto getUserRentalDetailsByAuthentication(Long id);

    RentalDto returnRentalCar(Long id);
}
