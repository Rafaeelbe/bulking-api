package io.github.rafaeelbe.bulking.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "meal_food")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class MealFoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private MealEntity mealEntity;
    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private FoodEntity foodEntity;
    @Column(nullable = false)
    private BigDecimal quantity;
}
