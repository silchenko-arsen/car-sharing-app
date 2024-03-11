package com.example.carsharingapp.mapper;

import com.example.carsharingapp.dto.car.CarDto;
import com.example.carsharingapp.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(target = "id", ignore = true)
    Car toEntity(CarDto carDto);

    CarDto toDto(Car car);
}
