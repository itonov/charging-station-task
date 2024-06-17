package com.example.chargingstationtask;

import com.example.chargingstationtask.controllers.ChargingStationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private ChargingStationController chargingStationController;

    @Test
    public void contextLoads() {
        assertThat(chargingStationController).isNotNull();
    }
}
