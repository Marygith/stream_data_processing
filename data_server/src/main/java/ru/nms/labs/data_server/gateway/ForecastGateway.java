package ru.nms.labs.data_server.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.nms.labs.model.SectorWeather;

@FeignClient(name = "forecastGateway", url = "${forecast.service.url}")
public interface ForecastGateway {

    @PostMapping("/forecast")
    void createOrUpdateForecast(@RequestBody SectorWeather sectorWeather);

}