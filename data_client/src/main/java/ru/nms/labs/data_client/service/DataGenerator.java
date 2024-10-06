package ru.nms.labs.data_client.service;

import org.springframework.stereotype.Service;
import ru.nms.labs.model.WeatherData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

@Service
public class DataGenerator {

    private final Random random = new Random();

    public WeatherData generate(WeatherData weatherData) {

        WeatherData previsousWeatherData = Optional.ofNullable(weatherData)
                .orElse(new WeatherData(
                        random.nextInt(0, 180),
                        random.nextInt(0, 180),
                        random.nextDouble(-70, 70),
                        random.nextInt(0, 100),
                        random.nextDouble(0, 30),
                        random.nextDouble(94, 104),
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));

        double temperature = previsousWeatherData.getTemperature() + getRandomChange(-0.1, 0.1);
        int humidity = previsousWeatherData.getHumidity() + getRandomChange(-1, 1);
        double windSpeed = previsousWeatherData.getWindSpeed() +  getRandomChange(-0.5, 0.5);
        double pressure = previsousWeatherData.getPressure() + getRandomChange(-0.2, 0.2);

        temperature = Math.max(-70, Math.min(70, temperature));
        humidity = Math.max(0, Math.min(100, humidity));
        windSpeed = Math.max(0, windSpeed);
        pressure = Math.max(94, Math.min(104, pressure));

        return new WeatherData(
                previsousWeatherData.getLon(),
                previsousWeatherData.getLat(),
                temperature,
                humidity,
                windSpeed,
                pressure,
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    }

    private double getRandomChange(double minChange, double maxChange) {
        return minChange + (maxChange - minChange) * random.nextDouble();
    }

    private int getRandomChange(int minChange, int maxChange) {
        return random.nextInt((maxChange - minChange) + 1) + minChange;
    }
}
