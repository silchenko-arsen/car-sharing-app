package com.example.carsharingapp.controller;

import com.example.carsharingapp.dto.car.CarDto;
import com.example.carsharingapp.dto.car.CarSearchParametersDto;
import com.example.carsharingapp.service.CarService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public CarDto addCar(@RequestBody CarDto car) {
        return carService.add(car);
    }

    @GetMapping
    public List<CarDto> getAllCars(Pageable pageable) {
        return carService.getAll(pageable);
    }

    @GetMapping("/search")
    public List<CarDto> searchCars(CarSearchParametersDto searchParameters) {
        return carService.search(searchParameters);
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable Long id) {
        return carService.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public CarDto updateCar(@PathVariable Long id, @Valid @RequestBody CarDto carUpdateDto) {
        return carService.update(id, carUpdateDto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public CarDto updateInventory(@PathVariable Long id, @RequestParam Integer inventory) {
        return carService.updateInventory(id, inventory);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public void deleteCar(@PathVariable Long id) {
        carService.delete(id);
    }
}
