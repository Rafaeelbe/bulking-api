
package io.github.rafaeelbe.bulking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record MealDetailDto(
        Integer id,
        Integer userId,
        String name,
        LocalDate data,
        String mealType,
        BigDecimal calories,
        BigDecimal carbohydrate,
        BigDecimal protein,
        BigDecimal fat,
        List<MealFoodDetailDto> foods
) {}
