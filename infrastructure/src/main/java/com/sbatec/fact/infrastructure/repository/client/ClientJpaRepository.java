package com.sbatec.fact.infrastructure.repository.client;

import com.sbatec.fact.infrastructure.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> getBySocialReasonIgnoreCase(String reasonSocial);

    Optional<ClientEntity> findByEmail(String mail);
}
