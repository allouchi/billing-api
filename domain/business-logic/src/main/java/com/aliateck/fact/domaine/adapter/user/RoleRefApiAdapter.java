package com.aliateck.fact.domaine.adapter.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.RoleRef;
import com.aliateck.fact.domaine.ports.api.user.RoleRefApiService;
import com.aliateck.fact.domaine.ports.spi.user.RoleRefSpiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleRefApiAdapter implements RoleRefApiService {
	RoleRefSpiService roleRefSpiService;


	@Override
	public List<RoleRef> getAll() {		
		return roleRefSpiService.findAll();
	}
	
}
