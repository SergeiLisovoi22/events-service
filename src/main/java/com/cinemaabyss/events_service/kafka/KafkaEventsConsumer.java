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

    @KafkaListener(id = "user-events",
            topics = "user-events",
            containerFactory = "kafkaListenerContainerFactory")
    public void userListener(@Payload List<EventDto> messageList,
                         Acknowledgment ack) {
        log.debug("User consumer: Обработка новых сообщений от пользователя");
        try {
           messageList.forEach(eventDto -> {
               log.info("Прочитано событие {}", eventDto);
           });
        } finally {
            ack.acknowledge();
        }
        log.debug("User consumer: записи обработаны");
    }

    @KafkaListener(id = "payment-events",
            topics = "payment-events",
            containerFactory = "kafkaListenerContainerFactory")
    public void paymentlistener(@Payload List<EventDto> messageList,
                         Acknowledgment ack) {
        log.debug("Payment consumer: Обработка новых платежных сообщений");
        try {
            messageList.forEach(eventDto -> {
                log.info("Прочитано событие {}", eventDto);
            });
        } finally {
            ack.acknowledge();
        }
        log.debug("Payment consumer: записи обработаны");
    }

    @KafkaListener(id = "movie-events",
            topics = "movie-events",
            containerFactory = "kafkaListenerContainerFactory")
    public void movielistener(@Payload List<EventDto> messageList,
                         Acknowledgment ack) {
        log.debug("Movie consumer: Обработка новых сообщений");
        try {
            messageList.forEach(eventDto -> {
                log.info("Прочитано событие {}", eventDto);
            });
        } finally {
            ack.acknowledge();
        }
        log.debug("Movie consumer: записи обработаны");
    }


}
