package com.aliateck.fact.domaine.business.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailMapper implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;	
	private boolean actived;
	private List<GrantedAuthority> roles = new ArrayList<>();
	
     
	public UserDetailMapper(User user) {
		
		 String[] split = user.getRoles().split(",");
	     for (int i = 0; i < split.length; i++) {
	    	 roles.add(new SimpleGrantedAuthority("role:"+split[i]));
	     }

		this.userName = user.getUserName();
		this.password = new BCryptPasswordEncoder().encode(user.getPassword());
		//this.password = user.getPassword();
		this.actived = user.getActived();		
				
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return roles;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return actived;
	}

}
