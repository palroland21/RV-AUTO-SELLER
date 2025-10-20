package com.rv_auto_seller.repository;

import com.rv_auto_seller.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing,Long> {

}
