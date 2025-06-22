package com.sbatec.fact.domaine.ports.api.user;

import com.sbatec.fact.domaine.business.object.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserApiService {
    User addUser(User user);

    void deleteUser(User user);

    void deleteUserById(Long id);

    void deleteAll();

    void updateUser(User user);

    List<User> findAllUsers();

    User findUserById(Long id);

    User findByUserName(String userName);

    User findByUserNameAndPassword(String userName, String password);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
