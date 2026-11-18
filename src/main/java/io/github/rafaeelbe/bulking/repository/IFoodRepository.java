package io.github.rafaeelbe.bulking.repository;

import io.github.rafaeelbe.bulking.model.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodRepository extends JpaRepository <FoodEntity, Integer> {



}
