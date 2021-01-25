package com.aliateck.fact.domaine.ports.api.user;

import com.aliateck.fact.domaine.business.object.User;
import java.util.List;

public interface UserApiService {
  public User addUser(User user);

  public void deleteUser(User user);

  public void updateUser(User user);

  public List<User> getUsers();

  public User findUserById(Long id);

  public User findUserByMailAndPassword(String email, String password);
  
  public User findUserByMail(String email);
}
