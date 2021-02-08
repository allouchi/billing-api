package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "T_User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "user_name", nullable = false)
	String userName;

	@Column(name = "last_name")
	String lastName;
	
	@Column(name = "first_name")
	String firstName;
	
	@Column(name = "actived")
	Boolean actived;

	@Column(name = "mail", unique = true, nullable = false, length = 500)
	String mail;

	@Column(name = "password", nullable = false)
	String password;

	@ManyToMany
	@JoinTable(name="T_USER_ROLE",
			joinColumns = @JoinColumn(
	                name = "USER_ID",
	                referencedColumnName = "ID"
	        ),
	        inverseJoinColumns = @JoinColumn(
	                name = "ROLE_ID",
	                referencedColumnName = "ID"
	        ))
	private List<RoleEntity> roles;
	

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "id")
	private CompanyEntity company;

	
}
