package com.aliateck.fact.domaine.ports.api.user;

import com.aliateck.fact.domaine.business.object.RoleRef;

import java.util.List;

public interface RoleRefApiService {
    List<RoleRef> getAll();
}
