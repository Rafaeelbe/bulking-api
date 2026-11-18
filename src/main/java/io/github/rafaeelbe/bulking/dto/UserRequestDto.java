package io.github.rafaeelbe.bulking.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(@NotBlank String name, @NotBlank String email){}
