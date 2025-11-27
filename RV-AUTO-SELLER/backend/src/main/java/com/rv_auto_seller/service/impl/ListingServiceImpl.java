package com.rv_auto_seller.service.impl;

import com.rv_auto_seller.dto.request.ListingDTO;
import com.rv_auto_seller.model.Image;
import com.rv_auto_seller.model.Listing;
import com.rv_auto_seller.model.User;
import com.rv_auto_seller.repository.ListingRepository;
import com.rv_auto_seller.repository.UserRepository;
import com.rv_auto_seller.service.ListingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListingServiceImpl implements ListingService {

   private final ListingRepository listingRepository;
   private final UserRepository userRepository;
   private final String UPLOAD_DIR = "/uploads";

    public ListingServiceImpl(ListingRepository listingRepository, UserRepository userRepository) {
        this.listingRepository = listingRepository;
        this.userRepository = userRepository;
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
    public Listing createListingFromDTO(ListingDTO dto) throws IOException {
        //System.out.println("Am ajuns in createListingDTO!!!!");
        Listing listing = new Listing();

        listing.setTitle(dto.getTitle());
        listing.setBrand(dto.getBrand());
        listing.setModel(dto.getModel());
        listing.setType(dto.getType());
        listing.setYearOfManufacture(dto.getYearOfManufacture());
        listing.setHorsePower(dto.getHorsePower());
        listing.setPrice(dto.getPrice());
        listing.setFuelType(dto.getFuelType());
        listing.setTransmissionType(dto.getTransmissionType());
        listing.setVIN(dto.getVin());
        listing.setLocation(dto.getLocation());
        listing.setDescription(dto.getDescription());

        //System.out.println("Username primit din Frontend: " + dto.getUsername()); // debug

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("Security error: You are not authenticated!");
        }

        String usernameDinToken = auth.getName();
        System.out.println("User detected from Token: " + usernameDinToken);

        User currentUser = userRepository.findByUsername(usernameDinToken)
                .orElseThrow(() -> new RuntimeException("User '" + usernameDinToken + "' does not exist in database!"));

        System.out.println("User found n DB with ID: " + currentUser.getId());

        listing.setUser(currentUser);

        // image processing
        if (dto.getImages() != null && !dto.getImages().isEmpty()) {
            List<Image> imageEntities = new ArrayList<>();

            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            for (MultipartFile file : dto.getImages()) {
                if (!file.isEmpty()) {
                    String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                    Image img = new Image();
                    img.setUrl("/uploads/" + fileName);
                    img.setListing(listing);
                    imageEntities.add(img);
                }
            }
            listing.setImages(imageEntities);
        }

        return listingRepository.save(listing);
    }

@Override
public Listing updateListing(Long id, Listing updatedListing) {
    Listing existing = listingRepository.findById(id).orElseThrow(() -> new RuntimeException("Listing not found"));

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
    existing.setTransmissionType(updatedListing.getTransmissionType());
    existing.setDescription(updatedListing.getDescription());

    //handle images on update
    existing.getImages().clear();
    if (updatedListing.getImages() != null) {
        for (Image img : updatedListing.getImages()) {
            img.setListing(existing);
            existing.getImages().add(img);
        }
    }
    return listingRepository.save(existing);
}

@Override
public void deleteListing(Long id) {
    listingRepository.deleteById(id);
}
}
