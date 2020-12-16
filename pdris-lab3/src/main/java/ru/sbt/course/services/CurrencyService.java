package ru.sbt.course.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {
    private final RestTemplate restTemplate;

    public CurrencyService (RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public List<Double> getNLatestCurrency(int n) {
        LocalDate today = LocalDate.now();
        List<Double> currencies = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            LocalDate day = today.minusDays(i);
            currencies.add(getDollarValue(day));
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
