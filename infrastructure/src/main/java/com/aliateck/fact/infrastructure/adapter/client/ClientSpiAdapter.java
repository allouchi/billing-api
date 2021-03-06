package com.aliateck.fact.infrastructure.adapter.client;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;
import com.aliateck.fact.domaine.ports.spi.client.ClientSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.CheckEmailAdress;
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
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientSpiAdapter implements ClientSpiService {
	ClientJpaRepository clientJpaRepository;
	CompanyJpaRepository companyJpaRepository;
	ClientMapper clientMapper;
	AdresseMapper addresseMapper;

	@Override
	public Client addClient(Client client, String siret) {
		Client reponse = null;
		if (client == null || siret == null || siret.equals("")) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}
		if (client.getId() != null && client.getId() == 0) {
			client.setId(null);
		}

		if (client.getAdresseClient() != null && client.getAdresseClient().getId() == 0) {
			client.getAdresseClient().setId(null);
		}

		CheckEmailAdress checkEmail = CheckEmailAdress.builder().build();
		if (checkEmail.checkEmailAdress(client, clientJpaRepository)) {
			final String format = String.format("L'adresse mail %s est déjà utilisée", client.getEmail());
			throw new ServiceException(ErrorCatalog.DUPLICATE_DATA, format);
		}

		try {
			ClientEntity clientEntity = clientMapper.fromDomainToEntity(client);
			Optional<CompanyEntity> oCompany = companyJpaRepository.findBySiret(siret);
			if (oCompany.isPresent()) {

				CompanyEntity companyEntity = oCompany.get();
				companyEntity.getClients().add(clientEntity);
				CompanyEntity cEntitySaved = companyJpaRepository.save(companyEntity);

				List<ClientEntity> savedClients = cEntitySaved.getClients();
				if (savedClients != null && !savedClients.isEmpty()) {
					for (ClientEntity c : savedClients) {
						if (c.getEmail().equals(client.getEmail())) {
							reponse = clientMapper.fromEntityToDomain(c);
						}
					}
				}
			}

		} catch (Exception e) {
			log.error("error while updating client", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, "Un problème est survenu lors de l'ajout du client", e);
		}

		if (reponse == null) {
			throw new ServiceException(ErrorCatalog.DB_ERROR, "Un problème est survenu lors de l'ajout du client");
		}
		return reponse;
	}

	@Override
	public Client updateClient(Client client, String siret) {

		Client reponse = null;
		if (client == null || siret == null || siret.equals("")) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		Optional<ClientEntity> oldEntity = clientJpaRepository.findById(client.getId());
		if (oldEntity.isPresent() && client.getEmail() != null
				&& !client.getEmail().equalsIgnoreCase(oldEntity.get().getEmail())) {

			CheckEmailAdress checkEmail = CheckEmailAdress.builder().build();
			if (checkEmail.checkEmailAdress(client, clientJpaRepository)) {
				final String format = String.format("L'adresse mail %s est déjà utilisée", client.getEmail());
				throw new ServiceException(ErrorCatalog.DUPLICATE_DATA, format);
			}
		}

		try {

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
						reponse = clientMapper.fromEntityToDomain(domain);
					}
				}
			}

		} catch (Exception e) {
			log.error("error while updating client", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR,
					"Un problème est survenu lors de la mise à jour du client", e);
		}

		return reponse;
	}

	@Override
	public void deleteClient(Long id) {
		try {
			clientJpaRepository.deleteById(id);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			log.error("error while deleting client", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, "Impossible de supprimer le client", e);
		}

	}

	@Override
	public List<Client> findAll(String siret) {
		List<Client> reponse = null;
		if (siret == null || siret.equals("")) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		try {

			Optional<CompanyEntity> oCompnay = companyJpaRepository.findBySiret(siret);
			if (oCompnay.isPresent()) {
				CompanyEntity entity = oCompnay.get();
				List<ClientEntity> oClient = entity.getClients();
				reponse = clientMapper.fromEntityToDomain(oClient);
			}
		} catch (Exception e) {
			log.error("error while getting all clients", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR,
					"Un problème est survenu lors de la recherche des clients", e);
		}
		if (reponse == null || reponse.isEmpty()) {
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucun client enregistré !");
		}
		return reponse;
	}

	@Override
	public Client findById(Long id) {
		Client reponse = null;
		if (id == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		try {
			Optional<ClientEntity> entity = clientJpaRepository.findById(id);
			if (entity.isPresent()) {
				reponse = clientMapper.fromEntityToDomain(entity.get());
			}
		} catch (Exception e) {
			log.error("error while get client", e);
			throw new ServiceException(ErrorCatalog.DB_ERROR, "Un problème est survenu lors de la recherche du client",
					e);
		}

		if (reponse == null) {
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, "Aucun client enregistré !");
		}
		return reponse;
	}
}
