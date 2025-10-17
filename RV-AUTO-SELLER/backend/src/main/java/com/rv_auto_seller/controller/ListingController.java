package com.rv_auto_seller.controller;

import com.rv_auto_seller.model.Listing;
import com.rv_auto_seller.service.ListingService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(("/listing"))
@RestController
public class ListingController {
        private final ListingService listingService;

        public ListingController(ListingService listingService) {
            this.listingService = listingService;
        }

        @GetMapping("/listings")
        public ResponseEntity<List<Listing>> getAllListings(){
            return ResponseEntity.ok(listingService.getAllListings());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Optional<Listing>> getListingById(Long listingId){
            return ResponseEntity.ok(listingService.getListingById(listingId));
        }

        @PostMapping("/new_listing")
        public ResponseEntity<Listing> createListing(@RequestBody Listing listing){
            if(listing.getImages() != null) {
                listing.getImages().forEach(image -> image.setListing(listing));
            }
            return ResponseEntity.ok(listingService.createListing(listing));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Listing> updateListing(@PathVariable Long id, @RequestBody Listing listing){
            return ResponseEntity.ok(listingService.updateListing(id, listing));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteListing(@PathVariable Long id){
            listingService.deleteListing(id);
            return ResponseEntity.noContent().build();
        }
}
