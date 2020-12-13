package com.aliateck.fact.infrastructure.adapter.facture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.exception.FactureNotFoundException;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureSpiAdapter implements FactureSpiService {
  FactureJpaRepository factureJpaRepository;
  CompanyJpaRepository companyJpaRepository;  
  FactureMapper factureMapper;

  @Override
  public void addFacture(Facture facture) {	  
	  factureJpaRepository.save(factureMapper.fromDomainToEntity(facture));
  }

  @Override
  public void deleteFacture(Facture facture) {
    factureJpaRepository.delete(factureMapper.fromDomainToEntity(facture));
  }

  @Override
  public void updateFacture(Facture facture) {
    Optional<FactureEntity> objBase = factureJpaRepository.findById(facture.getId());

    if (objBase.isPresent()) {
      FactureEntity entityBase = objBase.get();
      entityBase.setId(facture.getId());
      entityBase.setDateEcheance(facture.getDateEcheance());
      entityBase.setDateEncaissement(facture.getDateEncaissement());
      entityBase.setDateFacturation(facture.getDateFacturation());
      entityBase.setFraisRetard(facture.getFraisRetard());
      entityBase.setPrixTotalHT(facture.getPrixTotalHT());
      entityBase.setTva(facture.getTva());
      entityBase.setPrixTotalTTC(facture.getPrixTotalTTC());
      entityBase.setNbJourRetard(facture.getNbJourRetard());
      entityBase.setFactureStatus(facture.getFactureStatus());
      entityBase.setNumeroFacture(facture.getNumeroFacture());
      factureJpaRepository.save(entityBase);
    }
  }

  @Override
  public Facture findById(long id) {
    Optional<FactureEntity> entity = factureJpaRepository.findById(id);
    if (entity.isPresent()) {
      return factureMapper.fromEntityToDomain(entity.get());
    } else {
      throw new FactureNotFoundException("Facture not found");
    }
  }

  @Override
  public Facture findByNumeroFacture(String numeroFacture) {
    FactureEntity entity = factureJpaRepository.getByNumeroFacture(numeroFacture);
    if (entity == null) {
      throw new FactureNotFoundException(
        "Facture not found avec numÃƒÂ©ro : " + numeroFacture
      );
    }
    return factureMapper.fromEntityToDomain(entity);
  }

  @Override
  public List<Facture> findByFactureStatus(boolean statusFacture) {
    List<FactureEntity> entitys = factureJpaRepository.findByFactureStatus(statusFacture);
    if (entitys == null || entitys.isEmpty()) {
      throw new FactureNotFoundException(
        "Facture not found avec status : " + statusFacture
      );
    }
    return factureMapper.fromEntityToDomain(entitys);
  }

  @Override
  public List<Facture> findByDateEcheance(Date dateEcheance) {
    List<FactureEntity> entities = factureJpaRepository.findByDateEcheance(dateEcheance);

    if (entities == null || entities.isEmpty()) {
      throw new FactureNotFoundException(
        "Facture not found avec date echÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©ance : " + dateEcheance
      );
    }
    return factureMapper.fromEntityToDomain(entities);
  }

  @Override
  public List<Facture> findByDateEncaissement(Date dateEncaissement) {
    List<FactureEntity> entitys = factureJpaRepository.findByDateEncaissement(
      dateEncaissement
    );
    if (entitys == null || entitys.isEmpty()) {
      throw new FactureNotFoundException(
        "Facture not found avec date encaissement : " + dateEncaissement
      );
    }
    return factureMapper.fromEntityToDomain(entitys);
  }

@Override 
public List<Facture> findAll(){
	List<FactureEntity> entities =  factureJpaRepository.findAll();
	
	if (entities.isEmpty()) {
	      throw new FactureNotFoundException(
	        "Factures not found"
	      );
	    }
	    return factureMapper.fromEntityToDomain(entities);
	}


@Override public List<Facture> findBySiret(String siret){
	
	Optional<CompanyEntity> company = companyJpaRepository.findBySiret(siret);
	List<FactureEntity> listFacture = new ArrayList<>();
	if(company.isPresent()) {
		CompanyEntity entity = company.get();
		List<PrestationEntity> prestas = entity.getPrestations();
		for(PrestationEntity presta : prestas ) {
			listFacture.add(presta.getFacture());
		}		
	}	
	return factureMapper.fromEntityToDomain(listFacture);
	
	}
 
}
