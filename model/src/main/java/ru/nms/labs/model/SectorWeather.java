package ru.nms.labs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectorWeather {
    private int sector;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private double pressure;
    private String observationTime;
}
