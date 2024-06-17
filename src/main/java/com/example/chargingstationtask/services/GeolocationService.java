package com.example.chargingstationtask.services;

import com.example.chargingstationtask.domain.entities.Geolocation;

public interface GeolocationService {
    Geolocation create(double latitude, double longitude);
}
