package com.rv_auto_seller.service.impl;

import com.rv_auto_seller.dto.request.FeedbackRequest;
import com.rv_auto_seller.dto.response.FeedbackResponse;
import com.rv_auto_seller.model.Feedback;
import com.rv_auto_seller.model.User;
import com.rv_auto_seller.repository.FeedbackRepository;
import com.rv_auto_seller.repository.UserRepository;
import com.rv_auto_seller.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
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
        feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public FeedbackResponse addFeedback(FeedbackRequest request, String fromUsername) {
        User fromUser = userRepository.findByUsername(fromUsername)
                .orElseThrow(() -> new RuntimeException("User not found: " + fromUsername));

        User toUser = userRepository.findById(request.getToUserId())
                .orElseThrow(() -> new RuntimeException("Target user not found with ID: " + request.getToUserId()));

        if (fromUser.getId().equals(toUser.getId())) {
            throw new RuntimeException("You cannot review yourself.");
        }

        Feedback feedback = new Feedback();
        feedback.setFromUser(fromUser);
        feedback.setToUser(toUser);
        feedback.setRating(request.getRating());
        feedback.setDescription(request.getDescription());

        LocalDateTime now = LocalDateTime.now();
        feedback.setCreatedAt(now);
        feedback.setUpdatedAt(now);

        Feedback saved = feedbackRepository.save(feedback);

        return new FeedbackResponse(saved);
    }

    @Override
    public List<FeedbackResponse> getMyReceivedFeedback(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return feedbackRepository.findAllByToUserIdOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(FeedbackResponse::new)
                .collect(Collectors.toList());
    }
}
