package ru.sbt.course.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyServiceTest {
    @Autowired
    private CurrencyService currencyService;

    @Test
    public void getNLatestCurrency() {
        int n = 3;
        assertEquals(currencyService.getNLatestCurrency(n).size(), n);
    }
}