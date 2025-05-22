package com.aliateck.fact.domaine.business.object;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
    String role;
    String siret;
}
