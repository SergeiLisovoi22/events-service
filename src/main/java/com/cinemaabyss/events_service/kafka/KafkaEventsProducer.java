package com.cinemaabyss.events_service.kafka;

import com.cinemaabyss.events_service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaEventsProducer {

    private final KafkaTemplate<String, Object> kafkaEventTemplate;

    public void sendMessage(EventDto event, String topicName) {
        kafkaEventTemplate.send(topicName, event);
    }


}
