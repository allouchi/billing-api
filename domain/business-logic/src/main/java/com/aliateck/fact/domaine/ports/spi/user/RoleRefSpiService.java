package com.aliateck.fact.domaine.ports.spi.user;

import java.util.List;

import com.aliateck.fact.domaine.business.object.RoleRef;

public interface RoleRefSpiService {
  

  public List<RoleRef> findAll();

 
}
