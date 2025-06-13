package com.sbatec.fact.domaine.ports.api.user;


import com.sbatec.fact.domaine.business.object.User;

public interface UserApiDetailService {

    User findByUserName(String name);


}
