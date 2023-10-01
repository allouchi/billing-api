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
        if (domain == null) {
            return null;
        }
        return CompanyEntity.builder().id(domain.getId()).rcsName(domain.getRcsName())
                .siret(domain.getSiret()).socialReason(domain.getSocialReason()).status(domain.getStatus())
                .numeroTva(domain.getNumeroTva()).codeApe(domain.getCodeApe())
                .numeroIban(domain.getNumeroIban()).numeroBic(domain.getNumeroBic())
                .companyAdresse(adresseMapper.fromDomainToEntity(domain.getCompanyAdresse()))
                .clients(clientMapper.fromDomainToEntity(domain.getClients()))
                .consultants(consultantMapper.fromDomainToEntity(domain.getConsultants()))
                .prestations(prestationMapper.fromDomainToEntity(domain.getPrestations())).checked(domain.getChecked()).build();
    }

    @Override
    public Company fromEntityToDomain(CompanyEntity entity) {
        if (entity == null) {
            return null;
        }
        return Company.builder().id(entity.getId()).rcsName(entity.getRcsName())
                .siret(entity.getSiret()).socialReason(entity.getSocialReason()).status(entity.getStatus())
                .numeroTva(entity.getNumeroTva()).codeApe(entity.getCodeApe())
                .numeroIban(entity.getNumeroIban()).numeroBic(entity.getNumeroBic())
                .companyAdresse(adresseMapper.fromEntityToDomain(entity.getCompanyAdresse()))
                .clients(clientMapper.fromEntityToDomain(entity.getClients()))
                .consultants(consultantMapper.fromEntityToDomain(entity.getConsultants()))
                .prestations(prestationMapper.fromEntityToDomain(entity.getPrestations())).checked(entity.getChecked()).build();
    }
}
