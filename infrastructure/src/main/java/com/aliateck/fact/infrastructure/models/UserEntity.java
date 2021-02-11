package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Entity
@Table(name = "T_USER")
public class UserEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_name")
	String userName;	
	
	@Column(name = "password", nullable = false)
	String password;
	
	@Column(name = "enabled", length = 1, nullable = false)
	Boolean enabled;	

	/*

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name="T_USER_ROLE",
			joinColumns = @JoinColumn(
	                name = "USER_ID",
	                referencedColumnName = "id"
	        ),
	        inverseJoinColumns = @JoinColumn(
	                name = "ROLE_ID",
	                referencedColumnName = "id"
	        ))
	private List<RoleEntity> roles;
	
   */
	
	
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "id")
	private CompanyEntity company;

	
}
