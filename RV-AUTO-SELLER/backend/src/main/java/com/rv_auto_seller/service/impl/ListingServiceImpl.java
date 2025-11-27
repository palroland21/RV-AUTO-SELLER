package com.rv_auto_seller.service.impl;

import com.rv_auto_seller.dto.request.ListingDTO;
import com.rv_auto_seller.model.Image;
import com.rv_auto_seller.model.Listing;
import com.rv_auto_seller.model.User;
import com.rv_auto_seller.repository.ListingRepository;
import com.rv_auto_seller.repository.UserRepository;
import com.rv_auto_seller.service.ListingService;
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
    public Listing createListingFromDTO(ListingDTO listingDTO) throws IOException {

            Listing listing = new Listing();

            listing.setTitle(listingDTO.getTitle());
            listing.setBrand(listingDTO.getBrand());
            listing.setModel(listingDTO.getModel());
            listing.setType(listingDTO.getType());
            listing.setYearOfManufacture(listingDTO.getYearOfManufacture());
            listing.setHorsePower(listingDTO.getHorsePower());
            listing.setPrice(listingDTO.getPrice());
            listing.setFuelType(listingDTO.getFuelType());
            listing.setTransmissionType(listingDTO.getTransmissionType());
            listing.setVIN(listingDTO.getVin());
            listing.setLocation(listingDTO.getLocation());
            listing.setDescription(listingDTO.getDescription());

            // 2. Setare User (Hardcodat ID 1 pentru testare, sau arunca eroare daca nu exista)
            User user = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Userul default (ID 1) nu a fost gasit in baza de date!"));
            listing.setUser(user);

            // 3. Procesare Imagini
            if (listingDTO.getImages() != null && !listingDTO.getImages().isEmpty()) {
                List<Image> imageEntities = new ArrayList<>();

                // Verificam daca folderul uploads exista
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                for (MultipartFile file : listingDTO.getImages()) {
                    if (!file.isEmpty()) {
                        // Salvam fisierul fizic
                        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                        Path filePath = uploadPath.resolve(fileName);
                        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                        // Cream entitatea Image
                        Image img = new Image();
                        // Atentie: URL-ul trebuie sa fie accesibil din browser.
                        // Va trebui sa configurezi un ResourceHandler pentru "/uploads/**"
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

    // Gestionare imagini la update (logica ta existenta)
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
