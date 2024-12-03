package ru.nms.labs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectorDispersion {
    private int sector;
    private LocalDate calculationDay;
    private double dispersion;

}