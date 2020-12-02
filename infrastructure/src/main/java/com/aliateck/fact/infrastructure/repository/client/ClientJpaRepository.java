package com.aliateck.fact.infrastructure.repository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliateck.fact.infrastructure.models.ClientEntity;


@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {	

}
