package io.github.rafaeelbe.bulking.dto;
import java.math.BigDecimal;

public record MealFoodResponseDto(Integer id,
                                  Integer mealId,
                                  Integer foodId,
                                  BigDecimal quantity) {}
