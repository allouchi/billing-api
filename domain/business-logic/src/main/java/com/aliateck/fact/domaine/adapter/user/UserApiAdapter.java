package com.aliateck.fact.domaine.adapter.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import com.aliateck.fact.domaine.ports.spi.user.UserSpiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

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
		return userSpiService.findAllUsers();
	}

	@Override
	public User findUserById(Long id) {		
		return userSpiService.findUserById(id);
	}

	@Override
	public User findUserByMailAndPassword(String email, String password) {		
		return userSpiService.findUserByMailAndPassword(email, password);
	}

	@Override
	public User findUserByRole(String role) {

		return userSpiService.findUserByRole(role);
	}

	@Override
	public User findUserByMail(String email) {
		return userSpiService.findUserByMail(email);
	}
}
