package ru.sbt.course.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PredictServiceTest {
    @Autowired
    private PredictService predictService;

    @Test
    public void predictWithCity() {
        predictService.fit();
        double value = predictService.predict("London");
        assertTrue(value > 50);
        assertTrue(value < 100);
    }

    @Test
    public void predictWithoutCity() {
        predictService.fit();
        double value = predictService.predict();
        assertTrue(value > 50);
        assertTrue(value < 100);
    }
}