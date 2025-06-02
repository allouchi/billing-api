package com.sbatec.fact.infrastructure.mapper;

import com.sbatec.fact.domaine.business.object.User;
import com.sbatec.fact.infrastructure.models.UserEntity;
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
public class UserMapper {
    private final CompanyMapper companyMapper;
    private final RoleMapper roleMapper;

    public UserEntity fromDomainToEntity(User domain) {
        return UserEntity
                .builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .password(domain.getPassword())
                .activated(domain.getActivated())
                .roleNames(roleMapper.fromDomainToEntityList(domain.getRoles()))
                .siret(domain.getSiret())
                .build();
    }


    public User fromEntityToDomain(UserEntity entity) {
        return User
                .builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .password(entity.getPassword())
                .activated(entity.getActivated())
                .roles(roleMapper.fromEntityToDomainList(entity.getRoleNames()))
                .siret(entity.getSiret())
                .build();
    }


    public List<User> fromEntityToDomainList(List<UserEntity> entities) {
        Function<UserEntity, User> fnToDomain = this::fromEntityToDomain;
        Function<List<UserEntity>, List<User>> fnToDomains = list ->
                list.stream().map(fnToDomain).collect(Collectors.toList());
        return Optional.ofNullable(entities).map(fnToDomains).orElse(Collections.emptyList());
    }

    public List<UserEntity> fromDomainToEntityList(List<User> domain) {
        List<UserEntity> clientList = new ArrayList<>();
        if (domain != null && !domain.isEmpty()) {
            for (User entity : domain) {
                clientList.add(fromDomainToEntity(entity));
            }
        }
        return clientList;
    }
}
