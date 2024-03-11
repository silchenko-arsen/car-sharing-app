package com.example.carsharingapp.dto.car;

import com.example.carsharingapp.model.Car;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CarDto {
    @NotBlank(message = "Brand mustn't be null or empty")
    private String brand;
    @NotBlank(message = "Model mustn't be null or empty")
    private String model;
    private Car.Type type;
    @Min(value = 0, message = "Inventory mustn't be null or empty")
    private Integer inventory;
    @Min(value = 0, message = "Inventory mustn't be null or empty")
    private BigDecimal dailyFee;
}
