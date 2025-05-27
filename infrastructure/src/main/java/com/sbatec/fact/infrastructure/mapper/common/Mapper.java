package com.sbatec.fact.infrastructure.mapper.common;

import com.sbatec.fact.domaine.business.object.common.Domain;
import com.sbatec.fact.infrastructure.models.common.CommonEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<D extends Domain, E extends CommonEntity> {
  E fromDomainToEntity(D domain);
  D fromEntityToDomain(E entity);

  default List<D> fromEntityToDomain(List<E> entities) {
	  if(entities != null) {
		  return entities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());  
	  }
	  return Collections.emptyList();
   
  }

  default List<E> fromDomainToEntity(List<D> domains) {
	  if(domains != null) {
		  return domains.stream().map(this::fromDomainToEntity).collect(Collectors.toList()); 
	  } 
	  return Collections.emptyList();
  }
}
