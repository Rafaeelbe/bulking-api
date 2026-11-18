package io.github.rafaeelbe.bulking.service;

import io.github.rafaeelbe.bulking.dto.NutritionResultDto;
import io.github.rafaeelbe.bulking.model.FoodEntity;
import io.github.rafaeelbe.bulking.model.MealEntity;
import io.github.rafaeelbe.bulking.model.MealFoodEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NutritionCalculatorService {

    public NutritionResultDto calculateNutrition(FoodEntity foodEntity, BigDecimal quantity) {
    NutritionResultDto nutritionResultDto = new NutritionResultDto(
            foodEntity.getCalories().multiply(quantity).divide(foodEntity.getReferenceQuantity(),2,BigDecimal.ROUND_HALF_UP),
            foodEntity.getCarbohydrate().multiply(quantity).divide(foodEntity.getReferenceQuantity(),2,BigDecimal.ROUND_HALF_UP),
            foodEntity.getProtein().multiply(quantity).divide(foodEntity.getReferenceQuantity(),2,BigDecimal.ROUND_HALF_UP),
            foodEntity.getFat().multiply(quantity).divide(foodEntity.getReferenceQuantity(),2,BigDecimal.ROUND_HALF_UP)
    );
        return nutritionResultDto;
    }

    public NutritionResultDto calculateMealNutrition(MealEntity mealEntity) {

        BigDecimal calories = BigDecimal.ZERO;
        BigDecimal carbohydrate = BigDecimal.ZERO;
        BigDecimal protein = BigDecimal.ZERO;
        BigDecimal fat = BigDecimal.ZERO;

        for (MealFoodEntity mealFood : mealEntity.getMealFoods()) {

            NutritionResultDto result = calculateNutrition(
                    mealFood.getFoodEntity(),
                    mealFood.getQuantity()
            );

            calories = calories.add(result.calories());
            carbohydrate = carbohydrate.add(result.carbohydrate());
            protein = protein.add(result.protein());
            fat = fat.add(result.fat());
        }

        return new NutritionResultDto(
                calories,
                carbohydrate,
                protein,
                fat
        );
    }
}
