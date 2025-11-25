package com.rv_auto_seller.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rv_auto_seller.model.enums.CarBrand;
import com.rv_auto_seller.model.enums.CarType;
import com.rv_auto_seller.model.enums.FuelType;
import com.rv_auto_seller.model.enums.TransmissionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/*
{
  "title": "Volkswagen Golf 7 2.0 TDI",
  "images": [
    {
      "id": 101,
      "url": "https://example.com/images/golf1.jpg"
    },
    {
      "id": 102,
      "url": "https://example.com/images/golf2.jpg"
    }
  ],
  "price": 11500,
  "brand": "VOLKSWAGEN",
  "model": "Golf 7",
  "type": "SEDAN",
  "yearOfManufacture": 2017,
  "fuelType": "DIESEL",
  "horsePower": 150,
  "location": "Cluj-Napoca",
  "vin": "WVWZZZ1KZHW123456",
  "description": "Masina faina! Cumparati",
  "user": {
    "id": 5,
    "firstName": "Andrei",
    "lastName": "Popescu",
    "username": "andrei.pop",
    "email": "andrei.pop@example.com",
    "role": "USER"
  }
}

 */

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Listings")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 50)
    private String title;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Image> images = new ArrayList<>();

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

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSMISSION_TYPE", nullable = true)
    private TransmissionType transmissionType;

    @Column(name = "DESCRIPTION",  nullable = false, length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Image> getImages() {
        return images;
    }

    public int getPrice() {
        return price;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public CarType getType() {
        return type;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public String getLocation() {
        return location;
    }

    public String getVIN() {
        return VIN;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }
}
