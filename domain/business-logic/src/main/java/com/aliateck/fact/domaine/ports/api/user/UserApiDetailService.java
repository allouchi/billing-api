package com.aliateck.fact.domaine.ports.api.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.aliateck.fact.domaine.business.object.User;

public interface UserApiDetailService extends UserDetailsService{
  
	 public User findByUserName(String name);  
 
  
}
