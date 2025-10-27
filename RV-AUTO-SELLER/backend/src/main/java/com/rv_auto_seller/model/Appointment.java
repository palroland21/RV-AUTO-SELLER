package com.rv_auto_seller.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private LocalDateTime date;

    @Column(name = "DETAILS", nullable = true, length = 50)
    private String details;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID", nullable = false)
    private User seller;
}
