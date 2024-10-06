package ru.nms.labs.data_server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.nms.labs.model.WeatherData;

@Controller
@Slf4j
public class MessageController {

    @MessageMapping("/weather")
    @SendTo("/topic/messages")
    public void receive(WeatherData message) {
        log.info("Message came! " + message.toString());
    }
}
