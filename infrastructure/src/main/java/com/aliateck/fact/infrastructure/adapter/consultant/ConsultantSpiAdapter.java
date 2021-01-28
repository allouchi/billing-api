package com.aliateck.fact.infrastructure.adapter.consultant;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;
import com.aliateck.fact.domaine.ports.spi.consultant.ConsultantSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.CheckEmailAdress;
import com.aliateck.fact.infrastructure.mapper.ConsultantMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.ConsultantEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.consultant.ConsultantJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultantSpiAdapter implements ConsultantSpiService {
	private ConsultantJpaRepository consultantJpaRepository;
	private ConsultantMapper consultantMapper;
	private CompanyJpaRepository companyJpaRepository;

	@Override
	public Consultant addConsultant(Consultant consultant, String siret) {

		Consultant reponse = null;
		if (consultant == null || siret == null || siret.equals("")) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		CheckEmailAdress checkEmail = CheckEmailAdress.builder().build();
		if (checkEmail.checkEmailAdress(consultant, consultantJpaRepository)) {
			final String format = String.format("L'adresse mail %s est déjà utilisée", consultant.getMail());
			throw new ServiceException(ErrorCatalog.DUPLICATE_DATA, format);

		}

		consultant.setLastName(consultant.getLastName().toUpperCase());
		String firstName = consultant.getFirstName().substring(0, 1).toUpperCase()
				+ consultant.getFirstName().substring(1, consultant.getFirstName().length());
		consultant.setFirstName(firstName);
		ConsultantEntity consultEntity = consultantMapper.fromDomainToEntity(consultant);

		try {
			Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);

			if (oCompany.isPresent()) {
				CompanyEntity companyEntity = oCompany.get();
				companyEntity.getConsultants().add(consultEntity);
				CompanyEntity cEntitySaved = companyJpaRepository.saveAndFlush(companyEntity);
				List<ConsultantEntity> savedConsultants = cEntitySaved.getConsultants();
				if (savedConsultants != null && !savedConsultants.isEmpty()) {
					for (ConsultantEntity c : savedConsultants) {
						if (c.getMail().equals(consultant.getMail())) {
							reponse = consultantMapper.fromEntityToDomain(c);
						}
					}
				}
			}

		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			log.error("error while creating new consultant", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, e);
		}

		if (reponse == null) {
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND);
		}
		return reponse;
	}

	@Override
	public Consultant updateConsultant(Consultant consultant, String siret) {
		return this.addConsultant(consultant, siret);
	}

	@Override
	public void deleteConsultant(Long id) {
		if (id == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		try {
			consultantJpaRepository.deleteById(id);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			log.error("error while deleting consultant with requested ID:" + "" + id, e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, e);
		}
	}

	@Override
	public List<Consultant> findAll(String siret) {

		List<Consultant> reponse = null;

		if (siret == null || siret.equals("")) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}
		try {

			Optional<CompanyEntity> oCompnay = companyJpaRepository.findBySiret(siret);
			if (oCompnay.isPresent()) {
				CompanyEntity entity = oCompnay.get();
				List<ConsultantEntity> oConsultant = entity.getConsultants();
				reponse = consultantMapper.fromEntityToDomain(oConsultant);
			}

		} catch (Exception e) {
			log.error("error while retrieving data type with requested siret:" + "" + siret, e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, e);
		}
		if (reponse == null || reponse.isEmpty()) {
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND);
		}
		return reponse;

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
