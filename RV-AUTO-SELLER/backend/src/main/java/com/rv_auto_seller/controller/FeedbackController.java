package com.rv_auto_seller.controller;

import com.rv_auto_seller.dto.response.FeedbackResponse;
import com.rv_auto_seller.model.Feedback;
import com.rv_auto_seller.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/update")
    public ResponseEntity<FeedbackResponse> updateFeedback(@RequestBody Feedback feedback) {

        List<Feedback> feedBackList = feedbackService.getAllFeedback();
        boolean exists = feedBackList.stream().anyMatch(fb -> fb.getId().equals(feedback.getId()));

        if (!exists) {
            return ResponseEntity.badRequest().build();
        }

        feedback.setUpdatedAt(LocalDateTime.now());
        feedbackService.updateFeedback(feedback);

        FeedbackResponse response = new FeedbackResponse(feedback);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFeedback(@RequestBody Feedback feedback) {
        feedbackService.deleteFeedback(feedback);
        return ResponseEntity.ok().build();
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

}
