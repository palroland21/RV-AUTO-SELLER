package com.rv_auto_seller.service;

import com.rv_auto_seller.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);
    void deleteFeedback(Feedback feedback);
    void updateFeedback(Feedback feedback);
    List<Feedback> getAllFeedback();
}
