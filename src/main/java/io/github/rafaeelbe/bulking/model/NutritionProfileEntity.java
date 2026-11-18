package io.github.rafaeelbe.bulking.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "nutritional_profile")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NutritionProfileEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private BigDecimal weight;
    @Column(nullable = false)
    private BigDecimal height;
    @Column (name = "daily_calorie_goal", nullable = false)
    private BigDecimal dailyCalorieGoal;
    @Column (name = "carbohydrate_goal", nullable = false)
    private BigDecimal carbohydrateGoal;
    @Column (name = "protein_goal", nullable = false)
    private BigDecimal proteinGoal;
    @Column (name = "fat_goal", nullable = false)
    private BigDecimal fatGoal;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


}
