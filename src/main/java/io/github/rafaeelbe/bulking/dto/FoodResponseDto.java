
package io.github.rafaeelbe.bulking.dto;

import io.github.rafaeelbe.bulking.model.FoodUnit;

import java.math.BigDecimal;

public record FoodResponseDto(
        Integer id,
        String name,
        String category,
        BigDecimal calories,
        BigDecimal protein,
        BigDecimal carbohydrate,
        BigDecimal fat,
        BigDecimal referenceQuantity,
        FoodUnit referenceUnit
) {}
