package io.github.rafaeelbe.bulking.dto;
import java.time.LocalDate;

public record MealResponseDto(Integer id,
                              Integer userId,
                              String name,
                              LocalDate data,
                              String MealType) {}
