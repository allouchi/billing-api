package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.infrastructure.models.ConsultantEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultantMapper {

	public ConsultantEntity fromDomainToEntity(Consultant domain) {
		if (domain == null) {
			return null;
		}
		return ConsultantEntity.builder().id(domain.getId()).firstName(domain.getFirstName())
				.lastName(domain.getLastName()).fonction(domain.getFonction()).email(domain.getEmail()).build();
	}

	public Consultant fromEntityToDomain(ConsultantEntity entity) {
		if (entity == null) {
			return null;
		}
		return Consultant.builder().id(entity.getId()).firstName(entity.getFirstName()).lastName(entity.getLastName())
				.fonction(entity.getFonction()).email(entity.getEmail()).build();
	}

	public List<Consultant> fromEntityToDomain(List<ConsultantEntity> entities) {

		if (entities != null) {
			return entities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public List<ConsultantEntity> fromDomainToEntity(List<Consultant> domains) {
		if (domains != null) {
			return domains.stream().map(this::fromDomainToEntity).collect(Collectors.toList());
		}
		return Collections.emptyList();

	}
}
