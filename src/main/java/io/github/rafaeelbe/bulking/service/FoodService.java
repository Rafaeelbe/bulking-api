
package io.github.rafaeelbe.bulking.service;

import io.github.rafaeelbe.bulking.dto.FoodRequestDto;
import io.github.rafaeelbe.bulking.dto.FoodResponseDto;
import io.github.rafaeelbe.bulking.model.FoodEntity;
import io.github.rafaeelbe.bulking.repository.IFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final IFoodRepository iFoodRepository;

    public List<FoodResponseDto> list() {
        return iFoodRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }

    public FoodResponseDto create(FoodRequestDto food) {
        FoodEntity foodEntity = new FoodEntity();

        foodEntity.setName(food.name());
        foodEntity.setCategory(food.category());
        foodEntity.setCalories(food.calories());
        foodEntity.setProtein(food.protein());
        foodEntity.setCarbohydrate(food.carbohydrate());
        foodEntity.setFat(food.fat());
        foodEntity.setReferenceQuantity(food.referenceQuantity());
        foodEntity.setReferenceUnit(food.referenceUnit());

        foodEntity = iFoodRepository.save(foodEntity);

        return toResponseDto(foodEntity);
    }

    public FoodResponseDto update(Integer id, FoodRequestDto food) {
        FoodEntity foodEntity = iFoodRepository.findById(id)
                .orElseThrow();

        foodEntity.setName(food.name());
        foodEntity.setCategory(food.category());
        foodEntity.setCalories(food.calories());
        foodEntity.setProtein(food.protein());
        foodEntity.setCarbohydrate(food.carbohydrate());
        foodEntity.setFat(food.fat());
        foodEntity.setReferenceQuantity(food.referenceQuantity());
        foodEntity.setReferenceUnit(food.referenceUnit());

        foodEntity = iFoodRepository.save(foodEntity);

        return toResponseDto(foodEntity);
    }

    public void delete(Integer id) {
        iFoodRepository.deleteById(id);
    }

    private FoodResponseDto toResponseDto(FoodEntity foodEntity) {
        return new FoodResponseDto(
                foodEntity.getId(),
                foodEntity.getName(),
                foodEntity.getCategory(),
                foodEntity.getCalories(),
                foodEntity.getProtein(),
                foodEntity.getCarbohydrate(),
                foodEntity.getFat(),
                foodEntity.getReferenceQuantity(),
                foodEntity.getReferenceUnit()
        );
    }
}
