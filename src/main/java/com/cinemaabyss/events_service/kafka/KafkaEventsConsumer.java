package com.cinemaabyss.events_service.kafka;

import com.cinemaabyss.events_service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaEventsConsumer {

    @KafkaListener(id = "events",
            topics = "events",
            containerFactory = "kafkaListenerContainerFactory")
    public void listener(@Payload List<EventDto> messageList,
                         Acknowledgment ack) {
        log.debug("Client consumer: Обработка новых сообщений");
        try {
           messageList.forEach(eventDto -> {
               log.info("Прочитано событие {}", eventDto);
           });
        } finally {
            ack.acknowledge();
        }
        log.debug("Client consumer: записи обработаны");
    }
}
