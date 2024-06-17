package com.example.chargingstationtask.services;

import com.example.chargingstationtask.domain.entities.ChargingStation;
import com.example.chargingstationtask.repositories.ChargingStationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ChargingStationServiceImplTest {
    @InjectMocks
    private ChargingStationServiceImpl chargingStationService;

    @Mock
    private ChargingStationRepository chargingStationRepository;

    @Test
    void testFindByIdShouldReturnChargingStation() {
        ChargingStation chargingStation = new ChargingStation();

        when(chargingStationRepository.findById("testId")).thenReturn(Optional.of(chargingStation));

        ChargingStation result = chargingStationService.findById("testId");

        assertEquals(chargingStation, result);
    }

    @Test
    void testFindByIdShouldReturnNull() {
        when(chargingStationRepository.findById("testId")).thenReturn(Optional.empty());

        ChargingStation result = chargingStationService.findById("testId");

        assertNull(result);
    }

    @Test
    void testFindByZipcodeShouldReturnChargingStation() {
        List<ChargingStation> chargingStationList = List.of(new ChargingStation());

        when(chargingStationRepository.findChargingStationByZipcode("testZipcode")).thenReturn(chargingStationList);

        List<ChargingStation> result = chargingStationService.findByZipcode("testZipcode");

        assertEquals(chargingStationList, result);
    }

    @Test
    void testFindByZipcodeShouldReturnEmptyList() {
        when(chargingStationRepository.findChargingStationByZipcode("testZipcode")).thenReturn(List.of());

        List<ChargingStation> result = chargingStationService.findByZipcode("testZipcode");

        assertEquals(List.of(), result);
    }

    @Test
    void testFindByGeolocationPerimeterShouldReturnChargingStation() {
        List<ChargingStation> chargingStationList = List.of(new ChargingStation());

        when(chargingStationRepository.findByGeolocationPerimeter(1.0, 2.0, 3)).thenReturn(chargingStationList);

        List<ChargingStation> result = chargingStationService.findByGeolocationPerimeter(1.0, 2.0, 3);

        assertEquals(chargingStationList, result);
    }

    @Test
    void testFindByGeolocationPerimeterShouldReturnEmptyList() {
        when(chargingStationRepository.findByGeolocationPerimeter(1.0, 2.0, 3)).thenReturn(List.of());

        List<ChargingStation> result = chargingStationService.findByGeolocationPerimeter(1.0, 2.0, 3);

        assertEquals(List.of(), result);
    }
}