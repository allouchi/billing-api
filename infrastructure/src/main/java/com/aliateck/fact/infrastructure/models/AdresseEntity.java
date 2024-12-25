package com.aliateck.fact.infrastructure.models;


import com.aliateck.fact.infrastructure.models.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity(name = "T_Adresse")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
// @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdresseEntity extends CommonEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "numero", nullable = false)
    String numero;

    @Column(name = "rue", nullable = false)
    String rue;

    @Column(name = "code_postal", nullable = false)
    String codePostal;

    @Column(name = "localite", nullable = false)
    String localite;

    @Column(name = "pays", nullable = false)
    String pays;


}
