package com.sbatec.fact.domaine.adapter.user;

import com.sbatec.fact.domaine.business.object.User;
import com.sbatec.fact.domaine.ports.api.user.UserApiDetailService;
import com.sbatec.fact.domaine.ports.spi.user.UserSpiDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserApiDetailAdapter implements UserApiDetailService {

    // For jenkins
    UserSpiDetailService userSpiDetailsService;

    @Override
    public User findByUserName(String name) {
        return userSpiDetailsService.findByUserName(name);
    }
}
