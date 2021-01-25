package com.aliateck.fact.domaine.ports.spi.user;

import java.util.List;

import com.aliateck.fact.domaine.business.object.UserRoleRef;

public interface UserRoleRefSpiService {
  

  public List<UserRoleRef> findAll();

 
}
