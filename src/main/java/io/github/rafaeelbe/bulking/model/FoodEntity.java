package io.github.rafaeelbe.bulking.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "food")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FoodEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private BigDecimal calories;
    @Column(nullable = false)
    private BigDecimal protein;
    @Column(nullable = false)
    private BigDecimal carbohydrate;
    @Column(nullable = false)
    private BigDecimal fat;
    @Column(name = "reference_quantity", nullable = false)
    private BigDecimal referenceQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "reference_unit", nullable = false)
    private FoodUnit referenceUnit;



    @OneToMany (mappedBy = "foodEntity")
    @Builder.Default
    private Set<MealFoodEntity> mealFoods = new HashSet<>();


}
