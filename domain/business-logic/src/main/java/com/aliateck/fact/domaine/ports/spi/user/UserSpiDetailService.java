package com.aliateck.fact.domaine.ports.spi.user;

import com.aliateck.fact.domaine.business.object.User;

public interface UserSpiDetailService{
	
	User findByUserName(String userName);

}
