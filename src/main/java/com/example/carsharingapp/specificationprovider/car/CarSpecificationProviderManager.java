package com.example.carsharingapp.specificationprovider.car;

import com.example.carsharingapp.model.Car;
import com.example.carsharingapp.specificationprovider.SpecificationProvider;
import com.example.carsharingapp.specificationprovider.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CarSpecificationProviderManager implements SpecificationProviderManager<Car> {
    private final List<SpecificationProvider<Car>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Car> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specification provider for key " + key));
    }
}
