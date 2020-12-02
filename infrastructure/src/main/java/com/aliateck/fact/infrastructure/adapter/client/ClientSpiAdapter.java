package com.aliateck.fact.infrastructure.adapter.client;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.ports.spi.client.ClientSpiService;
import com.aliateck.fact.infrastructure.mapper.ClientMapper;
import com.aliateck.fact.infrastructure.models.ClientEntity;
import com.aliateck.fact.infrastructure.repository.client.ClientJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientSpiAdapter implements ClientSpiService{
	
	
	ClientJpaRepository clientJpaRepository;
	ClientMapper mapper;
	
	@Override
	public void addClient(Client client) {
		ClientEntity entity = mapper.fromDomainToEntity(client);
		clientJpaRepository.save(entity);
		
	}

	@Override
	public void removeClient(Client client) {
		ClientEntity entity = mapper.fromDomainToEntity(client);
		clientJpaRepository.delete(entity);
		
	}

	@Override
	public void updateClient(Client client) {
		ClientEntity entity = mapper.fromDomainToEntity(client);
		clientJpaRepository.save(entity);
		
	}

	@Override
	public List<Client> findAllClients() {
		
		List<ClientEntity> clientsEntity = clientJpaRepository.findAll();		
		return mapper.fromEntityToDomain(clientsEntity);		
		
	}	

	@Override
	public Client findClientById(long id) {
		
		Client client = null;
		Optional<ClientEntity> entity =  clientJpaRepository.findById(id);
		if(entity.isPresent()) {
			client = mapper.fromEntityToDomain(entity.get());
		}		
		return client;		
	}
	
	
}
