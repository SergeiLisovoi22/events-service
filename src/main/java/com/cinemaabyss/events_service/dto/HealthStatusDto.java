package com.cinemaabyss.events_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record HealthStatusDto(
        boolean status
) {}

