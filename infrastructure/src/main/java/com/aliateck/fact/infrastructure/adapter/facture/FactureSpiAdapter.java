package com.aliateck.fact.infrastructure.adapter.facture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.common.FactureStatus;
import com.aliateck.fact.domaine.common.edition.CalculerFactureService;
import com.aliateck.fact.domaine.exception.FactureNotFoundException;
import com.aliateck.fact.domaine.ports.spi.facture.FactureSpiService;
import com.aliateck.fact.infrastructure.mapper.CompanyMapper;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.mapper.PrestationMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.models.PrestationEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
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
  CompanyJpaRepository companyJpaRepository; 
  CalculerFactureService calculerFactureService;
  FactureMapper factureMapper;
  CompanyMapper companyMapper;
  PrestationMapper prestationMapper;

  @Override
  public Facture addFacture(Facture facture, long prestationId, String numeroCommande) {	 
	  Facture fDomain = null;	  
	  Optional<PrestationEntity> oPrestation = prestationJpaRepository.findById(prestationId);
	  
	  if(oPrestation.isPresent()) {
		  PrestationEntity pEntity = oPrestation.get();		  
		  Facture factureCaculee = calculerFactureService.calculerFacture( prestationMapper.fromEntityToDomain(pEntity), facture);
		  pEntity.setFacture(factureMapper.fromDomainToEntity(factureCaculee));
		  pEntity.setNumeroCommande(numeroCommande);
		  PrestationEntity pSaved = prestationJpaRepository.saveAndFlush(pEntity);		 
	      FactureEntity  fEntity = pSaved.getFacture();
	      
	      if(fEntity != null) {
				String numeroFacture = fEntity.getNumeroFacture();
				if(numeroFacture != null) {
					String endNumero[] = numeroFacture.split("-");
					long oldNumero = Long.parseLong(endNumero[1]);
					long newNumero = oldNumero + fEntity.getId().longValue();
					fEntity.setNumeroFacture(String.valueOf(endNumero[0]+"-"+newNumero));					;
					FactureEntity oEntity = factureJpaRepository.save(fEntity);
					fDomain = factureMapper.fromEntityToDomain(oEntity);					
				}
			}		  	 
	  }	  
	  return fDomain;
  }

  @Override
  public void deleteFacture(Facture facture) {
    factureJpaRepository.delete(factureMapper.fromDomainToEntity(facture));
  }

  @Override
  public Facture updateFacture(Facture facture) {
    Optional<FactureEntity> objBase = factureJpaRepository.findById(facture.getId());

    if (objBase.isPresent()) {
      FactureEntity entityBase = objBase.get();
      entityBase.setId(facture.getId());
      entityBase.setDateEcheance(facture.getDateEcheance());
      entityBase.setDateEncaissement(facture.getDateEncaissement());
      entityBase.setDateFacturation(facture.getDateFacturation());
      entityBase.setFraisRetard(facture.getFraisRetard());
      entityBase.setPrixTotalHT(facture.getPrixTotalHT());
      entityBase.setMontantTVA(facture.getMontantTVA());
      entityBase.setPrixTotalTTC(facture.getPrixTotalTTC());
      entityBase.setNbJourRetard(facture.getNbJourRetard());
      entityBase.setFactureStatus(facture.getFactureStatus());
      entityBase.setNumeroFacture(facture.getNumeroFacture());
      FactureEntity entity =  factureJpaRepository.save(entityBase);
      return factureMapper.fromEntityToDomain(entity);
    }
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
	public List<Facture> findAllBySiret(String siret){
		List<FactureEntity> listeFactures = new ArrayList<>();
		Optional<CompanyEntity> oEntity =  companyJpaRepository.findBySiret(siret);
	
		if(oEntity.isPresent()) {
			CompanyEntity entity = oEntity.get();
			for(PrestationEntity presta : entity.getPrestations()){
				if(presta != null && presta.getFacture() != null) {
					listeFactures.add(presta.getFacture());
				}				
			}
		}		
		 return factureMapper.fromEntityToDomain(listeFactures);
		}


	@Override 
	public List<Facture> findAllByPrestation(String siret, long idPrestation){
		List<FactureEntity> listFactures = new ArrayList<>();
		Optional<CompanyEntity> oEntity = companyJpaRepository.findBySiret(siret);
		if(oEntity.isPresent()) {
			CompanyEntity oCompany = oEntity.get();
			List<PrestationEntity> pEntityList = oCompany.getPrestations();
			if(pEntityList != null && !pEntityList.isEmpty()){
				for(PrestationEntity pEntity : pEntityList ) {
					listFactures.add(pEntity.getFacture());
				}
			}			
		}
		if(!listFactures.isEmpty()) {
			return factureMapper.fromEntityToDomain(listFactures);
		}
		return null;
		
//		return oEntity.map(entity ->	
//							Optional.ofNullable(entity.getPrestations())
//								.map(prestations -> prestations
//										.stream()
//										.map(prestation -> factureMapper.fromEntityToDomain(prestation.getFacture()))
//										.collect(Collectors.toList()))
//								.orElse(Collections.emptyList()))
//				.orElseThrow(() -> new CompanyNotFoundException(siret));
	}

	@Override 
	public void deleteById(long id){
		factureJpaRepository.deleteById(id);
	}
}
