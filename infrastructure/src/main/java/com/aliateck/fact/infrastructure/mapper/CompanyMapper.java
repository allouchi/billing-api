package com.aliateck.fact.infrastructure.mapper;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyMapper {
  private final CompanyAdresseMapper adresseMapper;
  private final UserMapper userMapper;
  private final ClientMapper clientMapper;

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
      .users(userMapper.fromDomainToEntityList(domain.getUsers()))
      //.clients(clientMapper.fromDomainToEntityList(domain.getClients()))
      .build();
  }

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
      .users(userMapper.fromEntityToDomainList(entity.getUsers()))
      .clients(clientMapper.fromEntityToDomain(entity.getClients()))
      .build();
  }

  public List<Company> fromEntityToDomainList(List<CompanyEntity> entities) {
    List<Company> companys = new ArrayList<>();
    if (entities != null && !entities.isEmpty()) {
      for (CompanyEntity entity : entities) {
        companys.add(fromEntityToDomain(entity));
      }
    }
    return companys;
  }
}
