package io.github.rafaeelbe.bulking.service;

import io.github.rafaeelbe.bulking.dto.*;
import io.github.rafaeelbe.bulking.model.MealEntity;
import io.github.rafaeelbe.bulking.model.MealFoodEntity;
import io.github.rafaeelbe.bulking.repository.IMealRepository;
import io.github.rafaeelbe.bulking.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MealService {
    private final IMealRepository iMealRepository;
    private final IUserRepository iUserRepository;
    private final NutritionCalculatorService nutritionCalculatorService;


    public List<MealResponseDto> getAllMeals(){

        return iMealRepository.findAll().stream()
                .map(mealEntity -> new MealResponseDto(
                        mealEntity.getId(),
                        mealEntity.getUserEntity().getId(),
                        mealEntity.getName(),
                        mealEntity.getData(),
                        mealEntity.getMealType()
                ))
                .toList();

    }

    public MealResponseDto getMealById(Integer id){
        MealEntity mealEntity = iMealRepository.findById(id).orElseThrow();

        return new MealResponseDto(
                mealEntity.getId(),
                mealEntity.getUserEntity().getId(),
                mealEntity.getName(),
                mealEntity.getData(),
                mealEntity.getMealType()
        );
    }

    public MealResponseDto createMeal(MealRequestDto mealRequestDto) {


            MealEntity savedMealEntity = new MealEntity();
            savedMealEntity.setUserEntity(iUserRepository.findById(mealRequestDto.userId()).orElseThrow());
            savedMealEntity.setName(mealRequestDto.name());
            savedMealEntity.setData(mealRequestDto.data());
            savedMealEntity.setMealType(mealRequestDto.mealType());
            savedMealEntity = iMealRepository.save(savedMealEntity);

            return new MealResponseDto(
                    savedMealEntity.getId(),
                    savedMealEntity.getUserEntity().getId(),
                    savedMealEntity.getName(),
                    savedMealEntity.getData(),
                    savedMealEntity.getMealType()
            );
        }

        public MealResponseDto updateMeal (Integer id, MealRequestDto mealRequestDto){
            MealEntity existingMealEntity = iMealRepository.findById(id).orElseThrow();

            existingMealEntity.setName(mealRequestDto.name());
            existingMealEntity.setData(mealRequestDto.data());
            existingMealEntity.setMealType(mealRequestDto.mealType());

            existingMealEntity = iMealRepository.save(existingMealEntity);

            return new MealResponseDto(
                    existingMealEntity.getId(),
                    existingMealEntity.getUserEntity().getId(),
                    existingMealEntity.getName(),
                    existingMealEntity.getData(),
                    existingMealEntity.getMealType()
            );
        }

        public void deleteMeal (Integer id){
            iMealRepository.deleteById(id);
        }

    @Transactional(readOnly = true)
    public NutritionResultDto getMealNutrition(Integer id) {
        MealEntity mealEntity = iMealRepository.findById(id)
                .orElseThrow();

        return nutritionCalculatorService.calculateMealNutrition(mealEntity);
    }

    private static final List<String> DEFAULT_MEAL_TYPES = List.of(
            "CAFE_DA_MANHA", "ALMOCO", "LANCHE_DA_TARDE", "JANTAR");

    @Transactional
    public DailySummaryDto getDailySummary(Integer userId, LocalDate date) {

        var user = iUserRepository.findByIdForUpdate(userId).orElseThrow();

        List<MealEntity> meals = iMealRepository
                .findByUserEntityIdAndData(userId, date);

        for (String mealType : DEFAULT_MEAL_TYPES) {
            boolean exists = meals.stream().anyMatch(meal -> mealType.equals(meal.getMealType()));
            if (!exists) {
                MealEntity meal = new MealEntity();
                meal.setUserEntity(user);
                meal.setData(date);
                meal.setMealType(mealType);
                meal.setName(mealType);
                meals.add(iMealRepository.save(meal));
            }
        }

        BigDecimal calories = BigDecimal.ZERO;
        BigDecimal carbohydrate = BigDecimal.ZERO;
        BigDecimal protein = BigDecimal.ZERO;
        BigDecimal fat = BigDecimal.ZERO;

        for (MealEntity meal : meals) {
            NutritionResultDto result =
                    nutritionCalculatorService.calculateMealNutrition(meal);

            calories = calories.add(result.calories());
            carbohydrate = carbohydrate.add(result.carbohydrate());
            protein = protein.add(result.protein());
            fat = fat.add(result.fat());
        }

        List<MealDetailDto> mealResponses = meals.stream()
                .map(this::toMealDetailDto)
                .toList();

        return new DailySummaryDto(
                userId,
                date,
                calories,
                carbohydrate,
                protein,
                fat,
                mealResponses
        );
    }


    private MealFoodDetailDto toMealFoodDetailDto(MealFoodEntity mealFood) {

        var food = mealFood.getFoodEntity();

        NutritionResultDto nutrition =
                nutritionCalculatorService.calculateNutrition(
                        food,
                        mealFood.getQuantity()
                );

        return new MealFoodDetailDto(
                mealFood.getId(),
                food.getId(),
                food.getName(),
                mealFood.getQuantity(),
                food.getReferenceUnit(),
                nutrition.calories(),
                nutrition.carbohydrate(),
                nutrition.protein(),
                nutrition.fat()
        );
    }


    private MealDetailDto toMealDetailDto(MealEntity meal) {

        List<MealFoodDetailDto> foods = meal.getMealFoods()
                .stream()
                .map(this::toMealFoodDetailDto)
                .toList();

        NutritionResultDto nutrition =
                nutritionCalculatorService.calculateMealNutrition(meal);

        return new MealDetailDto(
                meal.getId(),
                meal.getUserEntity().getId(),
                meal.getName(),
                meal.getData(),
                meal.getMealType(),
                nutrition.calories(),
                nutrition.carbohydrate(),
                nutrition.protein(),
                nutrition.fat(),
                foods
        );
    }



}
