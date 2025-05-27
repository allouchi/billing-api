package com.sbatec.fact.domaine.ports.spi.user;

import java.util.List;

import com.sbatec.fact.domaine.business.object.RoleRef;

public interface RoleRefSpiService {
  

  public List<RoleRef> findAll();

 
}
