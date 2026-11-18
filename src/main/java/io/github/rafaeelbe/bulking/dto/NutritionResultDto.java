package io.github.rafaeelbe.bulking.dto;

import java.math.BigDecimal;

public record   NutritionResultDto(BigDecimal calories,
                                   BigDecimal carbohydrate,
                                   BigDecimal protein,
                                   BigDecimal fat){

}
