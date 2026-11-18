package io.github.rafaeelbe.bulking.repository;

import io.github.rafaeelbe.bulking.model.NutritionProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INutritionProfileRepository extends JpaRepository <NutritionProfileEntity, Integer> {
}