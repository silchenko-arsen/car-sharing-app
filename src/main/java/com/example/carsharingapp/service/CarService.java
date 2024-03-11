package com.example.carsharingapp.service;

import com.example.carsharingapp.dto.car.CarDto;
import com.example.carsharingapp.dto.car.CarSearchParametersDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CarService {
    CarDto add(CarDto car);

    List<CarDto> getAll(Pageable pageable);

    List<CarDto> search(CarSearchParametersDto searchParameters);

    CarDto getById(Long id);

    CarDto update(Long id, CarDto carDto);

    CarDto updateInventory(Long id, Integer inventory);

    void delete(Long id);
}
