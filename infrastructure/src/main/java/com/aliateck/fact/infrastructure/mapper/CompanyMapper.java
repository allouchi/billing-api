package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.infrastructure.mapper.common.Mapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyMapper implements Mapper<Company, CompanyEntity> {
  private final ClientMapper clientMapper;
  private final ConsultantMapper consultantMapper;
  private final AdresseMapper adresseMapper;
  private final PrestationMapper prestationMapper;

  @Override
  public CompanyEntity fromDomainToEntity(Company domain) {
    return CompanyEntity
      .builder()
      .id(domain.getId())
      .rcsName(domain.getRcsName())
      .siret(domain.getSiret())
      .socialReason(domain.getSocialReason())
      .status(domain.getStatus())
      .tvaName(domain.getTvaName())
      .ape(domain.getApe())
      .companyAdresse(adresseMapper.fromDomainToEntity(domain.getCompanyAdresse()))
      .clients(clientMapper.fromDomainToEntity(domain.getClients()))
      .consultants(consultantMapper.fromDomainToEntity(domain.getConsultant()))
      .prestations(prestationMapper.fromDomainToEntity(domain.getPrestation()))
      .build();
  }

  @Override
  public Company fromEntityToDomain(CompanyEntity entity) {
    return Company
      .builder()
      .id(entity.getId())
      .rcsName(entity.getRcsName())
      .siret(entity.getSiret())
      .socialReason(entity.getSocialReason())
      .status(entity.getStatus())
      .tvaName(entity.getTvaName())
      .ape(entity.getApe())
      .companyAdresse(adresseMapper.fromEntityToDomain(entity.getCompanyAdresse()))
      .clients(clientMapper.fromEntityToDomain(entity.getClients()))
      .consultant(consultantMapper.fromEntityToDomain(entity.getConsultants()))
      .build();
  }
}
