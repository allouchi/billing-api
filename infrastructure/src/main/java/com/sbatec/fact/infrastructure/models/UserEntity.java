package com.sbatec.fact.infrastructure.models;

import com.sbatec.fact.domaine.business.object.Role;
import com.sbatec.fact.infrastructure.models.common.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "T_User")
public class UserEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "email", unique = true, length = 50)
    String email;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "password", nullable = false)
    String password;
    @Column(name = "activated", length = 1)
    Boolean activated;
    @Column(name = "siret")
    private String siret;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id")
    private List<RoleEntity> roles;
}
