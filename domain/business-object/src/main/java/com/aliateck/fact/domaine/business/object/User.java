package com.aliateck.fact.domaine.business.object;

import java.io.Serializable;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	String userName;
	String firstName;	
	String lastName;
	String password;
	Boolean actived;		
	List<Role> roles;	
	Company company;	
	
}
