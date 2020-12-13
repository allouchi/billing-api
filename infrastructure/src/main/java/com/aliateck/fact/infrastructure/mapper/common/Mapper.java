package com.aliateck.fact.infrastructure.mapper.common;

import com.aliateck.fact.domaine.business.object.common.Domain;
import com.aliateck.fact.infrastructure.models.common.CommonEntity;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<D extends Domain, E extends CommonEntity> {
  E fromDomainToEntity(D domain);
  D fromEntityToDomain(E entity);

  default List<D> fromEntityToDomain(List<E> entities) {
    return entities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());
  }

  default List<E> fromDomainToEntity(List<D> domains) {
    return domains.stream().map(this::fromDomainToEntity).collect(Collectors.toList());
  }
}
