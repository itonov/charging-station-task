package com.example.chargingstationtask.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ChargingStation {
    private String id;

    private Geolocation geolocation;

    private String zipcode;

    public ChargingStation() {
    }

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne
    public Geolocation getGeolocation() {
        return this.geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
