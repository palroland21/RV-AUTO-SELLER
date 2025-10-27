package com.rv_auto_seller.repository;

import com.rv_auto_seller.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing,Long> {

}
