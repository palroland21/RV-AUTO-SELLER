package com.rv_auto_seller.controller;

import com.rv_auto_seller.dto.response.AppointmentResponse;
import com.rv_auto_seller.dto.response.FeedbackResponse;
import com.rv_auto_seller.model.Feedback;
import com.rv_auto_seller.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/create")
    public ResponseEntity<FeedbackResponse> createFeedback(@RequestBody Feedback feedback) {
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setUpdatedAt(LocalDateTime.now());

        // validation -> cannot give feedback for yourself
        if(feedback.getFromUser().getId() == feedback.getToUser().getId()) {
            return ResponseEntity.badRequest().build();
        }

        FeedbackResponse response = new FeedbackResponse(feedback);

        feedbackService.createFeedback(feedback);

        System.out.println("Feedback created!");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FeedbackResponse> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        return feedbackService.findById(id)
                .map(existing -> {
                    existing.setRating(feedback.getRating());
                    existing.setDescription(feedback.getDescription());
                    existing.setUpdatedAt(LocalDateTime.now());
                    existing.setFromUser(feedback.getFromUser());
                    existing.setToUser(feedback.getToUser());
                    feedbackService.updateFeedback(existing);
                    return ResponseEntity.ok(new FeedbackResponse(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/feedbacks")
    public ResponseEntity<List<FeedbackResponse>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedback();

        List<FeedbackResponse> feedbackResponseList = new ArrayList<>();


        for(Feedback feedback : feedbacks) {
            feedbackResponseList.add(new FeedbackResponse(feedback));
        }

         return ResponseEntity.ok(feedbackResponseList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return feedbackService.findById(id)
                .map(feedback -> {
                    feedbackService.deleteFeedback(feedback);
                    return ResponseEntity.ok(new FeedbackResponse(feedback));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
