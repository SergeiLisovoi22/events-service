package com.cinemaabyss.events_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentEventDto(
        @NotNull @JsonProperty("payment_id") Long paymentId,
        @NotNull @JsonProperty("user_id") Long userId,
        @NotNull BigDecimal amount,
        @NotBlank String status,
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") OffsetDateTime timestamp,
        @JsonProperty("method_type") String methodType
) {}

