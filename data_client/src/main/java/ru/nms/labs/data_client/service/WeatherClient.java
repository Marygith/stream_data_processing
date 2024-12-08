package ru.nms.labs.data_client.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import ru.nms.labs.model.WeatherData;

import java.lang.reflect.Type;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class WeatherClient {

    private final DataGenerator dataGenerator;
    private StompSession stompSession;

    @PostConstruct
    public void connect() {
        final WebSocketClient client = new StandardWebSocketClient();

        final WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        stompClient.connect("ws://localhost:8080/weather", new ChatStompSessionHandler());
        log.info("Connecting to WebSocket server...");
    }

    private class ChatStompSessionHandler extends StompSessionHandlerAdapter {
        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            log.info("Connected to WebSocket: {}", session.getSessionId());
            stompSession = session;

            stompSession.subscribe("/topic/messages", this);
            log.info("Subscribed to /topic/messages");
        }

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return WeatherData.class;
        }
    }

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        log.info("About to send weather message");
        if (stompSession != null) {
            WeatherData message = dataGenerator.generate();
            stompSession.send("/app/weather", message);
            log.info("Sent message: {}", message);
        }
    }
}