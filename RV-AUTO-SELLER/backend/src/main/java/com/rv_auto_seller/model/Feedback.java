package com.rv_auto_seller.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FROM_USER_ID", nullable = false)
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "TO_USER_ID", nullable = false)
    private User toUser;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
}
