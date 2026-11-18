package io.github.rafaeelbe.bulking.repository;

import io.github.rafaeelbe.bulking.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository <UserEntity, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from UserEntity u where u.id = :id")
    java.util.Optional<UserEntity> findByIdForUpdate(@Param("id") Integer id);
}
