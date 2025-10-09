package com.cinemaabyss.events_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EventResponseDto(
        @NotBlank String status,
        @NotNull Integer partition,
        @NotNull Integer offset,
        @NotNull EventDto event
) {}

