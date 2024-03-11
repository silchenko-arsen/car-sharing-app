package com.example.carsharingapp.specificationprovider.car;

import com.example.carsharingapp.dto.car.CarSearchParametersDto;
import com.example.carsharingapp.model.Car;
import com.example.carsharingapp.specificationprovider.SpecificationBuilder;
import com.example.carsharingapp.specificationprovider.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CarSpecificationBuilder implements SpecificationBuilder<Car> {
    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private final SpecificationProviderManager<Car> carSpecificationProviderManager;

    @Override
    public Specification<Car> build(CarSearchParametersDto searchParametersDto) {
        Specification<Car> specification = Specification.where(null);
        if (searchParametersDto.getBrands() != null
                && searchParametersDto.getBrands().length > 0) {
            specification = specification
                    .and(carSpecificationProviderManager.getSpecificationProvider(BRAND)
                    .getSpecification(searchParametersDto.getBrands()));
        }
        if (searchParametersDto.getModels() != null
                && searchParametersDto.getModels().length > 0) {
            specification = specification
                    .and(carSpecificationProviderManager.getSpecificationProvider(MODEL)
                            .getSpecification(searchParametersDto.getModels()));
        }
        return specification;
    }
}
