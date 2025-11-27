package com.rv_auto_seller.controller;

import com.rv_auto_seller.dto.request.EnumOptions;
import com.rv_auto_seller.model.enums.CarBrand;
import com.rv_auto_seller.model.enums.CarType;
import com.rv_auto_seller.model.enums.FuelType;
import com.rv_auto_seller.model.enums.TransmissionType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utils")
public class EnumController {
    @GetMapping("/enums")
    public ResponseEntity<EnumOptions> getEnums() {
        EnumOptions options = new EnumOptions();

        options.setBrands(Arrays.stream(CarBrand.values()).map(Enum::name).collect(Collectors.toList()));
        options.setCarTypes(Arrays.stream(CarType.values()).map(Enum::name).collect(Collectors.toList()));
        options.setFuelTypes(Arrays.stream(FuelType.values()).map(Enum::name).collect(Collectors.toList()));
        options.setTransmissionTypes(Arrays.stream(TransmissionType.values()).map(Enum::name).collect(Collectors.toList()));

        return ResponseEntity.ok(options);
    }
}
