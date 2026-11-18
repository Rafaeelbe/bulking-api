package io.github.rafaeelbe.bulking.repository;
import io.github.rafaeelbe.bulking.model.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface IMealRepository extends JpaRepository <MealEntity, Integer> { List<MealEntity> findByUserEntityIdAndData(Integer userId, LocalDate data);}