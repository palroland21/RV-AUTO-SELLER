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
    private Long userId;
    private String username;
    private String telephone;

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
        this.userId = listing.getUser().getId();

        if(listing.getUser() != null) {
            this.username = listing.getUser().getUsername();
            this.telephone = listing.getUser().getTelephone();
        }else{
            this.username = "Unknown Seller";
            this.telephone = null;
        }

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

    public Long getUserId() {
        return userId;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

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

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
