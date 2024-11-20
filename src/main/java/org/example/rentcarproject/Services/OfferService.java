package org.example.rentcarproject.Services;


import org.example.rentcarproject.Entities.Car;
import org.example.rentcarproject.Entities.Offer;
import org.example.rentcarproject.Repository.CarRepository;
import org.example.rentcarproject.Repository.OfferRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService
{
    private final OfferRepository offerRepository;
    private final CarRepository carRepository;

    public OfferService(OfferRepository offerRepository, CarRepository carRepository) {
        this.offerRepository = offerRepository;
        this.carRepository = carRepository;
    }

    public ResponseEntity<?> createOffer(Offer offer) {
        try {
            Car car = carRepository.findById(offer.getCarId());
            if (car == null || car.isDeleted() || car.getIsRented()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error car either doesnt exist or is rented: ");
            }
            double totalPrice = car.getDailyPrice() * offer.getRentalDays();

            if (isWeekend(offer)) {
                totalPrice *= 1.1;
            }

            // Добавяне на 200 лв. при инциденти
            if (offer.getAccidents()) {
                totalPrice += 200;
            }

            offer.setTotalPrice(totalPrice);
            offerRepository.create(offer);
            return ResponseEntity.ok("Offer created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error Offer failed: " + e.getMessage());
        }
    }

    public ResponseEntity<?> findAllByCustomerName(String customerName) {
            List<Offer> offers = offerRepository.findAllByCustomerName(customerName);
            if (offers == null || offers.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No Offers Available for " + customerName);

            return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    public Offer findById(int id) {
        return offerRepository.findById(id);
    }

    public ResponseEntity<?> findByIdWithResponse(int id) {
        Offer offer =  offerRepository.findById(id);
        if (offer == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No Offers with id of: " + id);

        return new ResponseEntity<>(offer, HttpStatus.OK);
    }
    public ResponseEntity<?> acceptOffer(int id) {
        try {
            Offer offer = findById(id);

            if (offer == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Offer not found:");

            Car car = carRepository.findById(offer.getCarId());
            car.setIsRented(true);
            carRepository.update(car);

            offerRepository.accept(id);
            return ResponseEntity.ok("Offer accepted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error Offer acceptance: " + e.getMessage());
        }
    }

    public ResponseEntity<?> deleteOffer(int id) {
        try {
            offerRepository.softDelete(id);
            return ResponseEntity.ok("Offer softDeleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error Offer softDeleting failed: " + e.getMessage());
        }
    }
    private boolean isWeekend(Offer offer)
    {
        // To Do
        // Увеличаване с 10% за уикенди
        return true;
    }
}
