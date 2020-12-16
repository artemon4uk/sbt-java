package ru.sbt.course.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.course.services.CurrencyService;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency")
    public List<Double> getCurrencyHistory(@RequestParam @Min(1) int n) {
        return currencyService.getNLatestCurrency(n);
    }
}
