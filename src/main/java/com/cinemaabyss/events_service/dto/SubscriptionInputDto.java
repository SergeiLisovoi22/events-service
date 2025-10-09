package com.cinemaabyss.events_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SubscriptionInputDto(
        @NotNull @JsonProperty("user_id") Long userId,
        @NotBlank @JsonProperty("plan_type") String planType,
        @NotNull @JsonProperty("start_date") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") OffsetDateTime startDate,
        @NotNull @JsonProperty("end_date") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") OffsetDateTime endDate
) {}

