package ru.nms.labs.forecast_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastId implements Serializable {
    private int sector;
    private String observationDate;
}
