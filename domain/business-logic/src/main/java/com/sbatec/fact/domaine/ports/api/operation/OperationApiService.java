package com.sbatec.fact.domaine.ports.api.operation;


import com.sbatec.fact.domaine.business.object.Operation;

import java.util.List;

/**
 *
 */
public interface OperationApiService {

    List<Operation> findOperations();

    Operation addOperation(Operation operation);

    void deleteOperationById(Long id);
}
