package io.github.rafaeelbe.bulking.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "meal")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MealEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate data;
    @Column(name = "meal_type", nullable = false)
    private String mealType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany (mappedBy = "mealEntity")
    @Builder.Default
    private Set<MealFoodEntity> mealFoods = new HashSet<>();




}
