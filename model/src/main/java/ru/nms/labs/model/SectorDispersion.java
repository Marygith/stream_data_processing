package ru.nms.labs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectorDispersion implements Serializable {
    private int sector;
    private String observationDate;
    private double dispersion;

}