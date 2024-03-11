package com.example.carsharingapp.specificationprovider;

import com.example.carsharingapp.dto.car.CarSearchParametersDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(CarSearchParametersDto parametersDto);
}
