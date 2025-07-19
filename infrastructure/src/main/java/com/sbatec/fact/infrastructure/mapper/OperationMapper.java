package com.sbatec.fact.infrastructure.mapper;

import com.sbatec.fact.domaine.business.object.Operation;
import com.sbatec.fact.infrastructure.models.OperationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OperationMapper {

    /**
     * @param domain
     * @return
     */
    public OperationEntity fromDomainToEntity(Operation domain) {
        if (domain == null) {
            return null;
        }
        if (domain.getId() != null && domain.getId().longValue() == 0) {
            domain.setId(null);
        }
        return OperationEntity.builder().id(domain.getId()).montantoperation(domain.getMontantOperation())
                .dateOperation(domain.getDateOperation()).typeOperation(domain.getTypeOperation()).exercise(domain.getExercise()).build();
    }

    /**
     * @param entity
     * @return
     */
    public Operation fromEntityToDomain(OperationEntity entity) {

        if (entity == null) {
            return null;
        }
        return Operation.builder().id(entity.getId()).montantOperation(entity.getMontantoperation())
                .dateOperation(entity.getDateOperation()).typeOperation(entity.getTypeOperation()).exercise(entity.getExercise()).build();
    }

    /**
     * @param entities
     * @return
     */
    public List<Operation> fromEntityToDomain(List<OperationEntity> entities) {

        if (entities != null) {
            return entities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * @param domains
     * @return
     */
    public List<OperationEntity> fromDomainToEntity(List<Operation> domains) {
        if (domains != null) {
            return domains.stream().map(this::fromDomainToEntity).collect(Collectors.toList());
        }
        return Collections.emptyList();

    }

}
