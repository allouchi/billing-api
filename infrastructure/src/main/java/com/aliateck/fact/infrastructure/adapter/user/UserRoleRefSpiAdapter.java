package com.aliateck.fact.infrastructure.adapter.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.UserRoleRef;
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;
import com.aliateck.fact.domaine.ports.spi.user.UserRoleRefSpiService;
import com.aliateck.fact.infrastructure.mapper.RoleUserRefMapper;
import com.aliateck.fact.infrastructure.models.UserRoleRefEntity;
import com.aliateck.fact.infrastructure.repository.user.UserRoleRefJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRoleRefSpiAdapter implements UserRoleRefSpiService {

	UserRoleRefJpaRepository userRoleRefJpaRepository;
	RoleUserRefMapper roleUserRefMapper;

	@Override
	public List<UserRoleRef> findAll() {
		List<UserRoleRef> reponse = null;
		try {
			
			List<UserRoleRefEntity> entity = userRoleRefJpaRepository.findAll();
			reponse = roleUserRefMapper.fromEntityToDomainList(entity);
		} catch (Exception e) {
			throw new ServiceException(ErrorCatalog.DB_ERROR, "Problème lors de la recherche des rôles utilisateurs");
		}
		
		if (reponse == null || reponse.isEmpty()) {			
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucun role enregistré!");
		}
		return reponse;		
	}

}
