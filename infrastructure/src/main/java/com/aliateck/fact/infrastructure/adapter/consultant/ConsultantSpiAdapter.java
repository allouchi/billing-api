package com.aliateck.fact.infrastructure.adapter.consultant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.ports.spi.consultant.ConsultantSpiService;
import com.aliateck.fact.infrastructure.mapper.ConsultantMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.ConsultantEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.consultant.ConsultantJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultantSpiAdapter implements ConsultantSpiService {
	private ConsultantJpaRepository consultantJpaRepository;
	private ConsultantMapper consultantMapper;
	CompanyJpaRepository companyJpaRepository;

	@Override
	public Consultant addConsultant(Consultant consultant, String siret) {
		if (consultant == null || siret == null) {
			throw new IllegalArgumentException("Paramères nulls");
		}

		if (consultant.getId() != null && consultant.getId().longValue() == 0) {
			consultant.setId(null);
		}

		consultant.setLastName(consultant.getLastName().toUpperCase());
		String firstName = consultant.getFirstName().substring(0, 1).toUpperCase()
				+ consultant.getFirstName().substring(1, consultant.getFirstName().length());
		consultant.setFirstName(firstName);

		ConsultantEntity consultEntity = consultantMapper.fromDomainToEntity(consultant);
		Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);

		if (oCompany.isPresent()) {
			CompanyEntity companyEntity = oCompany.get();
			companyEntity.getConsultants().add(consultEntity);
			CompanyEntity cEntitySaved = companyJpaRepository.saveAndFlush(companyEntity);
			List<ConsultantEntity> savedConsultants = cEntitySaved.getConsultants();
			if (savedConsultants != null && !savedConsultants.isEmpty()) {
				for (ConsultantEntity c : savedConsultants) {
					if (c.getMail().equals(consultant.getMail())) {
						return consultantMapper.fromEntityToDomain(c);
					}
				}
			}
		}
		return null;
	}

	@Override
	public void deleteConsultant(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Id is null");
		}
		consultantJpaRepository.deleteById(id);
	}

	@Override
	public Consultant updateConsultant(Consultant consultant, String siret) {
		
		if (consultant == null || siret == null) {
			throw new IllegalArgumentException("Paramères nulls");
		}

		if (consultant.getId() != null && consultant.getId().longValue() == 0) {
			consultant.setId(null);
		}
		
		consultant.setLastName(consultant.getLastName().toUpperCase());
		String firstName = consultant.getFirstName().substring(0, 1).toUpperCase()
				+ consultant.getFirstName().substring(1, consultant.getFirstName().length());
		consultant.setFirstName(firstName);
		
		Optional<CompanyEntity> oCompnay = companyJpaRepository.findBySiret(siret);
		if (oCompnay.isPresent()) {
			CompanyEntity entity = oCompnay.get();
			List<ConsultantEntity> oConsultant = entity.getConsultants();
			for (ConsultantEntity c : oConsultant) {
				if (c.getId().longValue() == consultant.getId().longValue()) {
					ConsultantEntity nEntity = consultantMapper.fromDomainToEntity(consultant);
					ConsultantEntity domain = consultantJpaRepository.save(nEntity);
					return consultantMapper.fromEntityToDomain(domain);
				}
			}
		}
		return null;
	}

	@Override
	public List<Consultant> findAll() {
		List<Consultant> listEntities = new ArrayList<>();
		List<ConsultantEntity> entities = consultantJpaRepository.findAll();
		if (!entities.isEmpty()) {
			for (ConsultantEntity entity : entities) {
				listEntities.add(consultantMapper.fromEntityToDomain(entity));
			}
		}
		return listEntities;
	}

	@Override
	public Consultant findById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id is null");
		}
		Optional<ConsultantEntity> entity = consultantJpaRepository.findById(id);
		if (entity.isPresent()) {
			return consultantMapper.fromEntityToDomain(entity.get());
		}
		return null;
	}
}
