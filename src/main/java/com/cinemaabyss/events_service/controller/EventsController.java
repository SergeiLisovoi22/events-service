package com.cinemaabyss.events_service.controller;


import com.cinemaabyss.events_service.dto.*;
import com.cinemaabyss.events_service.kafka.KafkaEventsProducer;
import com.cinemaabyss.events_service.service.EventsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@ControllerAdvice
public class EventsController {
        private final EventsService eventsService;
        private final KafkaEventsProducer kafkaEventsProducer;

        @GetMapping(value = "/health", produces = "application/json")
        public HealthStatusDto getEventsServiceHealth() {
                return new HealthStatusDto(true);
        }

        @PostMapping(value = "/movie", consumes = "application/json", produces = "application/json")
        @ResponseStatus(HttpStatus.CREATED)
        public EventResponseDto createMovieEvent(@Valid @RequestBody MovieEventDto dto) {
                return eventsService.sendMessageToTopic("movie", dto);
        }

//        @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
//        @ResponseStatus(HttpStatus.CREATED)
//        public EventResponseDto createUserEvent(@Valid @RequestBody UserEventDto dto) {
//                return eventsService.sendMessageToTopic("user", dto);
//        }

        @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Map<String, Object>> createUserEvent(
                @RequestBody(required = false) JsonNode body) {

                EventDto event = new EventDto(
                        UUID.randomUUID().toString(),
                        "user",
                        OffsetDateTime.now(ZoneOffset.UTC),
                        body != null ? body : NullNode.getInstance()
                );

                kafkaEventsProducer.sendMessage(event, "user-events");
                return ResponseEntity.status(201).body(Map.of("status", "success"));
        }

//        @PostMapping(value = "/payment", consumes = "application/json", produces = "application/json")
//        @ResponseStatus(HttpStatus.CREATED)
//        public EventResponseDto createPaymentEvent(@Valid @RequestBody PaymentEventDto dto) {
//                return eventsService.sendMessageToTopic("payment", dto);
//        }

        @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Map<String, Object>> createPaymentEvent(
                @RequestBody(required = false) JsonNode body) {

                EventDto event = new EventDto(
                        UUID.randomUUID().toString(),
                        "payment",
                        OffsetDateTime.now(ZoneOffset.UTC),
                        body != null ? body : NullNode.getInstance()
                );

                kafkaEventsProducer.sendMessage(event, "payment-events");
                return ResponseEntity.status(201).body(Map.of("status", "success"));
        }



}
