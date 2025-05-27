package com.sbatec.fact.infrastructure.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbatec.fact.infrastructure.models.RoleEntity;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

	Optional<RoleEntity> findByRoleName(String roleName);

}
