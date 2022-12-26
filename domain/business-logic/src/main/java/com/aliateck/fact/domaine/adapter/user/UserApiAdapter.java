package com.aliateck.fact.domaine.adapter.user;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import com.aliateck.fact.domaine.ports.spi.user.UserSpiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
        userSpiService.removeUser(user);
    }

    @Override
    public void updateUser(User user) {
        userSpiService.updateUser(user);
    }

    @Override
    public List<User> getUsers() {
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
}
