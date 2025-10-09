package com.cinemaabyss.events_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MovieInputDto(
        @NotBlank String title,
        @NotBlank String description,
        List<String> genres,
        @NotNull BigDecimal rating
) {}
