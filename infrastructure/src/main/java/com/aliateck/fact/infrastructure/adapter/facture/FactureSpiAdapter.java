package com.aliateck.fact.infrastructure.adapter.facture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.common.edition.CalculerFactureService;
import com.aliateck.fact.domaine.exception.FactureNotFoundException;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.CommonSpiEntityService;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;
import com.aliateck.fact.infrastructure.repository.prestation.PrestationJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FactureSpiAdapter implements FactureSpiService {
  FactureJpaRepository factureJpaRepository;
  PrestationJpaRepository prestationJpaRepository;
  CalculerFactureService calculerFactureService;
  FactureMapper factureMapper;
  PrestationMapper prestationMapper;
  CommonSpiEntityService commonSpiEntityService;

  @Override
  public Facture addFacture(String siret, Facture factureParam, long prestationId) {	
	  Facture fDomain = null;
	  if (factureParam.getId() != null && factureParam.getId().longValue() == 0) {
		  factureParam.setId(null);
	  }
	  PrestationEntity prestaEntity =  commonSpiEntityService.findPrestationById(siret, prestationId);
	  FactureEntity factEntity =  commonSpiEntityService.findFactureById(siret, prestationId, factureParam.getId());
	 
	  if(factEntity != null && prestaEntity != null) {
		  List<FactureEntity> listFactures = new ArrayList<>();
		  Prestation prestation  = prestationMapper.fromEntityToDomain(prestaEntity); 
		  Facture facture  = factureMapper.fromEntityToDomain(factEntity);
		  Facture factureCaculee = calculerFactureService.calculerFacture(prestation, facture);
		  listFactures.add(factureMapper.fromDomainToEntity(factureCaculee));
		  prestaEntity.setFactures(listFactures);
		  PrestationEntity pSaved = prestationJpaRepository.saveAndFlush(prestaEntity);
		  List<FactureEntity>  fEntities = pSaved.getFactures();
		  for(FactureEntity entity  : fEntities ) {
			  if(entity.getNumeroCommande().equalsIgnoreCase(factureParam.getNumeroCommande())) {
				  String numeroFacture = entity.getNumeroFacture();
					if(numeroFacture != null) {
						String endNumero[] = numeroFacture.split("-");
						long oldNumero = Long.parseLong(endNumero[1]);
						long newNumero = oldNumero + entity.getId().longValue();
						entity.setNumeroFacture(String.valueOf(endNumero[0]+"-"+newNumero));					;
						FactureEntity oEntity = factureJpaRepository.save(entity); 
						fDomain = factureMapper.fromEntityToDomain(oEntity);						
					}
			  }
		  }	  
	  }
	  return fDomain;
  }
  
  @Override 
  public List<Facture> findAllByPrestation(String siret, long idPrestation){		
		List<FactureEntity> entities = commonSpiEntityService.findFacturesByPrestation(siret, idPrestation);
		if(entities != null && !entities.isEmpty()) {
			 return factureMapper.fromEntityToDomain(entities);
		}
		return Collections.emptyList();		
	}

  @Override 
	public void deleteById(String siret, long prestationId, long factureId){		
	  FactureEntity factureEntity =  commonSpiEntityService.findFactureById(siret, prestationId, factureId);
	  factureJpaRepository.delete(factureEntity);
	}

  @Override
  public void deleteFacture(String siret, Facture facture, long prestationId) {
    factureJpaRepository.delete(factureMapper.fromDomainToEntity(facture));
  }

  @Override
  public Facture updateFacture(String siret, Facture facture, long prestationId) {
   
    
    return null;
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
        "Facture not found avec numero : " + numeroFacture
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
        "Facture not found avec date echeance : " + dateEcheance
      );
    }
    return factureMapper.fromEntityToDomain(entities);
  }

  @Override
  public List<Facture> findByDateEncaissement(Date dateEncaissement) {
    List<FactureEntity> entitys = factureJpaRepository.findByDateEncaissement(dateEncaissement);
    if (entitys == null || entitys.isEmpty()) {
      throw new FactureNotFoundException(
        "Facture not found avec date encaissement : " + dateEncaissement
      );
    }
    return factureMapper.fromEntityToDomain(entitys);
  }

  @Override 
  public List<Facture> findAllBySiret(String siret){
	List<FactureEntity> entities  = commonSpiEntityService.findAllFacturesBySiret(siret);
	 return factureMapper.fromEntityToDomain(entities);
  }	
	
}
