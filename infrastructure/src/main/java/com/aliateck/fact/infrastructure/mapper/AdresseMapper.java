package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.infrastructure.models.AdresseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdresseMapper {

    public AdresseEntity fromDomainToEntity(Adresse domain) {
        if (domain == null) {
            return null;
        }
        return AdresseEntity
                .builder()
                .id(domain.getId())
                .numero(domain.getNumero())
                .rue(domain.getRue())
                .codePostal(domain.getCodePostal())
                .localite(domain.getLocalite())
                .pays(domain.getPays().toUpperCase())
                .build();
    }

    public Adresse fromEntityToDomain(AdresseEntity entity) {
        if (entity == null) {
            return null;
        }
        return Adresse
                .builder()
                .id(entity.getId())
                .numero(entity.getNumero())
                .rue(entity.getRue())
                .codePostal(entity.getCodePostal())
                .localite(entity.getLocalite())
                .pays(entity.getPays().toUpperCase())
                .build();
    }
}
