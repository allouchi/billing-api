package com.aliateck.fact.infrastructure.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.aliateck.fact.domaine.business.object.UserRole;
import com.aliateck.fact.infrastructure.models.UserRoleEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleUserMapper {

	public UserRoleEntity fromDomainToEntity(UserRole domain) {
		return UserRoleEntity.
				builder()
				.id(domain.getId())
				.roleName(domain.getRoleName())
				.roleId(domain.getRoleId())
				.build();
	}

	public UserRole fromEntityToDomain(UserRoleEntity entity) {
		return UserRole.builder().id(entity.getId()).roleName(entity.getRoleName())
				.roleId(entity.getRoleId())
				.build();
	}

	public List<UserRole> fromEntityToDomainList(List<UserRoleEntity> entities) {

		Function<UserRoleEntity, UserRole> fnToDomain = this::fromEntityToDomain;
		Function<List<UserRoleEntity>, List<UserRole>> fnToDomains = list -> list.stream().map(fnToDomain)
				.collect(Collectors.toList());
		return Optional.ofNullable(entities).map(fnToDomains).orElse(Collections.emptyList());
	}

	public List<UserRoleEntity> fromDomainToEntityList(List<UserRole> domain) {
		List<UserRoleEntity> roles = new ArrayList<>();
		if (domain != null && !domain.isEmpty()) {
			for (UserRole entity : domain) {
				roles.add(fromDomainToEntity(entity));
			}
		}
		return roles;
	}

}
