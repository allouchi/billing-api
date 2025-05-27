package com.sbatec.fact.domaine.ports.spi.user;

import com.sbatec.fact.domaine.business.object.User;

import java.util.List;

public interface UserSpiService {
    User addUser(User user);

    void deleteUser(User user);

    void deleteUserById(Long id);

    void deleteAll();

    void updateUser(User user);

    List<User> findAllUsers();

    User findUserById(Long id);

    User findByUserName(String userName);

    User findByUserNameAndPassword(String userName, String password);

}
