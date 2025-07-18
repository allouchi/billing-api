package com.sbatec.fact.infrastructure.models;

import com.sbatec.fact.infrastructure.models.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "T_Operation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class OperationEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "type_operation", nullable = false, unique = false)
    String typeOperation;

    @Column(name = "montant_operation", nullable = false)
    Float montantoperation;

    @Column(name = "exercice", nullable = false)
    String exercice;

    @Column(name = "date_operation", nullable = false)
    String dateOperation;


}
