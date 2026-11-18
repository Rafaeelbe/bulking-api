package io.github.rafaeelbe.bulking.controller;

import io.github.rafaeelbe.bulking.dto.FoodRequestDto;
import io.github.rafaeelbe.bulking.dto.FoodResponseDto;
import io.github.rafaeelbe.bulking.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/foods")
@Validated
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    @ResponseStatus (HttpStatus.OK)
    public List<FoodResponseDto> list(){
        return foodService.list();
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public FoodResponseDto create (@Valid @RequestBody FoodRequestDto food){
        return foodService.create(food);
    }

    @PutMapping("/{id}")
    @ResponseStatus (HttpStatus.OK)
    public FoodResponseDto update (@PathVariable Integer id, @Valid @RequestBody FoodRequestDto food) {
        return foodService.update(id, food);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Integer id) {
        foodService.delete(id);
    }

}
