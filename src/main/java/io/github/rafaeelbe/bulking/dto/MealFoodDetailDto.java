
package io.github.rafaeelbe.bulking.dto;

import io.github.rafaeelbe.bulking.model.FoodUnit;

import java.math.BigDecimal;

public record MealFoodDetailDto(
        Integer id,
        Integer foodId,
        String foodName,
        BigDecimal quantity,
        FoodUnit referenceUnit,
        BigDecimal calories,
        BigDecimal carbohydrate,
        BigDecimal protein,
        BigDecimal fat
) {}
