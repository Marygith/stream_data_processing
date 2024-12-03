package ru.nms.labs.forecast_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nms.labs.forecast_service.mapper.ForecastMapper;
import ru.nms.labs.forecast_service.service.ForecastService;
import ru.nms.labs.model.SectorWeather;

@RestController
@RequestMapping("/forecast")
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;
    private final ForecastMapper forecastMapper;

    @PostMapping
    public void createOrUpdateForecast(@RequestBody SectorWeather weatherData) {
        forecastService.createOrUpdateForecast(weatherData);
    }

    @GetMapping
    public ResponseEntity<SectorWeather> getForecast(
            @RequestParam int sector,
            @RequestParam String observationTime
    ) {

        return forecastService.getForecast(sector, observationTime)
                .map(forecastMapper::toSectorWeather)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
