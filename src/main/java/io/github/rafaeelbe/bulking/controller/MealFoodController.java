package io.github.rafaeelbe.bulking.controller;

import io.github.rafaeelbe.bulking.dto.MealFoodRequestDto;
import io.github.rafaeelbe.bulking.dto.MealFoodResponseDto;
import io.github.rafaeelbe.bulking.service.MealFoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal-foods")
@RequiredArgsConstructor
public class MealFoodController {

    private final MealFoodService mealFoodService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MealFoodResponseDto> listMealFoods() {
        return mealFoodService.getAllMealFoods();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MealFoodResponseDto getMealFood(@PathVariable Integer id) {
        return mealFoodService.getMealFoodById(id);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public MealFoodResponseDto createMealFood(@Valid @RequestBody MealFoodRequestDto mealFoodRequestDto) {
        return mealFoodService.createMealFood(mealFoodRequestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MealFoodResponseDto updateMealFood(@PathVariable Integer id, @Valid @RequestBody MealFoodRequestDto mealFoodRequestDto) {
        return mealFoodService.updateMealFood(id, mealFoodRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMealFood(@PathVariable Integer id) {
        mealFoodService.deleteMealFood(id);
    }
}
