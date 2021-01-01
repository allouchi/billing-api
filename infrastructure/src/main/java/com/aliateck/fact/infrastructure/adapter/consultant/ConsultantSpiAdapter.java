package com.aliateck.fact.infrastructure.adapter.consultant;

import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.ports.spi.consultant.ConsultantSpiService;
import com.aliateck.fact.infrastructure.mapper.ConsultantMapper;
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
  public void deleteById(Long id, String siret) {
    if (id == null || siret == null) {
      throw new IllegalArgumentException("id or siret are null");
    }
    Optional<CompanyEntity> oCompnay = companyJpaRepository.findBySiret(siret);
    if (oCompnay.isPresent()) {
      CompanyEntity entity = oCompnay.get();
      List<ConsultantEntity> oConsultant = entity.getConsultants();
      for (ConsultantEntity c : oConsultant) {
        if (c.getId().longValue() == id.longValue()) {
          consultantJpaRepository.deleteById(id);
        }
      }
    }
  }

  @Override
  public Consultant updateConsultant(Consultant consultant, String siret) {
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
