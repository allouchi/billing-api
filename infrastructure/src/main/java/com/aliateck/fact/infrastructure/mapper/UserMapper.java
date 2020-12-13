package com.aliateck.fact.infrastructure.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.infrastructure.models.UserEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
  private final CompanyMapper companyMapper;

  public UserEntity fromDomainToEntity(User domain) {
      return UserEntity
        .builder()
        .id(domain.getId())
        .firstName(domain.getFirstName())
        .lastName(domain.getLastName())
        .mail(domain.getEmail())
        .password(domain.getPassword())
        .role(domain.getRole())
        .company(companyMapper.fromDomainToEntity(domain.getCompany()))
        .build();
  }

  public User fromEntityToDomain(UserEntity entity) {
    return User
      .builder()
      .id(entity.getId())
      .firstName(entity.getFirstName())
      .lastName(entity.getLastName())
      .email(entity.getMail())
      .password(entity.getPassword())
      .role(entity.getRole())
      .company(companyMapper.fromEntityToDomain(entity.getCompany()))
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
