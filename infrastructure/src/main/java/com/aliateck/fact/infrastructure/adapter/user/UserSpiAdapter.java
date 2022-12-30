package com.aliateck.fact.infrastructure.adapter.user;

import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.exception.ErrorCatalog;
import com.aliateck.fact.domaine.exception.ServiceException;
import com.aliateck.fact.domaine.exception.UserNotFoundException;
import com.aliateck.fact.domaine.ports.spi.user.UserSpiService;
import com.aliateck.fact.infrastructure.adapter.commun.CheckEmailAdresse;
import com.aliateck.fact.infrastructure.mapper.CompanyMapper;
import com.aliateck.fact.infrastructure.mapper.UserMapper;
import com.aliateck.fact.infrastructure.models.UserEntity;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.user.UserJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@ToString
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSpiAdapter implements UserSpiService {
    UserMapper userMapper;
    UserJpaRepository userJpaRepository;
    CompanyJpaRepository companyJpaRepository;
    CompanyMapper companyMapper;


    @Override
    public User addUser(User user) {

        Optional.ofNullable(user).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        CheckEmailAdresse checkEmail = CheckEmailAdresse.builder().build();
        if (checkEmail.checkEmailAdresse(user, userJpaRepository)) {
            final String format = String.format("L'adresse mail %s est déjà utilisée", user.getUserName());
            throw new ServiceException(ErrorCatalog.DUPLICATE_DATA, format);
        }

        try {
            UserEntity entity = userJpaRepository.save(userMapper.fromDomainToEntity(user));
            return userMapper.fromEntityToDomain(entity);
        } catch (Exception e) {
            log.error("error while creating new user", e);
            throw new ServiceException(ErrorCatalog.DB_ERROR, e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        userJpaRepository.deleteAll();
    }

    @Override
    public void deleteUser(User user) {
        Optional.ofNullable(user).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        UserEntity userEntity = userMapper.fromDomainToEntity(user);
        userJpaRepository.delete(userEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        Optional.ofNullable(user).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        UserEntity userEntity = userMapper.fromDomainToEntity(user);
        userJpaRepository.save(userEntity);
    }

    @Override
    public List<User> findAllUsers() {
        try {
            List<UserEntity> usersEntity = userJpaRepository.findAll();
            Optional.ofNullable(usersEntity).orElseThrow(() -> new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND));
            return userMapper.fromEntityToDomainList(usersEntity);
        } catch (Exception e) {
            throw new ServiceException(ErrorCatalog.DB_ERROR, e.getMessage());
        }
    }

    @Override
    public User findUserById(Long id) {

        Optional.ofNullable(id).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));

        try {
            Optional<UserEntity> entity = userJpaRepository.findById(id);
            final String message = String.format("L'utilisateur %s est introuvable", id);
            entity.orElseThrow((() -> new UserNotFoundException(message)));
            return userMapper.fromEntityToDomain(entity.get());

        } catch (Exception e) {
            throw new ServiceException(ErrorCatalog.DB_ERROR, e.getMessage());
        }
    }

    @Override
    public User findByUserName(String userName) {

        Optional.ofNullable(userName).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        try {
            Optional<UserEntity> entity = userJpaRepository.findByUserName(userName);
            final String message = String.format("L'utilisateur %s est introuvable", userName);
            entity.orElseThrow((() -> new UserNotFoundException(message)));
            return userMapper.fromEntityToDomain(entity.get());
        } catch (Exception e) {
            log.error("error while find user", e);
            throw new ServiceException(ErrorCatalog.DB_ERROR, e.getMessage());
        }
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        Optional.ofNullable(userName).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        Optional.ofNullable(password).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        try {
            Optional<UserEntity> user = userJpaRepository.findByUserNameAndPassword(userName, password);
            user.orElseThrow(() -> new UserNotFoundException("L'identifiant et/ou le mot de passe est incorrect"));
            return userMapper.fromEntityToDomain(user.get());
        } catch (Exception e) {
            log.error("error while find user", e.getMessage());
            throw new ServiceException(ErrorCatalog.ACCESS_DENIED, e.getMessage());
        }
    }
}
