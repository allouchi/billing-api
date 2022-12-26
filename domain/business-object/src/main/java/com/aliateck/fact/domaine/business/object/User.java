package com.aliateck.fact.domaine.business.object;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

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
    String userName;
    String firstName;
    String lastName;
    String password;
    Boolean activated;
    List<Role> roles;
    Company company;

}
