package com.sbatec.fact.domaine.ports.spi.operation;

import com.sbatec.fact.domaine.business.object.Operation;

import java.util.List;

public interface OperationSpiService {

    List<Operation> findOperations();

    Operation addOperation(Operation operation);

    void deleteOperationById(Long id);
}
