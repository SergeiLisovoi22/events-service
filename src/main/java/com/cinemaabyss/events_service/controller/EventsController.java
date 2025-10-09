package com.cinemaabyss.events_service.controller;


import com.cinemaabyss.events_service.dto.*;
import com.cinemaabyss.events_service.service.EventsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventsController {
        private final EventsService eventsService;

        @GetMapping(value = "/health", produces = "application/json")
        public HealthStatusDto getEventsServiceHealth() {
                return new HealthStatusDto(true);
        }

        @PostMapping(value = "/movie", consumes = "application/json", produces = "application/json")
        @ResponseStatus(HttpStatus.CREATED)
        public EventResponseDto createMovieEvent(@Valid @RequestBody MovieEventDto dto) {
                return eventsService.sendMessageToTopic("movie", dto);
        }

        @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
        @ResponseStatus(HttpStatus.CREATED)
        public EventResponseDto createUserEvent(@Valid @RequestBody UserEventDto dto) {
                return eventsService.sendMessageToTopic("user", dto);
        }

        @PostMapping(value = "/payment", consumes = "application/json", produces = "application/json")
        @ResponseStatus(HttpStatus.CREATED)
        public EventResponseDto createPaymentEvent(@Valid @RequestBody PaymentEventDto dto) {
                return eventsService.sendMessageToTopic("payment", dto);
        }





}
