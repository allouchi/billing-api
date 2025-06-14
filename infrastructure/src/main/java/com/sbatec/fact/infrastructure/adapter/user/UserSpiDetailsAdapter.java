package com.sbatec.fact.infrastructure.adapter.user;

import com.sbatec.fact.domaine.business.object.User;
import com.sbatec.fact.domaine.exception.ErrorCatalog;
import com.sbatec.fact.domaine.exception.ServiceException;
import com.sbatec.fact.domaine.ports.spi.user.UserSpiDetailService;
import com.sbatec.fact.infrastructure.mapper.UserMapper;
import com.sbatec.fact.infrastructure.models.UserEntity;
import com.sbatec.fact.infrastructure.repository.user.UserJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@Transactional
@RequiredArgsConstructor
@ToString
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSpiDetailsAdapter implements UserSpiDetailService {

    private UserMapper userMapper;
    private UserJpaRepository userJpaRepository;

    @Override
    public User findByUserName(String userName) {
        Optional<UserEntity> entity = userJpaRepository.findByEmail(userName);

        if (entity.isPresent()) {
            return userMapper.fromEntityToDomain(entity.get());
        } else {
            final String format = String.format("User with userName %s not found", userName);
            log.debug(format);
            throw new ServiceException(ErrorCatalog.RESOURCE_NOT_FOUND, format);
        }

    }

}
