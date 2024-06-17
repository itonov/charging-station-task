package com.example.chargingstationtask.controllers;

import com.example.chargingstationtask.domain.entities.ChargingStation;
import com.example.chargingstationtask.domain.web.CreateChargingStationRequest;
import com.example.chargingstationtask.services.ChargingStationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("stations")
public class ChargingStationController {
    private static final String STATIONS_URI = "localhost:8080/stations/";

    private final ChargingStationService chargingStationService;

    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    @Operation(summary = "Create new charging station")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created new charging station",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ChargingStation.class))})})
    @PostMapping("/create")
    public ResponseEntity<ChargingStation> createStation(@RequestBody CreateChargingStationRequest request) {
        ChargingStation station = this.chargingStationService.create(
                request.id(),
                request.zipcode(),
                request.latitude(),
                request.longitude()
        );

        return ResponseEntity.created(URI.create(STATIONS_URI + station.getId())).body(station);
    }

    @Operation(summary = "Find charging station by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found charging station",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ChargingStation.class))}),
            @ApiResponse(responseCode = "404", description = "Charging station not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<ChargingStation> getStationById(@PathVariable String id) {
        ChargingStation result = this.chargingStationService.findById(id);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Find charging stations by zipcode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found charging stations",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ChargingStation.class)))}),
            @ApiResponse(responseCode = "404", description = "Charging stations not found",
                    content = @Content)})
    @GetMapping(params = "zipcode")
    public ResponseEntity<List<ChargingStation>> getStationByZipcode(@RequestParam String zipcode) {
        List<ChargingStation> result = this.chargingStationService.findByZipcode(zipcode);

        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Find charging stations by specified distance in km around a given geolocation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found charging stations",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ChargingStation.class)))}),
            @ApiResponse(responseCode = "404", description = "Charging stations not found",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<ChargingStation>> getStationsByGeolocationPerimeter(@RequestParam double latitude, @RequestParam double longitude, @RequestParam int perimeter) {
        List<ChargingStation> result = this.chargingStationService.findByGeolocationPerimeter(latitude, longitude, perimeter);

        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }
}
