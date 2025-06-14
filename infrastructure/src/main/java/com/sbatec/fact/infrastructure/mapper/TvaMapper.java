package com.sbatec.fact.infrastructure.mapper;

import com.sbatec.fact.domaine.business.object.Tva;
import com.sbatec.fact.infrastructure.models.TvaEntity;
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
        if (domain.getId() != null && domain.getId().longValue() == 0) {
            domain.setId(null);
        }
        return TvaEntity.builder().id(domain.getId()).datePayment(domain.getDatePayment()).monthPayment(domain.getMonthPayment())
                .montantPayment(domain.getMontantPayment()).siret(domain.getSiret()).exercise(domain.getExercise()).build();
    }

    public Tva fromEntityToDomain(TvaEntity entity) {

        if (entity == null) {
            return null;
        }
        return Tva.builder().id(entity.getId()).datePayment(entity.getDatePayment()).monthPayment(entity.getMonthPayment())
                .montantPayment(entity.getMontantPayment()).siret(entity.getSiret()).exercise(entity.getExercise()).build();
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
