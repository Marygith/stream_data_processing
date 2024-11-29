package ru.nms.labs.forecast_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.nms.labs.forecast_service.model.Forecast;
import ru.nms.labs.forecast_service.model.ForecastId;

@Repository
public interface ForecastRepository extends MongoRepository<Forecast, ForecastId> {
}
