package com.aliateck.fact.infrastructure.adapter.company;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;
import com.aliateck.fact.domaine.ports.spi.company.CompanySpiService;
import com.aliateck.fact.infrastructure.adapter.commun.CheckEmailAdress;
import com.aliateck.fact.infrastructure.mapper.CompanyMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.user.UserJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanySpiAdapter implements CompanySpiService {
	CompanyJpaRepository companyJpaRepository;
	UserJpaRepository userJpaRepository;
	CompanyMapper companyMapper;

	@Override
	public Company addCompany(Company company) {
		Company reponse = null;
		if (company == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		if (company.getId() != null && company.getId() == 0) {
			company.setId(null);
		}

		CheckEmailAdress checkEmail = CheckEmailAdress.builder().build();
		if (checkEmail.checkEmailAdress(company, companyJpaRepository)) {
			final String format = String.format("Le siret %s est déjà utilisé", company.getSiret());
			throw new ServiceException(ErrorCatalog.DUPLICATE_DATA, format);
		}
		try {
			CompanyEntity entity = companyMapper.fromDomainToEntity(company);
			CompanyEntity baseEntity = companyJpaRepository.save(entity);
			reponse = companyMapper.fromEntityToDomain(baseEntity);
		} catch (Exception e) {
			log.error("error while creating or updating company", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, e);
		}
		if (reponse == null) {
			final String format = String.format("Problème lors de l'ajout de la société avec le siret %s",
					company.getSiret());
			throw new ServiceException(ErrorCatalog.DB_ERROR, format);
		}
		return reponse;
	}

	@Override
	public Company updateCompany(Company company) {
		return this.addCompany(company);
	}

	@Override
	public List<Company> findAll() {
		List<Company> reponse = null;
		try {
			List<CompanyEntity> listEnities = companyJpaRepository.findAll();
			reponse = companyMapper.fromEntityToDomain(listEnities);
		} catch (Exception e) {
			log.error("error while get companies", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, e);
		}

		if (reponse == null || reponse.isEmpty()) {
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucune société enregistrée !");
		}
		return reponse;
	}

	@Override
	public Company findById(Long id) {

		Company reponse = null;
		if (id == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		try {
			Optional<CompanyEntity> entity = companyJpaRepository.findById(id);
			if (entity.isPresent()) {
				reponse = companyMapper.fromEntityToDomain(entity.get());
			}
		} catch (Exception e) {
			log.error("error while get companies", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, e);
		}

		if (reponse == null) {
			final String format = String.format("La société avec l'id %s est absente", id);
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, format);
		}
		return reponse;
	}

	@Override
	public Company findByReasonSocialIgnoreCase(String reasonSocial) {

		Company reponse = null;

		if (reasonSocial == null || reasonSocial.equals("")) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		try {
			Optional<CompanyEntity> entity = companyJpaRepository.findBySocialReasonIgnoreCase(reasonSocial);
			if (entity.isPresent()) {
				reponse = companyMapper.fromEntityToDomain(entity.get());
			}
		} catch (Exception e) {
			log.error("error while get company with : " + reasonSocial, e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, e);
		}

		if (reponse == null) {
			final String format = String.format("La société avec raison soçiale %s est absente", reasonSocial);
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, format);
		}
		return reponse;
	}

	@Override
	public Company findBySiret(String siret) {

		Company reponse = null;

		if (siret == null || siret.equals("")) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		try {
			Optional<CompanyEntity> entity = companyJpaRepository.findBySiret(siret);
			if (entity.isPresent()) {
				reponse = companyMapper.fromEntityToDomain(entity.get());
			}
		} catch (Exception e) {
			log.error("error while get company with siret : " + siret, e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, e);
		}
		if (reponse == null) {
			final String format = String.format("La société avec le numéro siret %s est absente", siret);
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, format);
		}
		return reponse;
	}

	@Override
	public void deleteCompany(Long id) {
		if (id == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		try {
			companyJpaRepository.deleteById(id);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			log.error("error while deleting data company with requested ID:" + "" + id, e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, e);
		}
	}

	@Override
	public Company findByUserName(String userName) {

		Optional<CompanyEntity> company = companyJpaRepository.findByUserName(userName);
		if (company.isPresent()) {
			return companyMapper.fromEntityToDomain(company.get());
		}

		final String format = String.format("Aucune société trouvée");
		throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, format);

	}
}