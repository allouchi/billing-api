package com.sbatec.fact.domaine.adapter.user;

import java.util.List;

import com.sbatec.fact.domaine.business.object.Role;
import com.sbatec.fact.domaine.ports.api.user.RoleApiService;
import org.springframework.stereotype.Service;

import com.sbatec.fact.domaine.ports.spi.user.RoleSpiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleApiAdapter implements RoleApiService {
	RoleSpiService roleSpiService;


	@Override
	public List<Role> getAll() {
		return roleSpiService.findAll();
	}

	@Override
	public Role findByRoleName(String roleName) {
		return roleSpiService.findByRoleName(roleName);
	}

}
