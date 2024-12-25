package com.aliateck.fact.infrastructure.adapter.user;

import com.aliateck.fact.domaine.business.object.RoleRef;
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;
import com.aliateck.fact.domaine.ports.spi.user.RoleRefSpiService;
import com.aliateck.fact.infrastructure.mapper.RoleRefMapper;
import com.aliateck.fact.infrastructure.models.RoleRefEntity;
import com.aliateck.fact.infrastructure.repository.user.RoleRefJpaRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRoleRefSpiAdapter implements RoleRefSpiService {

    RoleRefJpaRepository userRoleRefJpaRepository;
    RoleRefMapper roleUserRefMapper;

    @Override
    public List<RoleRef> findAll() {
        List<RoleRef> reponse = null;
        try {

            List<RoleRefEntity> entity = userRoleRefJpaRepository.findAll();
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
