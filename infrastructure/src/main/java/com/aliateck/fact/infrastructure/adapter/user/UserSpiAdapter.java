package com.aliateck.fact.infrastructure.adapter.user;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.exception.UserNotFoundException;
import com.aliateck.fact.domaine.ports.spi.user.UserSpiService;
import com.aliateck.fact.infrastructure.mapper.UserMapper;
import com.aliateck.fact.infrastructure.models.UserEntity;
import com.aliateck.fact.infrastructure.repository.user.UserJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSpiAdapter implements UserSpiService {
  UserMapper mapper;
  UserJpaRepository serviceJpaRepository;

  @Override
  public void addUser(User user) {
    UserEntity userEntity = mapper.fromDomainToEntity(user);

    serviceJpaRepository.save(userEntity);
  }

  @Override
  public void removeUser(User user) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateUser(User user) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<User> getAllUsers() throws UserNotFoundException {
    List<UserEntity> usersEntity = serviceJpaRepository.findAll();
    return mapper.fromEntityToDomainList(usersEntity);
  }

  @Override
  public User getUserById(long id) throws UserNotFoundException {
    return null;
  }

  @Override
  public User getUserByMailAndPassword(String mail, String password)
    throws UserNotFoundException {
    Optional<UserEntity> entity = serviceJpaRepository.findByMailAndPassword(
      mail,
      password
    );

    if (!entity.isPresent()) {
      throw new UserNotFoundException("User not found");
    } else {
      return mapper.fromEntityToDomain(entity.get());
    }
  }
}
