package com.aliateck.fact.infrastructure.models;

import com.aliateck.fact.infrastructure.models.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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

    @Column(name = "montant-payment", nullable = false)
    Float montantPayment;

    @Column(name = "exercise", nullable = false)
    String exercise;

    @Column(name = "siret", nullable = false)
    String siret;

    @Column(name = "month_payment", nullable = true)
    String monthPayment;

}
