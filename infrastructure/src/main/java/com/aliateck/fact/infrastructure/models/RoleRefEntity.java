package com.aliateck.fact.infrastructure.models;

import com.aliateck.fact.infrastructure.models.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "T_RoleRef")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleRefEntity extends CommonEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    @Column(name = "email", nullable = false)
    String email;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "description", nullable = false)
    private String description;

}
