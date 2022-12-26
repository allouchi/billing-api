package com.aliateck.fact.infrastructure.adapter.prestation;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Prestation addPrestation(Prestation prestation, Boolean templateChoice, String siret, Long moisPrestaId) {
        if (Objects.isNull(prestation) || Objects.isNull(siret) || Objects.isNull(moisPrestaId)) {
            throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
        }
        if (!templateChoice) {
            prestation.setClientPrestation(prestation.getClient().getSocialReason());
        }

        try {

            prestation.setDateDebut(Utils.convertFromDomainToEntityDate(prestation.getDateDebut()));
            prestation.setDateFin(Utils.convertFromDomainToEntityDate(prestation.getDateFin()));
            PrestationEntity pEntity = prestationMapper.fromDomainToEntity(prestation);
            Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
            oCompany.orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND));

            CompanyEntity cEntity = oCompany.get();
            cEntity.getPrestations().add(pEntity);
            CompanyEntity cEntitySaved = companyJpaRepository.save(cEntity);
            List<PrestationEntity> savedPrestations = cEntitySaved.getPrestations();
            List<Prestation> prestations = savedPrestations.stream()
                    .filter(e -> e.getNumeroCommande().equals(prestation.getNumeroCommande()))
                    .map(p -> prestationMapper.fromEntityToDomain(p)).collect(Collectors.toList());
            return prestations.get(0);

        } catch (Exception e) {
            log.error("error while creating new prestation", e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de l'ajout de la prestation", e);
        }
    }

    @Override
    public Prestation updatePrestation(Prestation prestation, String siret) {

        if (Objects.isNull(prestation) || Objects.isNull(siret)) {
            throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
        }

        try {
            prestation.setDateFin(Utils.convertFromDomainToEntityDate(prestation.getDateFin()));
            PrestationEntity entity = prestationMapper.fromDomainToEntity(prestation);
            PrestationEntity oEntity = prestationJpaRepository.save(entity);
            return prestationMapper.fromEntityToDomain(oEntity);
        } catch (Exception e) {
            log.error("error while updating prestation", e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de la mise à jour de la prestation", e);
        }
    }

    @Override
    public void deletePrestation(Prestation prestation) {
        if (Objects.isNull(prestation)) {
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

        if (Objects.isNull(id)) {
            throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
        }
        try {
            Optional<PrestationEntity> entity = prestationJpaRepository.findById(id);
            entity.orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucune prestation enregistrée !"));
            return prestationMapper.fromEntityToDomain(entity.get());

        } catch (Exception e) {
            log.error("error while find prestation", e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de la recherche de la prestation", e);
        }
    }

    @Override
    public List<Prestation> findAll(String siret) {

        Optional.ofNullable(siret).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        try {

            Optional<CompanyEntity> company = companyJpaRepository.findBySiret(siret);
            company.orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucune société enregistrée !"));
            List<Prestation> prestations = prestationMapper.fromEntityToDomain(company.get().getPrestations());
            Optional.ofNullable(prestations)
                    .orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucune prestation enregistrée !"));
            return prestations.stream()
                    .sorted(Comparator.comparingLong(Prestation::getId).reversed())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("error while find prestation", e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de la recherche des prestations", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional.ofNullable(id).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        try {
            prestationJpaRepository.deleteById(id);
        } catch (Exception e) {
            log.error("error while find prestation", e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de la suppression de la prestation", e);
        }
    }
}
