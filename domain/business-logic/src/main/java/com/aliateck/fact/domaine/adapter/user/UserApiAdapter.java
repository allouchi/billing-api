package com.aliateck.fact.domaine.adapter.user;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.exception.UserNotFoundException;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import com.aliateck.fact.domaine.ports.spi.user.UserSpiService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserApiAdapter implements UserApiService {
  UserSpiService userSpiService;

  @Override
  public void addUser(User user) {
    userSpiService.addUser(user);
  }

  @Override
  public void deleteUser(User user) {
    userSpiService.removeUser(user);
  }

  @Override
  public void updateUser(User user) {
    userSpiService.updateUser(user);
  }

  @Override
  public List<User> getUsers() {
    List<User> users = null;
    try {
      users = userSpiService.getAllUsers();
    } catch (UserNotFoundException e) {
      e.printStackTrace();
    }
    return users;
  }

  @Override
  public User findUserById(long id) {
    User user = null;
    try {
      user = userSpiService.getUserById(id);
    } catch (UserNotFoundException e) {
      e.printStackTrace();
    }

    return user;
  }

  @Override
  public User findUserByMailAndPassword(String email, String password) {
    User user = null;
    try {
      user = userSpiService.getUserByMailAndPassword(email, password);
    } catch (UserNotFoundException e) {
      System.out.println(e.getMessage());
    }
    return user;
  }
}
