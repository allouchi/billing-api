package com.aliateck.fact.infrastructure.repository.user;

import com.aliateck.fact.infrastructure.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);

    Optional<UserEntity> findByUserNameAndPassword(String userName, String password);

}
