package com.example.carsharingapp.specificationprovider.car;

import com.example.carsharingapp.model.Car;
import com.example.carsharingapp.specificationprovider.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BrandSpecificationProvider implements SpecificationProvider<Car> {
    private static final String BRAND = "brand";

    @Override
    public String getKey() {
        return BRAND;
    }

    public Specification<Car> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root
                .get(BRAND).in(Arrays.stream(params).toArray());
    }
}
