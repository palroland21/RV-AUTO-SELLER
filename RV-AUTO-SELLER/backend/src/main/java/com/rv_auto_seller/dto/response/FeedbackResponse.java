package com.rv_auto_seller.dto.response;

import com.rv_auto_seller.model.Feedback;

import java.time.LocalDateTime;


public class FeedbackResponse {
    private Long id;
    private Long fromUser;
    private Long toUser;
    private int rating;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedbackResponse(Feedback feedback) {
        this.id = feedback.getId();
        this.fromUser = feedback.getFromUser().getId();
        this.toUser = feedback.getToUser().getId();
        this.rating = feedback.getRating();
        this.description = feedback.getDescription();
        this.createdAt = feedback.getCreatedAt();
        this.updatedAt = feedback.getUpdatedAt();
    }

    public Long getFromUser() {
        return fromUser;
    }

    public void setFromUser(Long fromUser) {
        this.fromUser = fromUser;
    }

    public Long getToUser() {
        return toUser;
    }

    public void setToUser(Long toUser) {
        this.toUser = toUser;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
