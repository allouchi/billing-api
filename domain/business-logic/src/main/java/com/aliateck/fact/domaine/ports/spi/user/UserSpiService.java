package com.aliateck.fact.domaine.ports.spi.user;

import java.util.List;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.exception.UserNotFoundException;

public interface UserSpiService {	
	
	public void addUser(User user) ;

	public void removeUser(User user);

	public void updateUser(User user);

	public List<User> getAllUsers() throws UserNotFoundException ;

	public User getUserById(long id) throws UserNotFoundException;
	
	public User getUserByMailAndPassword(String email, String password) throws UserNotFoundException;

}
