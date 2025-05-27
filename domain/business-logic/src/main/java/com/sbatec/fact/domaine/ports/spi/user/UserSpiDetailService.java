package com.sbatec.fact.domaine.ports.spi.user;

import com.sbatec.fact.domaine.business.object.User;

public interface UserSpiDetailService{
	
	User findByUserName(String userName);

}
