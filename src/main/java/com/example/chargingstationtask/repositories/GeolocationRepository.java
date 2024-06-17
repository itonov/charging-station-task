package com.example.chargingstationtask.repositories;

import com.example.chargingstationtask.domain.entities.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeolocationRepository extends JpaRepository<Geolocation, String> {
}
