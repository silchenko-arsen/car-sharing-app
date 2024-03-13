package com.example.carsharingapp.service.impl;

import com.example.carsharingapp.dto.rental.CreateRentalRequestDto;
import com.example.carsharingapp.dto.rental.RentalDto;
import com.example.carsharingapp.exception.EmptyDataException;
import com.example.carsharingapp.mapper.RentalMapper;
import com.example.carsharingapp.model.Car;
import com.example.carsharingapp.model.Rental;
import com.example.carsharingapp.model.User;
import com.example.carsharingapp.repository.CarRepository;
import com.example.carsharingapp.repository.RentalRepository;
import com.example.carsharingapp.repository.UserRepository;
import com.example.carsharingapp.service.RentalService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RentalMapper rentalMapper;

    @Override
    public RentalDto add(
            CreateRentalRequestDto requestDto, String email) {
        Car car = carRepository.findById(requestDto.getCarId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find car by id " + requestDto.getCarId()));
        if (car.getInventory() == 0) {
            throw new EmptyDataException("These car models are out of stock for rent");
        }
        User user = userRepository
                .findByEmail(email).orElseThrow(()
                        -> new EntityNotFoundException("Can't find user by email" + email));
        car.setInventory(car.getInventory() - 1);
        Rental rental = rentalMapper.createDtoToEntity(requestDto);
        rental.setCar(car);
        rental.setUser(user);
        rental.setActive(true);
        carRepository.save(car);
        return rentalMapper.toDto(rentalRepository.save(rental));
    }

    @Override
    public List<RentalDto> findAllByUserIdAndStatus(
            Long userId, Boolean isActive, Pageable pageable) {
        User user = userRepository
                .findById(userId).orElseThrow(()
                        -> new EntityNotFoundException("Can't find user by id" + userId));
        if (user.getRole().equals(User.Role.MANAGER)) {
            return rentalRepository.findAllByIsActive(isActive, pageable)
                    .stream()
                    .map(rentalMapper::toDto)
                    .toList();
        }
        return rentalRepository.findAllByUserIdAndActive(userId, isActive, pageable)
                .stream()
                .map(rentalMapper::toDto)
                .toList();
    }

    @Override
    public RentalDto getUserRentalDetailsByAuthentication(Long id) {
        return rentalMapper.toDto(rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find rent by id " + id)));
    }

    @Override
    public RentalDto returnRentalCar(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find rent by id " + id));
        rental.setActualReturnDate(LocalDate.now());
        rental.setActive(false);
        rentalRepository.save(rental);
        Car car = carRepository.findById(rental.getCar().getId()).orElseThrow(
                () -> new EntityNotFoundException("Can't get car by id "
                        + rental.getCar().getId()));
        car.setInventory(car.getInventory() + 1);
        carRepository.save(car);
        return rentalMapper.toDto(rental);
    }
}
