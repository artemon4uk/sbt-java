package ru.sbt.course.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {
    private static Currency currency;
    private final static LocalDate date = LocalDate.of(2020, 10, 15);
    private final static double value = 10.0;
    @BeforeAll
    public static void setUp() {
        currency = new Currency(date, value);
    }

    @Test
    public void getDate() {
        assertEquals(currency.getDate(), date);
    }

    @Test
    public void getValue() {
        assertEquals(currency.getValue(), value);
    }
}