package org.example.rentcarproject.Controllers;

import org.example.rentcarproject.Entities.Offer;
import org.example.rentcarproject.Services.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController
{
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    public ResponseEntity<?> createOffer(@RequestBody Offer offer) {
        return offerService.createOffer(offer);
    }

    @GetMapping
    public ResponseEntity<?> getOffersByCustomerName(@RequestParam String customerName) {
        return offerService.findAllByCustomerName(customerName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOfferByID(@PathVariable int id) {
        return offerService.findByIdWithResponse(id);
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<?> acceptOffer(@PathVariable int id) {
        return offerService.acceptOffer(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable int id) {
        return offerService.deleteOffer(id);
    }
}
