package com.cinemaabyss.events_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserInputDto(
        @NotBlank String username,
        @NotBlank @Email String email
) {}
