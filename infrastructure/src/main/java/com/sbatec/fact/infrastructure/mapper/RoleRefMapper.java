package com.sbatec.fact.infrastructure.mapper;

import com.sbatec.fact.domaine.business.object.Role;
import com.sbatec.fact.domaine.business.object.RoleRef;
import com.sbatec.fact.infrastructure.models.RoleEntity;
import com.sbatec.fact.infrastructure.models.RoleRefEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleRefMapper {

    public RoleEntity fromDomainToEntity(Role domain) {
        return RoleEntity.builder()
                .id(domain.getId())
                .description(domain.getDescription())
                .build();
    }

    public Role fromEntityToDomain(RoleEntity entity) {
        return Role.builder()
                .id(entity.getId())
                .role(entity.getRole())
                .description(entity.getDescription())
                .build();
    }

    public List<Role> fromEntityToDomainList(List<RoleEntity> entities) {
        Function<RoleEntity, Role> fnToDomain = this::fromEntityToDomain;
        Function<List<RoleEntity>, List<Role>> fnToDomains = list ->
                list.stream().map(fnToDomain).collect(Collectors.toList());
        return Optional.ofNullable(entities).map(fnToDomains).orElse(Collections.emptyList());
    }

    public List<RoleEntity> fromDomainToEntityList(List<Role> domain) {
        List<RoleEntity> clientList = new ArrayList<>();
        if (domain != null && !domain.isEmpty()) {
            for (Role entity : domain) {
                clientList.add(fromDomainToEntity(entity));
            }
        }
        return clientList;
    }

}
