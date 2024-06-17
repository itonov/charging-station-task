package com.example.chargingstationtask.services;

import com.example.chargingstationtask.domain.entities.Geolocation;
import com.example.chargingstationtask.repositories.GeolocationRepository;
import org.springframework.stereotype.Service;

@Service
public class GeolocationServiceImpl implements GeolocationService{
    private final GeolocationRepository geolocationRepository;

    public GeolocationServiceImpl(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    @Override
    public Geolocation create(double latitude, double longitude) {
        Geolocation geolocation = new Geolocation();
        geolocation.setLatitude(latitude);
        geolocation.setLongitude(longitude);

        return this.geolocationRepository.save(geolocation);
    }
}
