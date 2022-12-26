package com.aliateck.fact.domaine.ports.spi.user;

import com.aliateck.fact.domaine.business.object.User;

import java.util.List;

public interface UserSpiService {
    User addUser(User user);

    void removeUser(User user);

    void updateUser(User user);

    List<User> findAllUsers();

    User findUserById(Long id);

    User findByUserName(String userName);

    User findByUserNameAndPassword(String userName, String password);

}
