package com.sbatec.fact.domaine.business.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private User user;
    private String socialReason;
    private Company company;
}
