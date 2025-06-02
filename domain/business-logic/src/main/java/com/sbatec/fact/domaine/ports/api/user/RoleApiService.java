package com.sbatec.fact.domaine.ports.api.user;

import com.sbatec.fact.domaine.business.object.Role;

import java.util.List;

public interface RoleApiService {
    List<Role> getAll();
}
