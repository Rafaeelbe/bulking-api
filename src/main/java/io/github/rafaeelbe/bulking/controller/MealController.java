package io.github.rafaeelbe.bulking.controller;

import io.github.rafaeelbe.bulking.dto.DailySummaryDto;
import io.github.rafaeelbe.bulking.dto.MealRequestDto;
import io.github.rafaeelbe.bulking.dto.MealResponseDto;
import io.github.rafaeelbe.bulking.dto.NutritionResultDto;
import io.github.rafaeelbe.bulking.service.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MealResponseDto> listAllMeals(){
        return mealService.getAllMeals();

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MealResponseDto getMealById(@PathVariable Integer id){
        return mealService.getMealById(id);
    }

    @GetMapping ("/{id}/nutrition")
    @ResponseStatus(HttpStatus.OK)
    public NutritionResultDto getMealNutrition(@PathVariable Integer id){
        return mealService.getMealNutrition(id);
    }

    @GetMapping("/daily-summary")
    @ResponseStatus(HttpStatus.OK)
    public DailySummaryDto getDailySummary(
            @RequestParam Integer userId,
            @RequestParam LocalDate date) {

        return mealService.getDailySummary(userId, date);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealResponseDto createMeal(@Valid @RequestBody MealRequestDto mealDto){
        return mealService.createMeal(mealDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MealResponseDto updateMeal(@PathVariable Integer id, @Valid @RequestBody MealRequestDto mealDto){
        return mealService.updateMeal(id, mealDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMeal(@PathVariable Integer id){
        mealService.deleteMeal(id);
    }
}
