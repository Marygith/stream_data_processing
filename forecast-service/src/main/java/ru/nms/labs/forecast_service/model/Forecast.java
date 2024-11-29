package ru.nms.labs.forecast_service.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "forecast")
@Data
@NoArgsConstructor
public class Forecast {

    @Id
    private ForecastId id;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private double pressure;

}