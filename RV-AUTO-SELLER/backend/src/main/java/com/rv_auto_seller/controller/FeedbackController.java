package com.rv_auto_seller.controller;

import com.rv_auto_seller.dto.response.FeedbackResponse;
import com.rv_auto_seller.model.Feedback;
import com.rv_auto_seller.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

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

        FeedbackResponse response = new FeedbackResponse(
                feedback.getFromUser(),
                feedback.getToUser(),
                feedback.getCreatedAt(),
                feedback.getUpdatedAt()
        );


        feedbackService.createFeedback(feedback);

        System.out.println("Feedback created!");
        return ResponseEntity.ok(response);
    }

}
