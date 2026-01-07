package com.rv_auto_seller.service;

import com.rv_auto_seller.dto.request.FeedbackRequest;
import com.rv_auto_seller.dto.response.FeedbackResponse;
import com.rv_auto_seller.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);
    void deleteFeedback(Feedback feedback);
    void updateFeedback(Feedback feedback);
    List<Feedback> getAllFeedback();
    Optional<Feedback> findById(Long id);

    FeedbackResponse addFeedback(FeedbackRequest request, String fromUsername);
    List<FeedbackResponse> getMyReceivedFeedback(String username);
}
