package com.aliateck.fact.domaine.ports.spi.user;

import java.util.List;

import com.aliateck.fact.domaine.business.object.User;

public interface UserSpiService {
	public User addUser(User user);

	public void removeUser(User user);

	public void updateUser(User user);

	public List<User> findAllUsers();

	public User findUserById(Long id);

	public User findByUserName(String name);

}
