package com.sbatec.fact.infrastructure.repository.operation;


import com.sbatec.fact.infrastructure.models.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationJpaRepository extends JpaRepository<OperationEntity, Long> {

    @Override
    List<OperationEntity> findAll();

    List<OperationEntity> findBySiret(String siret);

}
