package com.sbatec.fact.infrastructure.repository.user;

import com.sbatec.fact.infrastructure.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String userName);

    Optional<UserEntity> findByEmailAndPassword(String userName, String password);

}
