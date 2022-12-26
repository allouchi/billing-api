package com.aliateck.fact.domaine.ports.api.user;

import com.aliateck.fact.domaine.business.object.User;

import java.util.List;

public interface UserApiService {
    User addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    List<User> getUsers();

    User findUserById(Long id);

    User findByUserName(String userName);

    User findByUserNameAndPassword(String userName, String password);

}
