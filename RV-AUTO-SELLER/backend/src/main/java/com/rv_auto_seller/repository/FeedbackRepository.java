package com.rv_auto_seller.repository;

import com.rv_auto_seller.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
