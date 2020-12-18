package ru.sbt.course.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sbt.course.entities.Currency;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CurrencyRepositoryTest {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    public void findByDateTest() {
        LocalDate date1 = LocalDate.of(2020, 12, 10);
        LocalDate date2 = LocalDate.of(2020, 12, 11);
        LocalDate date3 = LocalDate.of(2020, 12, 12);
        Currency currency1 = new Currency(date1, 50.0);
        Currency currency2 = new Currency(date2, 60.0);
        Currency currency3 = new Currency(date3, 70.0);
        currencyRepository.save(currency1);
        currencyRepository.save(currency2);
        currencyRepository.save(currency3);

        Optional<Currency> currencyFromRepo1 = currencyRepository.findByDate(date1);
        Optional<Currency> currencyFromRepo2 = currencyRepository.findByDate(date2);
        Optional<Currency> currencyNotFromRepo = currencyRepository.findByDate(date3.plusDays(2));

        assertTrue(currencyFromRepo1.isPresent());
        assertEquals(currency1.getValue(), currencyFromRepo1.get().getValue());
        assertTrue(currencyFromRepo2.isPresent());
        assertEquals(currency2.getValue(), currencyFromRepo2.get().getValue());
        assertFalse(currencyNotFromRepo.isPresent());
    }
}