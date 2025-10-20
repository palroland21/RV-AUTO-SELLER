package com.rv_auto_seller.repository;

import com.rv_auto_seller.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
