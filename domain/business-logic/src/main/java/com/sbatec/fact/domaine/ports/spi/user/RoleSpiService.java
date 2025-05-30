package com.sbatec.fact.domaine.ports.spi.user;

import java.util.List;

import com.sbatec.fact.domaine.business.object.Role;
import com.sbatec.fact.domaine.business.object.RoleRef;

public interface RoleSpiService {
  

  public List<Role> findAll();
}
