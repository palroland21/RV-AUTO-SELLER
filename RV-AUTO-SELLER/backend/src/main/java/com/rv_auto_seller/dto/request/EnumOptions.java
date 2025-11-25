package com.rv_auto_seller.dto.request;

import lombok.Data;

import java.util.List;

public class EnumOptions {
    private List<String> brands;
    private List<String> carTypes;
    private List<String> fuelTypes;
    private List<String> transmissionTypes;

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public void setCarTypes(List<String> carTypes) {
        this.carTypes = carTypes;
    }

    public void setFuelTypes(List<String> fuelTypes) {
        this.fuelTypes = fuelTypes;
    }

    public void setTransmissionTypes(List<String> transmissionTypes) {
        this.transmissionTypes = transmissionTypes;
    }

    public List<String> getBrands() {
        return brands;
    }

    public List<String> getCarTypes() {
        return carTypes;
    }

    public List<String> getFuelTypes() {
        return fuelTypes;
    }

    public List<String> getTransmissionTypes() {
        return transmissionTypes;
    }
}
