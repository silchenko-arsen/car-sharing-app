package com.example.carsharingapp.service.impl;

import com.example.carsharingapp.dto.car.CarDto;
import com.example.carsharingapp.dto.car.CarSearchParametersDto;
import com.example.carsharingapp.exception.EntityNotFoundException;
import com.example.carsharingapp.mapper.CarMapper;
import com.example.carsharingapp.model.Car;
import com.example.carsharingapp.repository.CarRepository;
import com.example.carsharingapp.service.CarService;
import com.example.carsharingapp.specificationprovider.car.CarSpecificationBuilder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {
    private final CarMapper carMapper;
    private final CarRepository carRepository;
    private final CarSpecificationBuilder carSpecificationBuilder;

    @Override
    public CarDto add(CarDto carDto) {
        Car car = carMapper.toEntity(carDto);
        return carMapper.toDto(carRepository.save(car));
    }

    @Override
    public List<CarDto> getAll(Pageable pageable) {
        return carRepository.findAll(pageable).map(carMapper::toDto).toList();
    }

    @Override
    public List<CarDto> search(CarSearchParametersDto searchParameters) {
        Specification<Car> carSpecification = carSpecificationBuilder.build(searchParameters);
        return carRepository.findAll(carSpecification)
                .stream()
                .map(carMapper::toDto).toList();
    }

    @Override
    public CarDto getById(Long id) {
        return carMapper.toDto(carRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Can't find car by id" + id)));
    }

    @Override
    public CarDto update(Long id, CarDto carDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(()
                        -> new EntityNotFoundException("Can't find car by id" + id));
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setType(carDto.getType());
        car.setInventory(carDto.getInventory());
        car.setDailyFee(carDto.getDailyFee());
        return carMapper.toDto(carRepository.save(car));
    }

    @Override
    public CarDto updateInventory(Long id, Integer inventory) {
        if (inventory < 0) {
            throw new IllegalArgumentException("Inventory can't be negative");
        } else {
            Car car = carRepository.findById(id)
                    .orElseThrow(()
                            -> new EntityNotFoundException("Can't find car by id" + id));
            car.setInventory(inventory);
            return carMapper.toDto(carRepository.save(car));
        }
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
