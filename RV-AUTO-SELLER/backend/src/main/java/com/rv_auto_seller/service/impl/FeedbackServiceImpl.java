package com.rv_auto_seller.service.impl;

import com.rv_auto_seller.model.Feedback;
import com.rv_auto_seller.repository.FeedbackRepository;
import com.rv_auto_seller.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Feedback feedback) {
       feedbackRepository.delete(feedback);
    }

    @Override
    public void updateFeedback(Feedback feedback) {

        for(Feedback f : feedbackRepository.findAll()) {
            if(f.getId().equals(feedback.getId())) {
                f.setFromUser(feedback.getFromUser());
                f.setToUser(feedback.getToUser());
                f.setRating(feedback.getRating());
                f.setDescription(feedback.getDescription());

                // Set update data
                f.setUpdatedAt(LocalDateTime.now());
            }
        }

    }
}
