package com.aliateck.fact.infrastructure.repository.facture;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliateck.fact.infrastructure.models.FactureEntity;

@Repository
public interface FactureJpaRepository extends JpaRepository<FactureEntity, Long> {

	public FactureEntity getByNumeroFacture(String numeroFacture);

	public List<FactureEntity> findByFactureStatus(boolean status);

	public List<FactureEntity> findByDateEcheance(Date dateEcheance);

	public List<FactureEntity> findByDateEncaissement(Date dateEncaissement);

	public List<FactureEntity> findByDateFacturation(Date dateFacturation);

	//@Query(value = "SELECT * FROM T_Facture f WHERE f.date_encaissement IS NULL", nativeQuery = true)
	public List<FactureEntity> findByDateEncaissementIsNull();

}
