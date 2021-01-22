package com.aliateck.fact.domaine.ports.spi.user;

import com.aliateck.fact.domaine.business.object.User;
import java.util.List;

public interface UserSpiService {
  public void addUser(User user);

  public void removeUser(User user);

  public void updateUser(User user);

  public List<User> findAllUsers();

  public User findUserById(long id);

  public User findUserByRole(String role);

  public User findUserByMailAndPassword(String email, String password);
  
  public User findUserByMail(String email);
}
