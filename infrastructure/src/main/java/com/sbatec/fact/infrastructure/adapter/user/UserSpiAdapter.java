package com.sbatec.fact.infrastructure.adapter.user;

import com.sbatec.fact.domaine.business.object.Role;
import com.sbatec.fact.domaine.business.object.User;
import com.sbatec.fact.domaine.exception.ErrorCatalog;
import com.sbatec.fact.domaine.exception.ServiceException;
import com.sbatec.fact.domaine.exception.UserNotFoundException;
import com.sbatec.fact.domaine.ports.spi.user.UserSpiService;
import com.sbatec.fact.infrastructure.adapter.commun.CheckEmailAdresse;
import com.sbatec.fact.infrastructure.mapper.CompanyMapper;
import com.sbatec.fact.infrastructure.mapper.RoleMapper;
import com.sbatec.fact.infrastructure.mapper.UserMapper;
import com.sbatec.fact.infrastructure.models.RoleEntity;
import com.sbatec.fact.infrastructure.models.UserEntity;
import com.sbatec.fact.infrastructure.repository.user.RoleJpaRepository;
import com.sbatec.fact.infrastructure.repository.user.UserJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSpiAdapter implements UserSpiService {
    UserJpaRepository userJpaRepository;
    RoleJpaRepository roleJpaRepository;
    UserMapper userMapper;
    CompanyMapper companyMapper;
    RoleMapper roleMapper;


    @Override
    @Transactional
    public User addUser(User user) {

        Optional.ofNullable(user).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        CheckEmailAdresse checkEmail = CheckEmailAdresse.builder().build();
        if (user.getId() == null && checkEmail.checkEmailAdresse(user, userJpaRepository)) {
            final String format = String.format("L'adresse mail %s est déjà utilisée", user.getEmail());
            throw new ServiceException(ErrorCatalog.DUPLICATE_DATA, format);
        }

        try {
            UserEntity userEntity = userMapper.fromDomainToEntity(user);
            List<RoleEntity> rolesToAdd = new ArrayList<>();

            for (RoleEntity role : userEntity.getRoleNames()) {
                Optional<RoleEntity> roleInDb = roleJpaRepository.findById(role.getId());
                if (roleInDb.isPresent()) {
                    rolesToAdd.add(roleInDb.get());
                } else {
                    throw new IllegalArgumentException("Role non trouvé avec id = " + role.getId());
                }
            }

            userEntity.setRoleNames(rolesToAdd);
            UserEntity entity = userJpaRepository.save(userEntity);
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
            entity.orElseThrow((() -> new UserNotFoundException(ErrorCatalog.RESOURCE_NOT_FOUND, message)));
            return userMapper.fromEntityToDomain(entity.get());

        } catch (Exception e) {
            throw new ServiceException(ErrorCatalog.DB_ERROR, e.getMessage());
        }
    }

    @Override
    public User findByUserName(String userName) {

        Optional.ofNullable(userName).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        Optional<UserEntity> entity = userJpaRepository.findByEmail(userName);
        final String message = String.format("L'utilisateur %s est introuvable", userName);
        entity.orElseThrow((() -> new UserNotFoundException(ErrorCatalog.RESOURCE_NOT_FOUND, message)));
        return userMapper.fromEntityToDomain(entity.get());

    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        Optional.ofNullable(userName).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));
        Optional.ofNullable(password).orElseThrow(() -> new ServiceException(ErrorCatalog.BAD_DATA_ARGUMENT));

        Optional<UserEntity> user = userJpaRepository.findByEmailAndPassword(userName, password);
        user.orElseThrow(() -> new UserNotFoundException("L'identifiant et/ou le mot de passe est incorrect"));
        return userMapper.fromEntityToDomain(user.get());
    }

}
