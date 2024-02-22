package com.example.carsharingapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Table(name = "cars")
@Accessors(chain = true)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(nullable = false)
    @Min(0)
    private Integer inventory;
    @Column(nullable = false, name = "daily_fee")
    @Min(0)
    private BigDecimal dailyFee;

    public enum Type {
        SEDAN, SUV, HATCHBACK, UNIVERSAL
    }
}
