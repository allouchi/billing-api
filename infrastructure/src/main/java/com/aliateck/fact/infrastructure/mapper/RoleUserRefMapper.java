package com.aliateck.fact.infrastructure.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.business.object.UserRoleRef;
import com.aliateck.fact.infrastructure.models.UserEntity;
import com.aliateck.fact.infrastructure.models.UserRoleRefEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleUserRefMapper {

	public UserRoleRefEntity fromDomainToEntity(UserRoleRef domain) {
		return UserRoleRefEntity.builder().id(domain.getId()).roleName(domain.getRoleName()).roleId(domain.getRoleId())
				.build();
	}

	public UserRoleRef fromEntityToDomain(UserRoleRefEntity entity) {
		return UserRoleRef.builder().id(entity.getId()).roleName(entity.getRoleName()).roleId(entity.getRoleId())
				.build();
	}
	
	 public List<UserRoleRef> fromEntityToDomainList(List<UserRoleRefEntity> entities) {
		    Function<UserRoleRefEntity, UserRoleRef> fnToDomain = this::fromEntityToDomain;
		    Function<List<UserRoleRefEntity>, List<UserRoleRef>> fnToDomains = list ->
		      list.stream().map(fnToDomain).collect(Collectors.toList());
		    return Optional.ofNullable(entities).map(fnToDomains).orElse(Collections.emptyList());
		  }

		  public List<UserRoleRefEntity> fromDomainToEntityList(List<UserRoleRef> domain) {
		    List<UserRoleRefEntity> clientList = new ArrayList<>();
		    if (domain != null && !domain.isEmpty()) {
		      for (UserRoleRef entity : domain) {
		        clientList.add(fromDomainToEntity(entity));
		      }
		    }
		    return clientList;
		  }

}
