package com.sbatec.fact.infrastructure.models;

import com.sbatec.fact.infrastructure.models.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "T_Role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "description", nullable = false)
    private String description;

}
