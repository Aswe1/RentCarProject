package org.example.rentcarproject.Services;


import org.example.rentcarproject.Entities.Car;
import org.example.rentcarproject.Repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService
{
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public ResponseEntity<?> getCarByID(int id)
    {
        Car car =  carRepository.findById(id);
        if (car == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No Cars with id of: " + id);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    public ResponseEntity<?> getCarsByLocation(String location)
    {

        List<Car> carList = carRepository.findAllByLocationAndIsDeletedFalse(location);
        if (carList == null|| carList.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No Cars Available at " + location);

        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteCar(int id)
    {
        try {
            carRepository.softDelete(id);
            return ResponseEntity.ok("Car softDeleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error softDeleting car: " + e.getMessage());
        }

    }

    public ResponseEntity<String> update(Car car)
    {
        try {
            carRepository.update(car);
            return ResponseEntity.ok("Car updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating car: " + e.getMessage());
        }
    }

    public ResponseEntity<String> addCar(Car car) {
        try {
            // Проверяваме дали градът е валиден
            if (!isValidLocation(car.getLocation())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Invalid location. The car can only be located in Plovdiv, Sofia, Varna, or Burgas.");
            }

            // Добавяме новия автомобил
            carRepository.add(car);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Car added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error adding car: " + e.getMessage());
        }
    }

    // Метод за проверка на валидността на локацията
    private boolean isValidLocation(String location) {
        List<String> validLocations = Arrays.asList("Plovdiv", "Sofia", "Varna", "Burgas");
        return validLocations.contains(location);
    }
}
