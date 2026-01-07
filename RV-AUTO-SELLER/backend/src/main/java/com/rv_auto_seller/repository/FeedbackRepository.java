package com.rv_auto_seller.repository;

import com.rv_auto_seller.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByToUserIdOrderByCreatedAtDesc(Long toUserId);


}
