package com.rv_auto_seller.service;

import com.rv_auto_seller.model.Listing;

import java.util.List;
import java.util.Optional;

public interface ListingService {
    List<Listing> getAllListings();
    Optional<Listing> getListingById(Long id);
    Listing createListing(Listing listing);
    Listing updateListing(Long id, Listing updatedListing);
    void deleteListing(Long id);

}
