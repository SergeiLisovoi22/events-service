package com.cinemaabyss.events_service.service;

import com.cinemaabyss.events_service.dto.EventDto;
import com.cinemaabyss.events_service.dto.EventResponseDto;
import com.cinemaabyss.events_service.kafka.KafkaEventsProducer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventsService {

    private final KafkaEventsProducer kafkaEventsProducer;
    private final ObjectMapper objectMapper;

    public EventResponseDto sendMessageToTopic(String eventType, Object payloadDto) {
        EventDto eventDto = buildAndSendEvent(eventType, payloadDto);
        return new EventResponseDto("success", 0, 0, eventDto);
    }

    private EventDto buildAndSendEvent(String type, Object payloadDto) {
        String id = UUID.randomUUID().toString();
        OffsetDateTime now = OffsetDateTime.now();
        JsonNode payload = objectMapper.valueToTree(payloadDto);

        EventDto event = new EventDto(id, type, now, payload);
        kafkaEventsProducer.sendMessage(event);
        return event;
    }
}
