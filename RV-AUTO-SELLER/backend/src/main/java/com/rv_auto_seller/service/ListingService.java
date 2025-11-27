package com.rv_auto_seller.service;

import com.rv_auto_seller.dto.request.ListingDTO;
import com.rv_auto_seller.model.Listing;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ListingService {
    List<Listing> getAllListings();
    Optional<Listing> getListingById(Long id);
    Listing createListing(Listing listing);
    Listing createListingFromDTO(ListingDTO listingDTO) throws IOException;
    Listing updateListing(Long id, Listing updatedListing);
    void deleteListing(Long id);

}
