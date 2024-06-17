package com.example.chargingstationtask.repositories;

import com.example.chargingstationtask.domain.entities.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChargingStationRepository extends JpaRepository<ChargingStation, String> {
    List<ChargingStation> findChargingStationByZipcode(String zipCode);

    @Query("SELECT DISTINCT station " +
            "FROM ChargingStation AS station " +
            "LEFT JOIN Geolocation ON station.geolocation.id IN " +
            "(SELECT g.id " +
            "FROM Geolocation AS g " +
            "WHERE ( 6371 * acos( cos( RADIANS(?1) ) * cos( RADIANS( g.latitude ) ) * cos( RADIANS( g.longitude ) - RADIANS(?2) ) + sin( RADIANS(?1) ) * sin( RADIANS( g.latitude ) ) ) ) <= ?3)")
    List<ChargingStation> findByGeolocationPerimeter(double latitude, double longitude, double perimeter);
}
