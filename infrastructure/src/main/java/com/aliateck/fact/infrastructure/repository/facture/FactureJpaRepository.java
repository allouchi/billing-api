package com.aliateck.fact.infrastructure.repository.facture;

import com.aliateck.fact.infrastructure.models.FactureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureJpaRepository extends JpaRepository<FactureEntity, Long> {

    FactureEntity getByNumeroFacture(String numeroFacture);

    //List<FactureEntity> findByFactureStatus(boolean status);

    //List<FactureEntity> findByDateEcheance(Date dateEcheance);

    //List<FactureEntity> findByDateEncaissement(Date dateEncaissement);

    //List<FactureEntity> findByDateFacturation(Date dateFacturation);

    //@Query("select f from FactureEntity f where f.dateFacturation= :excerice")
    List<FactureEntity> findByExercice(String excerice);

    //@Query(value = "SELECT * FROM T_User user INNER JOIN T_Company company ON user.company_id = company.id WHERE user.user_name= :userName", nativeQuery = true)


    //@Query(value = "SELECT * FROM T_Facture f WHERE f.date_encaissement IS NULL", nativeQuery = true)
    List<FactureEntity> findByDateEncaissementIsNull();

}
