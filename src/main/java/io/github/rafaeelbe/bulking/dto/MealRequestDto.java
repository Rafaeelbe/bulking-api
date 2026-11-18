package io.github.rafaeelbe.bulking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record MealRequestDto(@NotNull Integer userId,
                             @NotBlank String name,
                             @NotNull LocalDate data,
                             @NotBlank String mealType
                             ){}
