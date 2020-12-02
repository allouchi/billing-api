package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.infrastructure.models.UserEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

  public UserEntity fromDomainToEntity(User domain) {
    return UserEntity
      .builder()
      .id(domain.getId())
      .firstName(domain.getFirstName())
      .lastName(domain.getLastName())
      .mail(domain.getEmail())
      .password(domain.getPassword())
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
