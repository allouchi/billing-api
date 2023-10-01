package com.aliateck.fact.infrastructure.repository.company;

import com.aliateck.fact.infrastructure.models.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Long> {


    Optional<CompanyEntity> findBySocialReasonIgnoreCase(String reasonSocial);

    Optional<CompanyEntity> findBySiret(String siret);

    @Query(value = "SELECT * FROM T_User user INNER JOIN T_Company company ON user.company_id = company.id WHERE user.user_name= :userName", nativeQuery = true)
    Optional<List<CompanyEntity>> findByUserName(String userName);

}
