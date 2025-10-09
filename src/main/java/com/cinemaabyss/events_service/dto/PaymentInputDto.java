package com.cinemaabyss.events_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentInputDto(
        @NotNull @JsonProperty("user_id") Long userId,
        @NotNull BigDecimal amount
) {}

