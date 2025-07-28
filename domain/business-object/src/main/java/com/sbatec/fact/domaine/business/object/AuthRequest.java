package com.sbatec.fact.domaine.business.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String username;
    private String password;
    private String rememberMe;
}
