package com.aliateck.fact.infrastructure.adapter.commun;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Consultant;
import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.infrastructure.repository.client.ClientJpaRepository;
import com.aliateck.fact.infrastructure.repository.company.CompanyJpaRepository;
import com.aliateck.fact.infrastructure.repository.consultant.ConsultantJpaRepository;
import com.aliateck.fact.infrastructure.repository.user.UserJpaRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckEmailAdresse implements ICheckEmailAdresse {

    @Override
    public boolean checkEmailAdresse(Object object, Object jpaRepository) {
        if (object instanceof Consultant consult) {
            ConsultantJpaRepository consultantJpaRepository = (ConsultantJpaRepository) jpaRepository;
            return consultantJpaRepository
                    .findByEmail(consult.getEmail()).isPresent();

        } else if (object instanceof Client client) {
            ClientJpaRepository clientJpaRepository = (ClientJpaRepository) jpaRepository;
            return clientJpaRepository
                    .findByEmail(client.getEmail()).isPresent();

        } else if (object instanceof Company company) {
            CompanyJpaRepository companyJpaRepository = (CompanyJpaRepository) jpaRepository;
            return companyJpaRepository.findBySiret(company.getSiret()).isPresent();

        } else if (object instanceof User user) {
            UserJpaRepository userJpaRepository = (UserJpaRepository) jpaRepository;
            return userJpaRepository.findByUserName(user.getUserName()).isPresent();
        }
        return false;
    }
}
