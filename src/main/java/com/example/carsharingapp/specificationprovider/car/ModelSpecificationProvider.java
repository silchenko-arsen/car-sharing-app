package com.example.carsharingapp.specificationprovider.car;

import com.example.carsharingapp.model.Car;
import com.example.carsharingapp.specificationprovider.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ModelSpecificationProvider implements SpecificationProvider<Car> {
    private static final String MODEL = "model";

    @Override
    public String getKey() {
        return MODEL;
    }

    public Specification<Car> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root
                .get(MODEL).in(Arrays.stream(params).toArray());
    }
}
