package com.sbatec.fact.infrastructure.models;

import com.sbatec.fact.infrastructure.models.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity(name = "T_Operation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class OperationEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "type_operation", nullable = false, unique = false)
    String typeOperation;

    @Column(name = "montant_operation", nullable = false)
    Float montantoperation;

    @Column(name = "exercise", nullable = false)
    String exercise;

    @Column(name = "date_operation", nullable = false)
    String dateOperation;


}
