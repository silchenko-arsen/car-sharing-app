package com.example.carsharingapp.mapper;

import com.example.carsharingapp.dto.rental.CreateRentalRequestDto;
import com.example.carsharingapp.dto.rental.RentalDto;
import com.example.carsharingapp.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "carId", source = "car.id")
    RentalDto toDto(Rental rental);

    Rental createDtoToEntity(CreateRentalRequestDto requestDto);
}
