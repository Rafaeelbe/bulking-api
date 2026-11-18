
package io.github.rafaeelbe.bulking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DailySummaryDto(
        Integer userId,
        LocalDate date,
        BigDecimal calories,
        BigDecimal carbohydrate,
        BigDecimal protein,
        BigDecimal fat,
        List<MealDetailDto> meals
) {}
