package com.aliateck.fact.infrastructure.adapter.prestation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;
import com.aliateck.fact.domaine.ports.spi.prestation.PrestationSpiService;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.prestation.PrestationJpaRepository;
import com.aliateck.util.Utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationSpiAdapter implements PrestationSpiService {

	PrestationJpaRepository prestationJpaRepository;
	CompanyJpaRepository companyJpaRepository;
	PrestationMapper prestationMapper;

	@Override
	public Prestation addPrestation(Prestation prestation, boolean templateChoice, String siret, Long moisPrestaId) {
		Prestation reponse = null;

		if (prestation == null || siret == null || siret.equals("") || moisPrestaId == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		if (prestation.getId() != null && prestation.getId() == 0) {
			prestation.setId(null);
		}

		if (!templateChoice) {
			prestation.setClientPrestation(prestation.getClient().getSocialReason());
		}

		try {

			prestation.setDateDebut(Utils.convertFromDomainToEntityDate(prestation.getDateDebut()));
			prestation.setDateFin(Utils.convertFromDomainToEntityDate(prestation.getDateFin()));
			PrestationEntity pEntity = prestationMapper.fromDomainToEntity(prestation);
			Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);

			if (oCompany.isPresent()) {
				CompanyEntity cEntity = oCompany.get();
				cEntity.getPrestations().add(pEntity);
				CompanyEntity cEntitySaved = companyJpaRepository.save(cEntity);
				List<PrestationEntity> savedPrestations = cEntitySaved.getPrestations();
				if (savedPrestations != null && !savedPrestations.isEmpty()) {
					for (PrestationEntity c : savedPrestations) {
						if (c.getNumeroCommande().equals(prestation.getNumeroCommande())) {
							reponse = prestationMapper.fromEntityToDomain(c);
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("error while creating new prestation", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR,
					"Un problème est survenu lors de l'ajout de la prestation", e);
		}
		return reponse;
	}

	@Override
	public Prestation updatePrestation(Prestation prestation, String siret) {

		Prestation reponse = null;

		if (prestation == null || siret == null || siret.equals("")) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		try {

			prestation.setDateFin(Utils.convertFromDomainToEntityDate(prestation.getDateFin()));
			PrestationEntity entity = prestationMapper.fromDomainToEntity(prestation);
			PrestationEntity oEntity = prestationJpaRepository.save(entity);
			reponse = prestationMapper.fromEntityToDomain(oEntity);
		} catch (Exception e) {
			log.error("error while updating prestation", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR,
					"Un problème est survenu lors de la mise à jour de la prestation", e);
		}
		return reponse;

	}

	@Override
	public void deletePrestation(Prestation prestation) {
		if (prestation == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}
		try {
			PrestationEntity entity = prestationMapper.fromDomainToEntity(prestation);
			prestationJpaRepository.delete(entity);

		} catch (Exception e) {
			log.error("error while deleting prestation", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR,
					"Un problème est survenu lors de la suppression de la prestation", e);
		}

	}

	@Override
	public Prestation findById(Long id) {

		Prestation reponse = null;
		if (id == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}
		try {
			Optional<PrestationEntity> entity = prestationJpaRepository.findById(id);
			if (entity.isPresent()) {
				reponse = prestationMapper.fromEntityToDomain(entity.get());
			}
		} catch (Exception e) {
			log.error("error while find prestation", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR,
					"Un problème est survenu lors de la recherche de la prestation", e);
		}

		if (reponse == null) {
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucune prestation enregistrée !");
		}

		return reponse;
	}

	@Override
	public List<Prestation> findAll(String siret) {

		List<Prestation> reponse = null;
		if (siret == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}
		try {

			Optional<CompanyEntity> oCompnay = companyJpaRepository.findBySiret(siret);
			if (oCompnay.isPresent()) {
				CompanyEntity entity = oCompnay.get();
				reponse = prestationMapper.fromEntityToDomain(entity.getPrestations());
			}

		} catch (Exception e) {
			log.error("error while find prestation", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR,
					"Un problème est survenu lors de la recherche des prestations", e);
		}

		if (reponse == null || reponse.isEmpty()) {
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucune prestation enregistrée !");
		}
		return reponse;
	}

	@Override
	public void deleteById(Long id) {

		if (id == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}
		try {
			prestationJpaRepository.deleteById(id);
		} catch (Exception e) {
			log.error("error while find prestation", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR,
					"Un problème est survenu lors de la suppression de la prestation", e);
		}
	}
}
