package com.rv_auto_seller.repository;

import com.rv_auto_seller.model.Listing;
import com.rv_auto_seller.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing,Long> {
    List<Listing> findAllByUser(User user);
}
