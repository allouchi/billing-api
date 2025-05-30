package com.sbatec.fact.infrastructure.adapter.user;

import com.sbatec.fact.domaine.business.object.Role;
import com.sbatec.fact.domaine.exception.ErrorCatalog;
import com.sbatec.fact.domaine.exception.ServiceException;
import com.sbatec.fact.domaine.ports.spi.user.RoleSpiService;
import com.sbatec.fact.infrastructure.mapper.RoleRefMapper;
import com.sbatec.fact.infrastructure.models.RoleEntity;
import com.sbatec.fact.infrastructure.repository.user.RoleJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRoleSpiAdapter implements RoleSpiService {

    RoleJpaRepository userRoleJpaRepository;
    RoleRefMapper roleUserRefMapper;

    @Override
    public List<Role> findAll() {
        List<Role> reponse = null;
        try {

            List<RoleEntity> entity = userRoleJpaRepository.findAll();
            reponse = roleUserRefMapper.fromEntityToDomainList(entity);
        } catch (Exception e) {
            throw new ServiceException(ErrorCatalog.DB_ERROR, "Problème lors de la recherche des rôles utilisateurs");
        }

        if (reponse == null || reponse.isEmpty()) {
            throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucun role enregistré !");
        }
        return reponse;
    }


}
