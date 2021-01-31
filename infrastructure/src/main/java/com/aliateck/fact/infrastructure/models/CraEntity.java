package com.aliateck.fact.infrastructure.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "T_Cra")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class CraEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;
	
	@Column(name = "nbJoursOuvres")
	private String nbJoursOuvres;
	
	@ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name="name")   
	@Column(name = "joursSemaine")
	private Map<String, String> joursSemaine;
	
	@ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name="name") 
	@Column(name = "joursRepos")
	private Map<String, String> joursRepos;
	

}
