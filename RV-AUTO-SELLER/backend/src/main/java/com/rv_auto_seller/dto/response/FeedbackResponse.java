package com.rv_auto_seller.dto.response;

import com.rv_auto_seller.model.User;

import java.time.LocalDateTime;


public class FeedbackResponse {
    private User fromUser;
    private User toUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedbackResponse(User fromUser, User toUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
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
