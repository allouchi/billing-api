package com.aliateck.fact.infrastructure.adapter.user;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;
import com.aliateck.fact.domaine.exception.UserAlreadyExistsException;
import com.aliateck.fact.domaine.ports.spi.user.UserSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.CheckEmailAdress;
import com.aliateck.fact.infrastructure.mapper.CompanyMapper;
import com.aliateck.fact.infrastructure.mapper.UserMapper;
import com.aliateck.fact.infrastructure.models.CompanyEntity;
import com.aliateck.fact.infrastructure.models.UserEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.user.UserJpaRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSpiAdapter implements UserSpiService {
	private UserMapper userMapper;
	private UserJpaRepository userJpaRepository;
	private CompanyJpaRepository companyJpaRepository;
	private CompanyMapper companyMapper;

	@Override
	public User addUser(User user) {

		User reponse = null;
		if (user == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}

		CheckEmailAdress checkEmail = CheckEmailAdress.builder().build();
		if (checkEmail.checkEmailAdress(user, userJpaRepository)) {
			final String format = String.format("L'adresse mail %s est déjà utilisée", user.getEmail());
			throw new ServiceException(ErrorCatalog.DUPLICATE_DATA, format);
		}

		try {

			Optional<UserEntity> oUser = userJpaRepository.findByMail(user.getEmail());

			if (oUser.isPresent()) {
				throw new UserAlreadyExistsException(user.getEmail());
			}
			Optional<CompanyEntity> company = companyJpaRepository.findById(user.getCompany().getId());
			if (company.isPresent()) {
				Company oCompany = companyMapper.fromEntityToDomain(company.get());
				user.setCompany(oCompany);
				UserEntity userEntity = userMapper.fromDomainToEntity(user);
				UserEntity entity = userJpaRepository.save(userEntity);
				reponse = userMapper.fromEntityToDomain(entity);
			}
		} catch (Exception e) {
			throw new ServiceException(ErrorCatalog.DB_ERROR, "Problème lors de l'ajout de l'utilisateur");
		}

		return reponse;
	}

	@Override
	public void removeUser(User user) {
		UserEntity userEntity = userMapper.fromDomainToEntity(user);
		userJpaRepository.delete(userEntity);
	}

	@Override
	public void updateUser(User user) {
		UserEntity userEntity = userMapper.fromDomainToEntity(user);
		userJpaRepository.save(userEntity);
	}

	@Override
	public List<User> findAllUsers() {
		List<UserEntity> usersEntity = userJpaRepository.findAll();
		return userMapper.fromEntityToDomainList(usersEntity);
	}

	@Override
	public User findUserById(Long id) {
		
		User reponse = null;
		if (id == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}
		try {
			Optional<UserEntity> entity = userJpaRepository.findById(id);
			if (entity.isPresent()) {
				reponse = userMapper.fromEntityToDomain(entity.get());
			}

		} catch (Exception e) {
			final String format = String.format("Problème lors de la recherche de l'utilisateur avec Id %s", id);
			throw new ServiceException(ErrorCatalog.DB_ERROR, format);
		}

		if (reponse == null) {
			final String format = String.format("Aucun utilisateur avec %s avec Id", id);
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, format);
		}
		return reponse;
	}

	@Override
	public User findUserByMailAndPassword(String mail, String password) {
		User reponse = null;
		if (mail == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}
		try {
			Optional<UserEntity> entity = userJpaRepository.findByMailAndPassword(mail, password);
			if (entity.isPresent()) {
				reponse = userMapper.fromEntityToDomain(entity.get());
			}

		} catch (Exception e) {
			final String format = String.format("Problème lors de la recherche de l'utilisateur avec %s", mail);
			throw new ServiceException(ErrorCatalog.DB_ERROR, format);
		}

		if (reponse == null) {
			final String format = String.format("Aucun utilisateur avec %s comme mail", mail);
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, format);
		}
		return reponse;

	}

	@Override
	public User findUserByMail(String mail) {

		User reponse = null;
		if (mail == null) {
			throw new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT);
		}
		try {

			Optional<UserEntity> entity = userJpaRepository.findByMail(mail);
			if (entity.isPresent()) {
				reponse = userMapper.fromEntityToDomain(entity.get());
			}

		} catch (Exception e) {
			final String format = String.format("Problème lors de la recherche de l'utilisateur avec %s", mail);
			throw new ServiceException(ErrorCatalog.DB_ERROR, format);
		}

		if (reponse == null) {
			final String format = String.format("Aucun utilisateur avec %s comme adresse", mail);
			throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, format);
		}
		return reponse;

	}
}
