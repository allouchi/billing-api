package com.aliateck.fact.infrastructure.adapter.consultant;

import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.ports.spi.consultant.ConsultantSpiService;
import com.aliateck.fact.infrastructure.mapper.ClientMapper;
import com.aliateck.fact.infrastructure.mapper.ConsultantMapper;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.ConsultantEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.consultant.ConsultantJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultantSpiAdapter implements ConsultantSpiService {
  private ConsultantJpaRepository consultantJpaRepository;
  private ConsultantMapper consultantMapper;
  private PrestationMapper prestationMapper;
  private ClientMapper clientMapper;
  CompanyJpaRepository companyJpaRepository;

  @Override
  public Consultant addConsultant(Consultant consultant, String siret) {
    Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
    return oCompany
      .map(
        company -> {
          List<ConsultantEntity> consultants = company.getConsultants();
          ConsultantEntity entity = consultantMapper.fromDomainToEntity(consultant);
          consultants.add(entity);
          company.setConsultants(consultants);
          companyJpaRepository.save(company);
          ConsultantEntity savedConsultant = company
            .getConsultants()
            .stream()
            .filter(c -> c.getMail().equalsIgnoreCase(consultant.getMail()))
            .findFirst()
            .orElseGet(null);
          return consultantMapper.fromEntityToDomain(savedConsultant);
        }
      )
      .orElse(null);
  }

  @Override
  public void removeConsultant(Consultant consultant) {
    ConsultantEntity entity = consultantMapper.fromDomainToEntity(consultant);
    consultantJpaRepository.delete(entity);
  }

  @Override
  public Consultant updateConsultant(Consultant consultant) {
    ConsultantEntity entity = consultantMapper.fromDomainToEntity(consultant);

    Optional<ConsultantEntity> objBase = consultantJpaRepository.findById(
      consultant.getId()
    );
    if (objBase.isPresent()) {
      ConsultantEntity entityBase = objBase.get();
      entityBase.setId(entity.getId());
      entityBase.setFirstName(consultant.getFirstName());
      entityBase.setLastName(consultant.getLastName());
      entityBase.setMail(consultant.getMail());
      ConsultantEntity domain = consultantJpaRepository.save(entityBase);
      return consultantMapper.fromEntityToDomain(domain);
    }
    return null;
  }

  @Override
  public List<Consultant> getAllConsultants() {
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
  public Consultant getConsultantById(long id) {
    Optional<ConsultantEntity> entity = consultantJpaRepository.findById(id);
    if (entity.isPresent()) {
      return consultantMapper.fromEntityToDomain(entity.get());
    }
    return null;
  }
}
