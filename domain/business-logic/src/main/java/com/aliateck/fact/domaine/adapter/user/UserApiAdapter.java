package com.aliateck.fact.domaine.adapter.user;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import com.aliateck.fact.domaine.ports.spi.user.UserSpiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserApiAdapter implements UserApiService {
    UserSpiService userSpiService;

    @Override
    public User addUser(User user) {
        return userSpiService.addUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userSpiService.deleteUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userSpiService.deleteUserById(id);
    }

    @Override
    public void deleteAll() {
        userSpiService.findAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userSpiService.updateUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userSpiService.findAllUsers();
    }

    @Override
    public User findUserById(Long id) {
        return userSpiService.findUserById(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userSpiService.findByUserName(userName);
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        return userSpiService.findByUserNameAndPassword(userName, password);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userSpiService.findByUserName(username);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                //.roles(user.getRoles().replace("ROLE_", ""))
                .build();
    }
}
