package ru.nms.labs.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    private int lon;
    private int lat;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private double pressure;
    private String observationTime;
}
