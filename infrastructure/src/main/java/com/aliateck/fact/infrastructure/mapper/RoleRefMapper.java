package com.aliateck.fact.infrastructure.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.aliateck.fact.domaine.business.object.RoleRef;
import com.aliateck.fact.infrastructure.models.RoleRefEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleRefMapper {

	public RoleRefEntity fromDomainToEntity(RoleRef domain) {
		return RoleRefEntity.builder()
				.id(domain.getId())
				.description(domain.getDescription())				
				.build();
	}

	public RoleRef fromEntityToDomain(RoleRefEntity entity) {
		return RoleRef.builder()
				.id(entity.getId())
				.description(entity.getDescription())				
				.build();
	}
	 public List<RoleRef> fromEntityToDomainList(List<RoleRefEntity> entities) {
		    Function<RoleRefEntity, RoleRef> fnToDomain = this::fromEntityToDomain;
		    Function<List<RoleRefEntity>, List<RoleRef>> fnToDomains = list ->
		      list.stream().map(fnToDomain).collect(Collectors.toList());
		    return Optional.ofNullable(entities).map(fnToDomains).orElse(Collections.emptyList());
		  }

		  public List<RoleRefEntity> fromDomainToEntityList(List<RoleRef> domain) {
		    List<RoleRefEntity> clientList = new ArrayList<>();
		    if (domain != null && !domain.isEmpty()) {
		      for (RoleRef entity : domain) {
		        clientList.add(fromDomainToEntity(entity));
		      }
		    }
		    return clientList;
		  }

}
