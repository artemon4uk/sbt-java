package ru.sbt.course.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sbt.course.entities.Weather;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class WeatherRepositoryTest {
    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    public void findByCityAndDateTest() {
        LocalDate date1 = LocalDate.of(2020, 12, 10);
        LocalDate date2 = LocalDate.of(2020, 12, 11);
        LocalDate date3 = LocalDate.of(2020, 12, 12);
        String city1 = "Moscow";
        String city2 = "London";
        String city3 = "Paris";
        Weather weather1 = new Weather(city1, date1, 0.0, 20.0, 10.0);
        Weather weather2 = new Weather(city2, date2, -10.0, 10.0, 0.0);
        Weather weather3 = new Weather(city3, date3, 10.0, 30.0, 20.0);
        weatherRepository.save(weather1);
        weatherRepository.save(weather2);
        weatherRepository.save(weather3);

        Optional<Weather> weatherFromRepo1 = weatherRepository.findByCityAndDate(city1, date1);
        Optional<Weather> weatherFromRepo2 = weatherRepository.findByCityAndDate(city2, date2);
        Optional<Weather> weatherNotFromRepo = weatherRepository.findByCityAndDate(city3, date3.plusDays(2));

        assertTrue(weatherFromRepo1.isPresent());
        assertEquals(city1, weatherFromRepo1.get().getCity());
        assertEquals(date1, weatherFromRepo1.get().getDate());
        assertEquals(10.0, weatherFromRepo1.get().getAvgTemperature());
        assertTrue(weatherFromRepo2.isPresent());
        assertEquals(city2, weatherFromRepo2.get().getCity());
        assertEquals(date2, weatherFromRepo2.get().getDate());
        assertEquals(0.0, weatherFromRepo2.get().getAvgTemperature());
        assertFalse(weatherNotFromRepo.isPresent());
    }
}