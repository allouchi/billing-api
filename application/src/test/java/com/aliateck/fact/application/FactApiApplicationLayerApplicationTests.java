package com.aliateck.fact.application;

import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FactApiApplicationLayerApplicationTests {
  @Autowired
  UserApiService userApiService;

  @Autowired
  CompanyApiService companyApiService;

 
  
}
