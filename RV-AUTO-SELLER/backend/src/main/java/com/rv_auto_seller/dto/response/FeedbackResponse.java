package com.rv_auto_seller.dto.response;

import java.time.LocalDateTime;


public class FeedbackResponse {
    private Long id;
    private Long fromUser;
    private Long toUser;
    private int rating;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedbackResponse(Long id, Long  fromUser, Long toUser, int rating, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.rating = rating;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
