package com.example.carsharingapp.dto.car;

import com.example.carsharingapp.model.Car;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CarSearchParametersDto {
    private String[] models;
    private String[] brands;
    private Car.Type[] types;
}
