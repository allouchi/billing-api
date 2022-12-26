package com.aliateck.fact.domaine.ports.api.user;


import com.aliateck.fact.domaine.business.object.User;

public interface UserApiDetailService {

    User findByUserName(String name);


}
