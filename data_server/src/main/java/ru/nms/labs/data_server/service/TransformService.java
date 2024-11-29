package ru.nms.labs.data_server.service;

import org.springframework.stereotype.Service;
import ru.nms.labs.model.SectorWeather;
import ru.nms.labs.model.WeatherData;

@Service
public class TransformService {

    public SectorWeather transform(WeatherData weatherData) {
        return SectorWeather.builder()
                .pressure(weatherData.getPressure())
                .observationTime(weatherData.getObservationTime())
                .windSpeed(weatherData.getWindSpeed())
                .temperature(weatherData.getTemperature())
                .humidity(weatherData.getHumidity())
                .sector(mapToSector(weatherData.getLat(), weatherData.getLon()))
                .build();
    }

    private static int mapToSector(double latitude, double longitude) {
        int latIndex = (int) Math.floor(latitude + 90);

        int lonIndex = (int) Math.floor(longitude + 180);

        return (latIndex * 360) + lonIndex;
    }
}