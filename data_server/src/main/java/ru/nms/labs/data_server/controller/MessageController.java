package ru.nms.labs.data_server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.nms.labs.data_server.gateway.ForecastGateway;
import ru.nms.labs.model.SectorWeather;
import ru.nms.labs.data_server.producer.KafkaMessageProducer;
import ru.nms.labs.data_server.service.TransformService;
import ru.nms.labs.model.WeatherData;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    private final KafkaMessageProducer kafkaMessageProducer;
    private final TransformService transformService;
    private final ForecastGateway forecastGateway;

    @MessageMapping("/weather")
    @SendTo("/topic/messages")
    public void receive(WeatherData message) {
        log.info("Message came! " + message.toString());
        SectorWeather sectorWeather = transformService.transform(message);
        forecastGateway.createOrUpdateForecast(sectorWeather);
        kafkaMessageProducer.sendMessage(sectorWeather);
    }
}
