package com.rv_auto_seller.service.impl;

import com.rv_auto_seller.model.Image;
import com.rv_auto_seller.model.Listing;
import com.rv_auto_seller.repository.ListingRepository;
import com.rv_auto_seller.service.ListingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingServiceImpl implements ListingService {

   private final ListingRepository listingRepository;

    public ListingServiceImpl(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @Override
    public List<Listing>  getAllListings(){
        return listingRepository.findAll();
    }

    @Override
    public Optional<Listing> getListingById(Long id){
        return listingRepository.findById(id);

    }
    @Override
    public Listing createListing(Listing listing){
        return listingRepository.save(listing);
    }
    @Override
    public Listing updateListing(Long id, Listing updatedListing){
        Listing existing  = listingRepository.findById(id).get();

        existing.setTitle(updatedListing.getTitle());
        existing.setPrice(updatedListing.getPrice());
        existing.setBrand(updatedListing.getBrand());
        existing.setModel(updatedListing.getModel());
        existing.setType(updatedListing.getType());
        existing.setYearOfManufacture(updatedListing.getYearOfManufacture());
        existing.setFuelType(updatedListing.getFuelType());
        existing.setHorsePower(updatedListing.getHorsePower());
        existing.setLocation(updatedListing.getLocation());
        existing.setVIN(updatedListing.getVIN());
        existing.setDescription(updatedListing.getDescription());
        //existing.setImages(updatedListing.getImages());
        existing.getImages().clear();
        if(updatedListing.getImages() != null){
            for(Image img : updatedListing.getImages()){
                img.setListing(existing);
                existing.getImages().add(img);
            }
        }
        return listingRepository.save(existing);
    }
    @Override
    public void deleteListing(Long id){
        listingRepository.deleteById(id);
    }
}
