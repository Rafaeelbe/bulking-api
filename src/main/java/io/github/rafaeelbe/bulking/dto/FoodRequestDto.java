
package io.github.rafaeelbe.bulking.dto;

import io.github.rafaeelbe.bulking.model.FoodUnit;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FoodRequestDto(
        @NotBlank
        String name,

        @NotBlank
        String category,

        @NotNull
        BigDecimal calories,

        @NotNull
        BigDecimal protein,

        @NotNull
        BigDecimal carbohydrate,

        @NotNull
        BigDecimal fat,

        @DecimalMin("0.01")
        @NotNull
        BigDecimal referenceQuantity,

        @NotNull
        FoodUnit referenceUnit
) {}
