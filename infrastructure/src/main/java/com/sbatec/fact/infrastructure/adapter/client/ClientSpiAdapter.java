package com.sbatec.fact.infrastructure.adapter.client;

import com.sbatec.fact.domaine.business.object.Client;
import com.sbatec.fact.domaine.exception.ErrorCatalog;
import com.sbatec.fact.domaine.exception.ServiceException;
import com.sbatec.fact.domaine.ports.spi.client.ClientSpiService;
import com.sbatec.fact.infrastructure.adapter.commun.CheckEmailAdresse;
import com.sbatec.fact.infrastructure.mapper.AdresseMapper;
import com.sbatec.fact.infrastructure.mapper.ClientMapper;
import com.sbatec.fact.infrastructure.models.ClientEntity;
import com.sbatec.fact.infrastructure.models.CompanyEntity;
import com.sbatec.fact.infrastructure.repository.client.ClientJpaRepository;
import com.sbatec.fact.infrastructure.repository.company.CompanyJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@Transactional
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
        CheckEmailAdresse checkEmail = CheckEmailAdresse.builder().build();
        if (checkEmail.checkEmailAdresse(client, clientJpaRepository)) {
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
    public Client updateClient(Client requestClient, String siret) {

        Client reponse = null;
        if (requestClient == null || siret == null || siret.equals("")) {
            throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
        }

        Optional<ClientEntity> oldEntity = clientJpaRepository.findById(requestClient.getId());
        if (oldEntity.isPresent() && requestClient.getEmail() != null
                && !requestClient.getEmail().equalsIgnoreCase(oldEntity.get().getEmail())) {

            CheckEmailAdresse checkEmail = CheckEmailAdresse.builder().build();
            if (checkEmail.checkEmailAdresse(requestClient, clientJpaRepository)) {
                final String format = String.format("L'adresse mail %s est déjà utilisée", requestClient.getEmail());
                throw new ServiceException(ErrorCatalog.DUPLICATE_DATA, format);
            }
        }

        try {
            ClientEntity nEntity = null;
            Optional<CompanyEntity> oCompnay = companyJpaRepository.findBySiret(siret);
            if (oCompnay.isPresent()) {
                CompanyEntity entityCompany = oCompnay.get();
                List<ClientEntity> oClients = entityCompany.getClients();
                for (ClientEntity cEntity : oClients) {
                    if (cEntity.getId().longValue() == requestClient.getId().longValue()) {
                        nEntity = clientMapper.fromDomainToEntity(requestClient);
                        oClients.add(nEntity);
                        entityCompany.setClients(oClients);
                        break;
                    }
                }
                companyJpaRepository.save(entityCompany);
                reponse = clientMapper.fromEntityToDomain(nEntity);
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
    public List<Client> findAllBySiret(String siret) {

        Optional.ofNullable(siret).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        try {

            Optional<CompanyEntity> oCompnay = companyJpaRepository.findBySiret(siret);
            Optional.ofNullable(oCompnay).orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND));
            List<ClientEntity> oClient = oCompnay.get().getClients();
            Optional.ofNullable(oClient).orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND));
            return oClient.stream().map((c) -> clientMapper.fromEntityToDomain(c)).collect(Collectors.toList());

        } catch (Exception e) {
            log.error("error while getting all clients", e);
            throw new ServiceException(ErrorCatalog.DB_ERROR,
                    "Un problème est survenu lors de la recherche des clients", e);
        }
    }

    @Override
    public List<Client> findAll() {
        List<ClientEntity> clients = clientJpaRepository.findAll();
        return clientMapper.fromEntityToDomain(clients);
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
