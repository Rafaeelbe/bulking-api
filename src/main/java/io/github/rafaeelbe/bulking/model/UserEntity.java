package io.github.rafaeelbe.bulking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany (mappedBy = "userEntity")
    @Builder.Default
    @Column (nullable = false)
    private Set<MealEntity> meals = new HashSet<>();

}
