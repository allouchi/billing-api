package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.infrastructure.models.TvaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TvaMapper {

    //private final ExerciseMapper exerciseMapper;

    public TvaEntity fromDomainToEntity(Tva domain) {
        if (domain == null) {
            return null;
        }

        return TvaEntity.builder().id(domain.getId()).datePayment(domain.getDatePayment())
                .montantPayment(domain.getMontantPayment()).siret(domain.getSiret()).build();
    }

    public Tva fromEntityToDomain(TvaEntity entity) {

        if (entity == null) {
            return null;
        }
        return Tva.builder().id(entity.getId()).datePayment(entity.getDatePayment())
                .montantPayment(entity.getMontantPayment()).siret(entity.getSiret()).build();
    }

    public List<Tva> fromEntityToDomain(List<TvaEntity> entities) {

        if (entities != null) {
            return entities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<TvaEntity> fromDomainToEntity(List<Tva> domains) {
        if (domains != null) {
            return domains.stream().map(this::fromDomainToEntity).collect(Collectors.toList());
        }
        return Collections.emptyList();

    }

}
