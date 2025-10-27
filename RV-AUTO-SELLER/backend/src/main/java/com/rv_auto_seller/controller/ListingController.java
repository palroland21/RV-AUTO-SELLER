package com.rv_auto_seller.controller;

import com.rv_auto_seller.dto.response.ListingResponse;
import com.rv_auto_seller.model.Listing;
import com.rv_auto_seller.service.ListingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/listing")
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("/listings")
    public ResponseEntity<List<ListingResponse>> getAllListings() {
        List<ListingResponse> responses = listingService.getAllListings()
                .stream()
                .map(ListingResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingResponse> getListingById(@PathVariable("id") Long id) {
        return listingService.getListingById(id)
                .map(listing -> ResponseEntity.ok(new ListingResponse(listing)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new_listing")
    public ResponseEntity<ListingResponse> createListing(@RequestBody Listing listing) {
        if (listing.getImages() != null) {
            listing.getImages().forEach(image -> image.setListing(listing));
        }
        Listing savedListing = listingService.createListing(listing);
        return ResponseEntity.ok(new ListingResponse(savedListing));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListingResponse> updateListing(@PathVariable("id") Long id, @RequestBody Listing listing) {
        Listing updated = listingService.updateListing(id, listing);
        return ResponseEntity.ok(new ListingResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable("id") Long id) {
        listingService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }
}
