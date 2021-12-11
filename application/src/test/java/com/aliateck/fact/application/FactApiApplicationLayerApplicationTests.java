package com.aliateck.fact.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;

@SpringBootTest
class FactApiApplicationLayerApplicationTests {
  @Autowired
  UserApiService userApiService;

  @Autowired
  CompanyApiService companyApiService;

 
  
}
