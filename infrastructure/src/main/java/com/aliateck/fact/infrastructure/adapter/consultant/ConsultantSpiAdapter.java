package com.aliateck.fact.infrastructure.adapter.consultant;

import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.ports.spi.consultant.ConsultantSpiService;
import com.aliateck.fact.infrastructure.mapper.ClientMapper;
import com.aliateck.fact.infrastructure.mapper.ConsultantMapper;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.ConsultantEntity;
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

  @Override
  public void addConsultant(Consultant consultant) {
    ConsultantEntity entity = consultantMapper.fromDomainToEntity(consultant);
    consultantJpaRepository.save(entity);
  }

  @Override
  public void removeConsultant(Consultant consultant) {
    ConsultantEntity entity = consultantMapper.fromDomainToEntity(consultant);
    consultantJpaRepository.delete(entity);
  }

  @Override
  public void updateConsultant(Consultant consultant) {
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
      //      entityBase.setPrestation(
      //        prestationMapper.fromDomainToEntity(consultant.getPrestation())
      //      );
      //      entityBase.setClients(clientMapper.fromDomainToEntityList(consultant.getClients()));

      consultantJpaRepository.save(entityBase);
    }
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