package com.example.carsharingapp.dto.rental;

import com.example.carsharingapp.validation.ValidDateOrderConstraint;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@ValidDateOrderConstraint(
        field = "rentalDate",
        fieldMatch = "returnDate",
        message = "Rental date should be earlier then returnDate"
)
public class CreateRentalRequestDto {
    @NotNull(message = "rental date should not be null")
    @FutureOrPresent(message = "rental date should be in the future or present")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate rentalDate;

    @NotNull(message = "return date should not be null")
    @Future(message = "return date should be in the future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate returnDate;
    @NotNull(message = "car id should not be null")
    private Long carId;
}
