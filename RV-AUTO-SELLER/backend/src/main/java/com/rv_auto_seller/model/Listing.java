package com.rv_auto_seller.model;

import com.rv_auto_seller.model.enums.CarBrand;
import com.rv_auto_seller.model.enums.CarType;
import com.rv_auto_seller.model.enums.FuelType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Listings")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 50)
    private String title;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "BRAND", nullable = false)
    private CarBrand brand;

    @Column(name = "MODEL", nullable = false, length = 50)
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private CarType type;

    @Column(name = "YEAR", nullable = false)
    private int yearOfManufacture;

    @Enumerated(EnumType.STRING)
    @Column(name = "FUEL_TYPE", nullable = false)
    private FuelType fuelType;

    @Column(name = "HORSE_POWER", nullable = false)
    private int horsePower;

    @Column(name = "Location", nullable = false, length = 50)
    private String location;

    @Column(name = "VIN", nullable = true, unique = true, length = 17)
    private String VIN;

    @Column(name = "DESCRIPTION",  nullable = false, length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
