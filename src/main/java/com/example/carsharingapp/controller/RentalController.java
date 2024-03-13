package com.example.carsharingapp.controller;

import com.example.carsharingapp.dto.rental.CreateRentalRequestDto;
import com.example.carsharingapp.dto.rental.RentalDto;
import com.example.carsharingapp.service.RentalService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping
    public RentalDto addRental(@Valid @RequestBody CreateRentalRequestDto createRentalRequestDto) {
        return rentalService.add(createRentalRequestDto, getAuthenticationName());
    }

    @GetMapping
    public List<RentalDto> getRentalsByUserIdAndRentalStatus(
            @RequestParam(name = "user_id", required = false) Long userId,
            @RequestParam(name = "is_active", defaultValue = "true") Boolean isActive,
            Pageable pageable) {
        return rentalService.findAllByUserIdAndStatus(userId, isActive, pageable);
    }

    @GetMapping("/{id}")
    public RentalDto getUserRentalDetails(@PathVariable Long id) {
        return rentalService.getUserRentalDetailsByAuthentication(id);
    }

    @PostMapping("/{id}/return")
    public RentalDto returnRental(@PathVariable Long id) {
        return rentalService.returnRentalCar(id);
    }

    private String getAuthenticationName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
