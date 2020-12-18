package ru.sbt.course.controllers;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.course.services.PredictService;

@RestController
public class PredictController {
    private final PredictService predictService;

    public PredictController(PredictService predictService) {
        this.predictService = predictService;
    }

    @GetMapping("/predict")
    public double predictCurrency(@RequestParam @Nullable String city) {
        predictService.fit();
        if (city == null) {
            return predictService.predict();
        }
        return predictService.predict(city);
    }
}
