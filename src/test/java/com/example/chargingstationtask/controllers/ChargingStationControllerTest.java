package com.example.chargingstationtask.controllers;

import com.example.chargingstationtask.domain.entities.ChargingStation;
import com.example.chargingstationtask.domain.entities.Geolocation;
import com.example.chargingstationtask.services.ChargingStationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ChargingStationControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ChargingStationService chargingStationService;

    private static ChargingStation chargingStation;

    @BeforeAll
    static void setUpBeforeTest() throws Exception {
        Geolocation geolocation = new Geolocation();
        geolocation.setId("test");
        geolocation.setLatitude(1.0);
        geolocation.setLongitude(2.0);

        chargingStation = new ChargingStation();
        chargingStation.setId("testId");
        chargingStation.setZipcode("testZipcode");
        chargingStation.setGeolocation(geolocation);
    }

    @Test
    public void createChargingStationShouldReturnResult() throws Exception {
        when(chargingStationService.create("testId", "testZipcode", 1.0, 2.0)).thenReturn(chargingStation);

        mvc.perform(post("/stations/create")
                        .accept(MediaType.APPLICATION_JSON).content("{\"id\": \"testId\",\"latitude\": 1.0, \"longitude\":  2.0,\"zipcode\": \"testZipcode\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\": \"testId\",\"geolocation\": {\"id\": \"test\", \"latitude\": 1.0, \"longitude\":  2.0},\"zipcode\": \"testZipcode\"}"));
    }

    @Test
    public void findChargingStationByIdShouldReturnResult() throws Exception {
        when(chargingStationService.findById("testId")).thenReturn(chargingStation);

        mvc.perform(get("/stations/testId"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\": \"testId\",\"geolocation\": {\"id\": \"test\", \"latitude\": 1.0, \"longitude\":  2.0},\"zipcode\": \"testZipcode\"}"));
    }

    @Test
    public void findChargingStationByIdShouldReturnNotFound() throws Exception {
        when(chargingStationService.findById("testId")).thenReturn(null);

        mvc.perform(get("/stations/testId"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findChargingStationByZipcodeShouldReturnResult() throws Exception {
        when(chargingStationService.findByZipcode("testZipcode")).thenReturn(List.of(chargingStation));

        mvc.perform(get("/stations?zipcode=testZipcode"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\": \"testId\",\"geolocation\": {\"id\": \"test\", \"latitude\": 1.0, \"longitude\":  2.0},\"zipcode\": \"testZipcode\"}]"));
    }

    @Test
    public void findChargingStationByZipcodeShouldReturnNotFound() throws Exception {
        when(chargingStationService.findByZipcode("testZipcode")).thenReturn(List.of());

        mvc.perform(get("/stations?zipcode=testZipcode"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findChargingStationByGeolocationPerimeterShouldReturnResult() throws Exception {
        when(chargingStationService.findByGeolocationPerimeter(1.0, 2.0, 0)).thenReturn(List.of(chargingStation));

        mvc.perform(get("/stations?latitude=1.0&longitude=2.0&perimeter=0"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\": \"testId\",\"geolocation\": {\"id\": \"test\", \"latitude\": 1.0, \"longitude\":  2.0},\"zipcode\": \"testZipcode\"}]"));
    }

    @Test
    public void findChargingStationByGeolocationPerimeterShouldReturnNotFound() throws Exception {
        when(chargingStationService.findByGeolocationPerimeter(1.0, 2.0, 0)).thenReturn(List.of());

        mvc.perform(get("/stations?latitude=1.0&longitude=2.0&perimeter=0"))
                .andExpect(status().isNotFound());
    }
}