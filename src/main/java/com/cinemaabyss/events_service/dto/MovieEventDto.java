package com.cinemaabyss.events_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MovieEventDto(
        @NotNull @JsonProperty("movie_id") Long movieId,
        @NotBlank String title,
        @NotBlank String action,
        @JsonProperty("user_id") Long userId,
        BigDecimal rating,
        List<String> genres,
        String description
) {}

