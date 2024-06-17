package com.example.chargingstationtask.domain.web;

public record CreateChargingStationRequest(String id, String zipcode, double latitude, double longitude) {
}
