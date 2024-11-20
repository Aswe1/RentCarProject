package org.example.rentcarproject.Controllers;

import org.example.rentcarproject.Entities.Car;
import org.example.rentcarproject.Services.CarService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController
{
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<?> getCarsByLocation(@RequestParam String location)
    {
        return carService.getCarsByLocation(location);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCarsByID(@PathVariable("id") int id)
    {
      return carService.getCarByID(id);

    }

    @PostMapping
    public ResponseEntity<String> addCar(@RequestBody Car car)
    {
        return carService.addCar(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCar(@PathVariable("id") int id, @RequestBody Car car)
    {
        car.setId(id);
        return carService.update(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable int id) {
        return carService.deleteCar(id);
    }


}
