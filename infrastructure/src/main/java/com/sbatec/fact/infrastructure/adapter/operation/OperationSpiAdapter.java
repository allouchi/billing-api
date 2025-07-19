package com.sbatec.fact.infrastructure.adapter.operation;

import com.sbatec.fact.domaine.business.object.Operation;
import com.sbatec.fact.domaine.ports.spi.operation.OperationSpiService;
import com.sbatec.fact.infrastructure.mapper.OperationMapper;
import com.sbatec.fact.infrastructure.models.OperationEntity;
import com.sbatec.fact.infrastructure.repository.operation.OperationJpaRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OperationSpiAdapter implements OperationSpiService {

    OperationJpaRepository operationJpaRepository;
    OperationMapper operationMapper;

    @Override
    public List<Operation> findOperations() {
        List<OperationEntity> operationEntities = operationJpaRepository.findAll();
        return operationMapper.fromEntityToDomain(operationEntities);
    }

    @Override
    public Operation addOperation(Operation operation) {
        OperationEntity operationEntity = operationMapper.fromDomainToEntity(operation);
        OperationEntity operationSaved = operationJpaRepository.save(operationEntity);
        return operationMapper.fromEntityToDomain(operationSaved);
    }

    @Override
    public void deleteOperationById(Long id) {
        operationJpaRepository.deleteById(id);
    }
}
