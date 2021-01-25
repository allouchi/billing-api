package com.aliateck.fact.domaine.adapter.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.UserRoleRef;
import com.aliateck.fact.domaine.ports.api.user.UserRoleRefApiService;
import com.aliateck.fact.domaine.ports.spi.user.UserRoleRefSpiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRoleRefApiAdapter implements UserRoleRefApiService {
	UserRoleRefSpiService userRoleRefSpiService;


	@Override
	public List<UserRoleRef> getAll() {		
		return userRoleRefSpiService.findAll();
	}
	
}
