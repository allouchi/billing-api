package com.sbatec.fact.domaine.business.object;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Long id;
    String firstName;
    String lastName;
    String password;
    String email;
    Boolean activated;
    String siret;
    List<Role> roles;
}
