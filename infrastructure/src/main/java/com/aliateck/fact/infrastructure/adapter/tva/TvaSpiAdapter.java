package com.aliateck.fact.infrastructure.adapter.tva;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.business.object.TvaInfo;
import com.aliateck.fact.domaine.ports.spi.tva.TvaSpiService;
import com.aliateck.fact.infrastructure.mapper.TvaMapper;
import com.aliateck.fact.infrastructure.models.FactureEntity;
import com.aliateck.fact.infrastructure.models.TvaEntity;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;
import com.aliateck.fact.infrastructure.repository.tva.TvaJpaRepository;
import com.aliateck.util.Utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author maliane
 *
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TvaSpiAdapter implements TvaSpiService {

	private TvaMapper tvaMapper;
	// private ExerciseMapper exerciseMapper;
	private TvaJpaRepository tvaJpaRepository;
	// private ExerciseJpaRepository exerciseJpaRepository;
	private FactureJpaRepository factureJpaRepository;

	@Override
	public List<Tva> findByExercise(String exercise) {
		List<TvaEntity> e = null;
		if (exercise.equalsIgnoreCase("All")) {
			e = tvaJpaRepository.findAll();
		} else {
			e = tvaJpaRepository.findByExercise(exercise);
		}

		if (e != null) {
			return tvaMapper.fromEntityToDomain(e);
		}
		return Collections.emptyList();
	}

	@Override
	public void deleteByExercise(String exercise) {

	}

	@Override
	public void deleteById(Long id) {
		tvaJpaRepository.deleteById(id);
	}

	@Override
	public Tva addTva(Tva tva) {
		TvaEntity entity = tvaJpaRepository.save(tvaMapper.fromDomainToEntity(tva));
		return tvaMapper.fromEntityToDomain(entity);

	}

	@Override
	public void updateTva(Tva tva) {
		String datePaiement = Utils.convertFromDomainToEntityDate(tva.getDatePayment());
		tva.setDatePayment(datePaiement);
		Optional<TvaEntity> entity = tvaJpaRepository.findById(tva.getId());
		if (entity.isPresent()) {
			TvaEntity e = entity.get();
			e.setDatePayment(tva.getDatePayment());
			e.setMontantPayment(tva.getMontantPayment());
			tvaJpaRepository.saveAndFlush(e);
		}
	}

	@Override
	public Tva findById(Long id) {
		Optional<TvaEntity> o = tvaJpaRepository.findById(id);
		if (o.isPresent()) {
			return tvaMapper.fromEntityToDomain(o.get());
		}
		return null;

	}

	/**
	 * 
	 */
	@Override
	public List<Tva> findAllTva() {
		List<TvaEntity> entities = tvaJpaRepository.findAll();
		return tvaMapper.fromEntityToDomain(entities);
	}

	@Override
	public TvaInfo findTvaInfo(String exercise) {
		float sumOfTva = 0;
		float sumOfTvaPaye = 0;
		float totalHT = 0;
		TvaInfo info = new TvaInfo();

		List<FactureEntity> entities = factureJpaRepository.findAll();
		for (FactureEntity e : entities) {
			if (e.getDateEncaissement() != null && !e.getDateEncaissement().equals("")) {
				String[] dateEncaissement = e.getDateEncaissement().split("/");

				if (exercise.equalsIgnoreCase("All")) {
					sumOfTva += e.getMontantTVA();
					totalHT += e.getPrixTotalHT();
				} else if (dateEncaissement[2] != null && exercise.equals(dateEncaissement[2])) {
					sumOfTva += e.getMontantTVA();
					totalHT += e.getPrixTotalHT();
				}
			}
		}

		List<TvaEntity> listeTvaPayee = null;
		if (exercise.equalsIgnoreCase("All")) {
			listeTvaPayee = tvaJpaRepository.findAll();
		} else {
			listeTvaPayee = tvaJpaRepository.findByExercise(exercise);
		}

		for (TvaEntity e : listeTvaPayee) {
			sumOfTvaPaye += e.getMontantPayment();
		}
		info.setTotalTvaPaye(sumOfTvaPaye);
		info.setTotalTvaRestant(sumOfTva - sumOfTvaPaye);
		info.setTotalHT(totalHT);
		return info;
	}

}
