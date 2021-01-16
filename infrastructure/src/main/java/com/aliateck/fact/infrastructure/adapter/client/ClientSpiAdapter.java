package com.aliateck.fact.infrastructure.adapter.client;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.ports.spi.client.ClientSpiService;
import com.aliateck.fact.infrastructure.mapper.AdresseMapper;
import com.aliateck.fact.infrastructure.mapper.ClientMapper;
import com.aliateck.fact.infrastructure.models.AdresseEntity;
import com.aliateck.fact.infrastructure.models.ClientEntity;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.repository.client.ClientJpaRepository;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientSpiAdapter implements ClientSpiService {
	ClientJpaRepository clientJpaRepository;
	CompanyJpaRepository companyJpaRepository;
	ClientMapper clientMapper;
	AdresseMapper addresseMapper;

	@Override
	public Client addClient(Client client, String siret) {
		if (client.getId() != null && client.getId().longValue() == 0) {
			client.setId(null);
		}

		ClientEntity clientEntity = clientMapper.fromDomainToEntity(client);
		Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
		if (oCompany.isPresent()) {

			CompanyEntity companyEntity = oCompany.get();
			companyEntity.getClients().add(clientEntity);
			CompanyEntity cEntitySaved = companyJpaRepository.save(companyEntity);
			
			List<ClientEntity> savedClients = cEntitySaved.getClients();
			if (savedClients != null && !savedClients.isEmpty()) {
				for (ClientEntity c : savedClients) {
					if (c.getMail().equals(client.getMail())) {
						return clientMapper.fromEntityToDomain(c);
					}
				}
			}
		}
		return null;
	}

	@Override
	public void deleteClient(long id) {
		clientJpaRepository.deleteById(id);
	}

	@Override
	public Client updateClient(Client client, String siret) {
		Optional<CompanyEntity> oCompnay = companyJpaRepository.findBySiret(siret);
		Adresse newAdresse = client.getAdresseClient();
		AdresseEntity newEntityAdresse = addresseMapper.fromDomainToEntity(newAdresse);
		if (oCompnay.isPresent()) {
			CompanyEntity entity = oCompnay.get();
			List<ClientEntity> oClient = entity.getClients();
			for (ClientEntity cEntity : oClient) {
				if (cEntity.getId().longValue() == client.getId().longValue()) {
					ClientEntity nEntity = clientMapper.fromDomainToEntity(client);
					cEntity.setAdresseClient(newEntityAdresse);
					ClientEntity domain = clientJpaRepository.save(nEntity);
					return clientMapper.fromEntityToDomain(domain);
				}
			}
		}
		return null;
	}

	@Override
	public List<Client> findAll(String siret) {
		Optional<CompanyEntity> oCompnay = companyJpaRepository.findBySiret(siret);
		if (oCompnay.isPresent()) {
			CompanyEntity entity = oCompnay.get();
			List<ClientEntity> oClient = entity.getClients();
			return clientMapper.fromEntityToDomain(oClient);
		}
		return Collections.emptyList();
	}

	@Override
	public Client findById(long id) {
		Client client = null;
		Optional<ClientEntity> entity = clientJpaRepository.findById(id);
		if (entity.isPresent()) {
			client = clientMapper.fromEntityToDomain(entity.get());
		}
		return client;
	}
}
