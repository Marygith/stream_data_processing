package ru.nms.labs.data_server.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.nms.labs.model.SectorWeather;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMessageProducer {

    private final KafkaTemplate<String, SectorWeather> kafkaTemplate;

    @Value("${kafka.topic.weather}")
    private String topic;

    public void sendMessage(SectorWeather sectorWeather) {
        log.info("Sending WeatherData to Kafka: {}", sectorWeather);
        kafkaTemplate.send(topic, sectorWeather);
    }
}