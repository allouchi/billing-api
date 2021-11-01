package com.aliateck.fact.infrastructure.adapter.commun;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.infrastructure.models.ClientEntity;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.ConsultantEntity;
import com.aliateck.fact.infrastructure.models.UserEntity;
import com.aliateck.fact.infrastructure.repository.client.ClientJpaRepository;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.consultant.ConsultantJpaRepository;
import com.aliateck.fact.infrastructure.repository.user.UserJpaRepository;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckEmailAdress implements ICheckEmailAdress {

	@Override
	public boolean checkEmailAdress(Object object, Object jpaRepository) {
		if (object instanceof Consultant) {
			Consultant consult = (Consultant) object;
			ConsultantJpaRepository consultantJpaRepository = (ConsultantJpaRepository) jpaRepository;
			Optional<ConsultantEntity> entity = consultantJpaRepository.findByEmail(consult.getEmail());
			if (entity.isPresent()) {
				return true;
			}
		} else if (object instanceof Client) {
			Client client = (Client) object;
			ClientJpaRepository clientJpaRepository = (ClientJpaRepository) jpaRepository;
			Optional<ClientEntity> entity = clientJpaRepository.findByEmail(client.getEmail());
			if (entity.isPresent()) {
				return true;
			}
		} else if (object instanceof Company) {
			Company company = (Company) object;
			CompanyJpaRepository companyJpaRepository = (CompanyJpaRepository) jpaRepository;
			Optional<CompanyEntity> entity = companyJpaRepository.findBySiret(company.getSiret());
			if (entity.isPresent()) {
				return true;
			}
		} else if (object instanceof User) {
			User user = (User) object;
			UserJpaRepository userJpaRepository = (UserJpaRepository) jpaRepository;
			Optional<UserEntity> entity = userJpaRepository.findByUserName(user.getUserName());
			if (entity.isPresent()) {
				return true;
			}
		}

		return false;
	}

}
