package io.github.rafaeelbe.bulking.service;

import io.github.rafaeelbe.bulking.dto.MealFoodRequestDto;
import io.github.rafaeelbe.bulking.dto.MealFoodResponseDto;
import io.github.rafaeelbe.bulking.model.MealFoodEntity;
import io.github.rafaeelbe.bulking.repository.IFoodRepository;
import io.github.rafaeelbe.bulking.repository.IMealFoodRepository;
import io.github.rafaeelbe.bulking.repository.IMealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealFoodService {

    private final IMealFoodRepository mealFoodRepository;
    private final IFoodRepository foodRepository;
    private final IMealRepository mealRepository;

    public List<MealFoodResponseDto> getAllMealFoods() {
        return mealFoodRepository.findAll().stream().map(mealFood -> new MealFoodResponseDto(
                mealFood.getId(),
                mealFood.getMealEntity().getId(),
                mealFood.getFoodEntity().getId(),
                mealFood.getQuantity()
        )).toList();
    }


    public MealFoodResponseDto getMealFoodById(Integer id) {
        MealFoodEntity mealFoodEntity = mealFoodRepository.findById(id).orElseThrow();

        return new MealFoodResponseDto(
                mealFoodEntity.getId(),
                mealFoodEntity.getMealEntity().getId(),
                mealFoodEntity.getFoodEntity().getId(),
                mealFoodEntity.getQuantity()
        );
    }

    public MealFoodResponseDto createMealFood(MealFoodRequestDto mealFoodDto) {
        MealFoodEntity mealFoodEntity = new MealFoodEntity();
        mealFoodEntity.setMealEntity(mealRepository.findById(mealFoodDto.mealId()).orElseThrow());
        mealFoodEntity.setFoodEntity(foodRepository.findById(mealFoodDto.foodId()).orElseThrow());
        mealFoodEntity.setQuantity(mealFoodDto.quantity());

        mealFoodEntity = mealFoodRepository.save(mealFoodEntity);

        return new MealFoodResponseDto(
                mealFoodEntity.getId(),
                mealFoodEntity.getMealEntity().getId(),
                mealFoodEntity.getFoodEntity().getId(),
                mealFoodEntity.getQuantity()
        );
    }


    public MealFoodResponseDto updateMealFood(Integer id, MealFoodRequestDto mealFoodDto) {
        MealFoodEntity mealFoodEntity = mealFoodRepository.findById(id).orElseThrow();
        mealFoodEntity.setMealEntity(mealRepository.findById(mealFoodDto.mealId()).orElseThrow());
        mealFoodEntity.setFoodEntity(foodRepository.findById(mealFoodDto.foodId()).orElseThrow());
        mealFoodEntity.setQuantity(mealFoodDto.quantity());

        mealFoodEntity = mealFoodRepository.save(mealFoodEntity);

        return new MealFoodResponseDto(
                mealFoodEntity.getId(),
                mealFoodEntity.getMealEntity().getId(),
                mealFoodEntity.getFoodEntity().getId(),
                mealFoodEntity.getQuantity()
        );
    }

    public void deleteMealFood(Integer id) {
        mealFoodRepository.deleteById(id);
    }
}
