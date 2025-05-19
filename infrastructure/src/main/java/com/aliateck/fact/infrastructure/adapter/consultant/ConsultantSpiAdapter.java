package com.aliateck.fact.infrastructure.adapter.consultant;

import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.exception.CompanyNotFoundException;
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;
import com.aliateck.fact.domaine.ports.spi.consultant.ConsultantSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.CheckEmailAdresse;
import com.aliateck.fact.infrastructure.mapper.ConsultantMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.ConsultantEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.consultant.ConsultantJpaRepository;
import com.aliateck.util.Utils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
//@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultantSpiAdapter implements ConsultantSpiService {

    private ConsultantJpaRepository consultantJpaRepository;
    private ConsultantMapper consultantMapper;
    private CompanyJpaRepository companyJpaRepository;

    @Override
    public Consultant addConsultant(Consultant consultant, String siret) {

        if (consultant == null || siret == null || siret.equals("")) {
            throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
        }

        if (consultant.getId() != null && consultant.getId() == 0) {
            consultant.setId(null);
        }

        CheckEmailAdresse checkEmail = CheckEmailAdresse.builder().build();
        if (checkEmail.checkEmailAdresse(consultant, consultantJpaRepository)) {
            final String format =
                    String.format("L'adresse mail %s est déjà utilisée", consultant.getEmail());
            throw new ServiceException(ErrorCatalog.DUPLICATE_DATA, format);

        }
        ConsultantEntity consultEntity =
                consultantMapper.fromDomainToEntity(Utils.formatConsulantName(consultant));

        try {
            Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
            oCompany.orElseThrow(
                    () -> new CompanyNotFoundException(String.format("La société %s n'existe pas", siret)));

            CompanyEntity companyEntity = oCompany.get();
            companyEntity.getConsultants().add(consultEntity);
            CompanyEntity cEntitySaved = companyJpaRepository.saveAndFlush(companyEntity);
            Optional.ofNullable(cEntitySaved).orElseThrow(() -> new ServiceException(ErrorCatalog.DB_ERROR));
            List<Consultant> consultants = cEntitySaved.getConsultants().stream()
                    .filter(c -> c.getEmail().equals(consultant.getEmail()))
                    .map(p -> consultantMapper.fromEntityToDomain(p))
                    .toList();
            return consultants.get(0);

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("error while creating new consultant", e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de l'ajout du consultant", e);
        }
    }

    @Override
    public Consultant updateConsultant(Consultant consultant, String siret) {

        if (Objects.isNull(consultant) || Objects.isNull(siret)) {
            throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
        }

        ConsultantEntity consultEntity =
                consultantMapper.fromDomainToEntity(Utils.formatConsulantName(consultant));
        Optional<ConsultantEntity> oldEntity = consultantJpaRepository.findById(consultant.getId());
        oldEntity.orElseThrow(() -> new ServiceException(ErrorCatalog.DB_ERROR));
        if (!Objects.isNull(consultant.getEmail())
                && !consultant.getEmail().equalsIgnoreCase(oldEntity.get().getEmail())) {
            CheckEmailAdresse checkEmail = CheckEmailAdresse.builder().build();
            if (checkEmail.checkEmailAdresse(consultant, consultantJpaRepository)) {
                final String format =
                        String.format("L'adresse mail %s est déjà utilisée", consultant.getEmail());
                throw new ServiceException(ErrorCatalog.DUPLICATE_DATA, format);
            }
        }

        try {
            Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
            oCompany.orElseThrow(
                    () -> new CompanyNotFoundException(String.format("La société %s n'existe pas", siret)));
            CompanyEntity companyEntity = oCompany.get();
            companyEntity.getConsultants().add(consultEntity);
            CompanyEntity cEntitySaved = companyJpaRepository.saveAndFlush(companyEntity);
            Optional.ofNullable(cEntitySaved).orElseThrow(() -> new ServiceException(ErrorCatalog.DB_ERROR));
            List<ConsultantEntity> savedConsultants = cEntitySaved.getConsultants();
            return savedConsultants.stream()
                    .filter(s -> s.getEmail().equalsIgnoreCase(consultant.getEmail()))
                    .map(c -> consultantMapper.fromEntityToDomain(c))
                    .toList().get(0);

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("error while creating new consultant", e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de la mise à jour du consultant", e);
        }

    }

    @Override
    public void deleteConsultant(Long id) {
        Optional.ofNullable(id).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));

        try {
            consultantJpaRepository.deleteById(id);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("error while deleting consultant with requested ID:" + "" + id, e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de la suppression du consultant", e);
        }
    }

    @Override
    public List<Consultant> findAllBySiret(String siret) {

        Optional.ofNullable(siret).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        try {

            Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
            oCompany.orElseThrow(
                    () -> new CompanyNotFoundException(String.format("La société %s n'existe pas", siret)));
            CompanyEntity entity = oCompany.get();
            List<ConsultantEntity> oConsultant = entity.getConsultants();
            return Optional.ofNullable(consultantMapper.fromEntityToDomain(oConsultant))
                    .orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucun consultant enregistré !"));
        } catch (Exception e) {
            log.error("error while retrieving data type with requested siret :" + "" + siret, e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de la recherche des consultants", e);
        }
    }

    @Override
    public List<Consultant> findAll() {
        List<ConsultantEntity> consultants = consultantJpaRepository.findAll();
        return consultantMapper.fromEntityToDomain(consultants);
    }

    @Override
    public Consultant findById(Long id) {

        Optional.ofNullable(id).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        try {
            Optional<ConsultantEntity> entity = consultantJpaRepository.findById(id);
            entity.orElseThrow(
                    () -> new CompanyNotFoundException(String.format("Le consultant %s n'existe pas", id)));
            return Optional.ofNullable(consultantMapper.fromEntityToDomain(entity.get()))
                    .orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucun consultant enregistré !"));
        } catch (Exception e) {
            log.error("error while retrieving consultant with requested Id :" + "" + id, e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de la recherche du consultant", e);
        }
    }
}
