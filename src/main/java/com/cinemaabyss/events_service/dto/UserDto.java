package com.cinemaabyss.events_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        @NotNull Long id,
        @NotBlank String username,
        @NotBlank @Email String email
) {
}

