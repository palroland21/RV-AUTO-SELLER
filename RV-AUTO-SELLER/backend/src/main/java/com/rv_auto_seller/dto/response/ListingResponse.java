package com.rv_auto_seller.dto.response;

import com.rv_auto_seller.model.User;
import com.rv_auto_seller.model.enums.CarBrand;
import com.rv_auto_seller.model.enums.CarType;
import com.rv_auto_seller.model.enums.FuelType;
import java.util.List;
import java.util.Optional;

import com.rv_auto_seller.model.Listing;
import com.rv_auto_seller.model.Image;
import com.rv_auto_seller.model.enums.TransmissionType;

public class ListingResponse {

    private Long id;
    private String title;
    private List<Image> images;
    private int price;
    private CarBrand brand;
    private String model;
    private CarType type;
    private int yearOfManufacture;
    private FuelType fuelType;
    private int horsePower;
    private String location;
    private String VIN;
    private TransmissionType transmissionType;
    private String description;
    private Long user;

    public ListingResponse(Listing listing) {
        this.id = listing.getId();
        this.title = listing.getTitle();
        this.images = listing.getImages();
        this.price = listing.getPrice();
        this.brand = listing.getBrand();
        this.model = listing.getModel();
        this.type = listing.getType();
        this.yearOfManufacture = listing.getYearOfManufacture();
        this.fuelType = listing.getFuelType();
        this.horsePower = listing.getHorsePower();
        this.location = listing.getLocation();
        this.VIN = listing.getVIN();
        this.description = listing.getDescription();
        this.user = listing.getUser().getId();
    }

    // Getteri
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

    public Long getUser() {
        return user;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }
}
