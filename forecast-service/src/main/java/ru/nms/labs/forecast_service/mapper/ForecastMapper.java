package ru.nms.labs.forecast_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.nms.labs.forecast_service.model.Forecast;
import ru.nms.labs.model.ForecastWeather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface ForecastMapper {

    @Mapping(target = "sector", source = "id.sector")
    @Mapping(target = "observationDate", source = "id.observationDate", qualifiedByName = "dateToString")
    ForecastWeather toForecastWeather(Forecast forecast);

    @Named("dateToString")
    default String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_DATE);
    }
}
