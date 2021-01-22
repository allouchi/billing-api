package com.aliateck.fact.infrastructure.adapter.user;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.exception.UserNotFoundException;
import com.aliateck.fact.domaine.ports.spi.user.UserSpiService;
import com.aliateck.fact.infrastructure.mapper.UserMapper;
import com.aliateck.fact.infrastructure.models.UserEntity;
import com.aliateck.fact.infrastructure.repository.user.UserJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSpiAdapter implements UserSpiService {
  UserMapper userMapper;
  UserJpaRepository userJpaRepository;

  @Override
  public void addUser(User user) {
    UserEntity userEntity = userMapper.fromDomainToEntity(user);
    userJpaRepository.save(userEntity);
  }

  @Override
  public void removeUser(User user) {
    UserEntity userEntity = userMapper.fromDomainToEntity(user);
    userJpaRepository.delete(userEntity);
  }

  @Override
  public void updateUser(User user) {
    UserEntity userEntity = userMapper.fromDomainToEntity(user);
    userJpaRepository.save(userEntity);    
  }

  @Override
  public List<User> findAllUsers() {
    List<UserEntity> usersEntity = userJpaRepository.findAll();
    return userMapper.fromEntityToDomainList(usersEntity);
  }

  @Override
  public User findUserById(long id) {
    Optional<UserEntity> usersEntity = userJpaRepository.findById(id);
    if (!usersEntity.isPresent()) {
      throw new UserNotFoundException("User not found");
    } else {
      return userMapper.fromEntityToDomain(usersEntity.get());
    }
  }

  @Override
  public User findUserByMailAndPassword(String mail, String password) {
    Optional<UserEntity> entity = userJpaRepository.findByMailAndPassword(mail, password);

    if (!entity.isPresent()) {
      throw new UserNotFoundException("User not found");
    } else {
      return userMapper.fromEntityToDomain(entity.get());
    }
  }
  
  @Override
  public User findUserByMail(String mail) {
    Optional<UserEntity> entity = userJpaRepository.findByMail(mail);

    if (!entity.isPresent()) {
      throw new UserNotFoundException("User not found");
    } else {
      return userMapper.fromEntityToDomain(entity.get());
    }
  }

  @Override
  public User findUserByRole(String role) {
    Optional<UserEntity> entity = userJpaRepository.findByRole(role);
    if (!entity.isPresent()) {
      throw new UserNotFoundException("User not found");
    } else {
      return userMapper.fromEntityToDomain(entity.get());
    }
  }
}
