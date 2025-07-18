package com.sbatec.fact.domaine.adapter.operation;

import com.sbatec.fact.domaine.business.object.Operation;
import com.sbatec.fact.domaine.ports.api.operation.OperationApiService;
import com.sbatec.fact.domaine.ports.spi.operation.OperationSpiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OperationApiAdapter implements OperationApiService {

    OperationSpiService operationSpiService;

    @Override
    public List<Operation> findOperations() {
        return operationSpiService.findOperations();
    }
}
