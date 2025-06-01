package com.sbatec.fact.infrastructure.models;

import com.sbatec.fact.infrastructure.models.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    String role;

    @Column(name = "description", nullable = false)
    String description;

    @ManyToMany( fetch = FetchType.EAGER, mappedBy = "roles")
    List<UserEntity> users;

}
