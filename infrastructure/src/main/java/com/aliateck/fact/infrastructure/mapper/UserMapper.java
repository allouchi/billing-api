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
  private final RoleMapper roleUserMapper;

  public UserEntity fromDomainToEntity(User domain) {
      return UserEntity
        .builder()        
        .userName(domain.getUserName())  
        .firstName(domain.getFirstName())
        .lastName(domain.getLastName())
        .password(domain.getPassword())     
        .actived(domain.getActived())
        .roles(roleUserMapper.fromDomainToEntityList(domain.getRoles()))
        .company(companyMapper.fromDomainToEntity(domain.getCompany()))
        .build();
  }
  

  public User fromEntityToDomain(UserEntity entity) {
    return User
      .builder()      
      .userName(entity.getUserName()) 
      .firstName(entity.getFirstName())
      .lastName(entity.getLastName())
      .password(entity.getPassword())     
      .actived(entity.getActived())
      .roles(roleUserMapper.fromEntityToDomainList(entity.getRoles()))
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
