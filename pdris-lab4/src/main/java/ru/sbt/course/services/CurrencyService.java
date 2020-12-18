package ru.sbt.course.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sbt.course.entities.Currency;
import ru.sbt.course.repositories.CurrencyRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    public CurrencyService(RestTemplateBuilder restTemplateBuilder, CurrencyRepository currencyRepository) {
        restTemplate = restTemplateBuilder.build();
        this.currencyRepository = currencyRepository;
    }

    public List<Double> getNLatestCurrency(int n) {
        LocalDate today = LocalDate.now();
        List<Double> currencies = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            LocalDate day = today.minusDays(i);
            Optional<Currency> currencyFromRepo = currencyRepository.findByDate(day);
            if (currencyFromRepo.isPresent()) {
                System.out.println("Currency is taken from BD");
                currencies.add(currencyFromRepo.get().getValue());
            } else {
                System.out.println("Currency is taken from response");
                double value = getDollarValue(day);
                currencies.add(value);
                currencyRepository.save(new Currency(day, value));
            }
        }
        return currencies;
    }

    private double getDollarValue(LocalDate day) {
        String body = getResponse(getUrl(day));
        String startPattern = "Доллар США</Name><Value>";
        int startIndex = body.indexOf(startPattern) + startPattern.length();
        body = body.substring(startIndex);
        int endIndex = body.indexOf("</Value>");
        return Double.parseDouble(body.substring(0, endIndex).replace(",", "."));
    }

    private String getResponse(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    private String getUrl(LocalDate date) {
        return "http://www.cbr.ru/scripts/XML_daily.asp?date_req="
                + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
