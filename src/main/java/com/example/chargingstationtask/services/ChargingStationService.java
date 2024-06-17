package com.example.chargingstationtask.services;

import com.example.chargingstationtask.domain.entities.ChargingStation;

import java.util.List;

public interface ChargingStationService {
    ChargingStation create(String id, String zipcode, double latitude, double longitude);

    ChargingStation findById(String id);

    List<ChargingStation> findByZipcode(String zipcode);

    List<ChargingStation> findByGeolocationPerimeter(double latitude, double longitude, double perimeter);
}
