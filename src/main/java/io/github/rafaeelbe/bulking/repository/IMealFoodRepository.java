package io.github.rafaeelbe.bulking.repository;

import io.github.rafaeelbe.bulking.model.MealEntity;
import io.github.rafaeelbe.bulking.model.MealFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMealFoodRepository extends JpaRepository <MealFoodEntity, Integer> { }