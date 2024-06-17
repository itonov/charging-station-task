package com.example.chargingstationtask.services;

import com.example.chargingstationtask.domain.entities.ChargingStation;
import com.example.chargingstationtask.repositories.ChargingStationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargingStationServiceImpl implements ChargingStationService {
    private final ChargingStationRepository chargingStationRepository;

    private final GeolocationService geolocationService;

    public ChargingStationServiceImpl(ChargingStationRepository chargingStationRepository, GeolocationService geolocationService) {
        this.chargingStationRepository = chargingStationRepository;
        this.geolocationService = geolocationService;
    }

    @Override
    public ChargingStation create(String id, String zipcode, double latitude, double longitude) {
        ChargingStation station = new ChargingStation();
        station.setId(id);
        station.setZipcode(zipcode);
        station.setGeolocation(this.geolocationService.create(latitude, longitude));

        return this.chargingStationRepository.save(station);
    }

    @Override
    public ChargingStation findById(String id) {
        return this.chargingStationRepository.findById(id).orElse(null);
    }

    @Override
    public List<ChargingStation> findByZipcode(String zipcode) {
        return this.chargingStationRepository.findChargingStationByZipcode(zipcode);
    }

    @Override
    public List<ChargingStation> findByGeolocationPerimeter(double latitude, double longitude, double perimeter) {
        return this.chargingStationRepository.findByGeolocationPerimeter(latitude, longitude, perimeter);
    }
}
