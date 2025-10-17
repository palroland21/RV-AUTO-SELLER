package com.rv_auto_seller.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "URL", nullable = false, length = 500)
    private String url;

    @ManyToOne
    @JoinColumn(name = "listing_id", nullable = false)
    @JsonBackReference
    private Listing listing;
}
