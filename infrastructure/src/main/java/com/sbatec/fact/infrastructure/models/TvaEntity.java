package com.sbatec.fact.infrastructure.models;

import com.sbatec.fact.infrastructure.models.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity(name = "T_Tva")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class TvaEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "date_payment", nullable = false, unique = false)
    String datePayment;

    @Column(name = "montant_payment", precision = 10, scale = 2, nullable = false)
    BigDecimal montantPayment;

    @Column(name = "exercise", nullable = false)
    String exercise;

    @Column(name = "siret", nullable = false)
    String siret;

    @Column(name = "month_payment", nullable = false)
    String monthPayment;

}
