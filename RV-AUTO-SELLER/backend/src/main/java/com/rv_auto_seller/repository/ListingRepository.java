package com.rv_auto_seller.repository;

import com.rv_auto_seller.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing,Long> {
//
//    List<Listing> findByTitle(String title);
//    List<Listing> findByBrand(String brand);
//    List<Listing> findByModel(String model);
//    List<Listing> findByType(String type);
//    List<Listing> findByPrice(int price);
//    List<Listing> findByDescription(String description);
//    List<Listing> findByYear(int year);
}
