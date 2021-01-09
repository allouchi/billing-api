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
	  if(consultant.getId()!= null && consultant.getId().longValue() == 0) {
		  consultant.setId(null);  
	  }
	  
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
