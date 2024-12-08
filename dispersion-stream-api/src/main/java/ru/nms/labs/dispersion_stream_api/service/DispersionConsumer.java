package ru.nms.labs.dispersion_stream_api.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.nms.labs.model.SectorDispersion;

@Slf4j
@Service
public class DispersionConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    public DispersionConsumer(SimpMessagingTemplate messagingTemplate, ObjectMapper objectMapper) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "SECTOR_DISPERSION", groupId = "websocket-consumer")
    public void consume(@Payload SectorDispersion dispersion) {
        try {
            System.out.println("Consumed sector dispersion data: {" + dispersion + "}");
            messagingTemplate.convertAndSend("/topic/dispersion", dispersion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
