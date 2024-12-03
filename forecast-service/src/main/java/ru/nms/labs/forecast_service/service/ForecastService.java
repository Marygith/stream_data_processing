package ru.nms.labs.forecast_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nms.labs.forecast_service.model.Forecast;
import ru.nms.labs.forecast_service.model.ForecastId;
import ru.nms.labs.forecast_service.repository.ForecastRepository;
import ru.nms.labs.model.SectorWeather;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForecastService {

    private final ForecastRepository repository;

    public void createOrUpdateForecast(SectorWeather weatherData) {
        log.info("received weather data: {}", weatherData);
        ForecastId forecastId = new ForecastId(weatherData.getSector(), LocalDate.parse(weatherData.getObservationDate(), ISO_DATE_TIME));

        Optional<Forecast> existingForecastOpt = repository.findById(forecastId);

        Forecast newForecast = existingForecastOpt.map(existingForecast ->
                generateForecast(existingForecast, weatherData)
        ).orElse(generateForecast(null, weatherData));
        log.info("new forecast: {}", newForecast);
        repository.save(newForecast);
    }

    public Optional<Forecast> getForecast(int sector, String observationTime) {
        return repository.findById(new ForecastId(sector, LocalDate.parse(observationTime, ISO_DATE_TIME)));
    }

    private Forecast generateForecast(Forecast existingForecast, SectorWeather weatherData) {
        Random random = new Random();

        double temperatureChange = random.nextDouble() * 2 - 1; // Random change: [-1, 1]
        double humidityChange = random.nextInt(3) - 1;          // Random change: [-1, 1, 2]
        double windSpeedChange = random.nextDouble() * 1.5;     // Random increase: [0, 1.5]
        double pressureChange = random.nextDouble() * 0.5;      // Random increase: [0, 0.5]

        Forecast forecast = new Forecast();
        LocalDate nextDay = LocalDate.parse(weatherData.getObservationDate(), ISO_DATE_TIME).plusDays(1);
        forecast.setId(new ForecastId(weatherData.getSector(), nextDay));

        if (existingForecast != null) {
            log.info("sector weather with this sector and observation time already exists, starting update");
            forecast.setTemperature((existingForecast.getTemperature() + weatherData.getTemperature()) / 2 + temperatureChange);
            forecast.setHumidity((existingForecast.getHumidity() + weatherData.getHumidity()) / 2 + (int) humidityChange);
            forecast.setWindSpeed((existingForecast.getWindSpeed() + weatherData.getWindSpeed()) / 2 + windSpeedChange);
            forecast.setPressure((existingForecast.getPressure() + weatherData.getPressure()) / 2 + pressureChange);
        } else {
            log.info("sector weather with this sector and observation time does not exist, starting creation");

            forecast.setTemperature(weatherData.getTemperature() + temperatureChange);
            forecast.setHumidity(weatherData.getHumidity() + (int) humidityChange);
            forecast.setWindSpeed(weatherData.getWindSpeed() + windSpeedChange);
            forecast.setPressure(weatherData.getPressure() + pressureChange);
        }

        return forecast;
    }
}