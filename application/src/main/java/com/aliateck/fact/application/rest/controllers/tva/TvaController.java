package com.aliateck.fact.application.rest.controllers.tva;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.domaine.business.object.Tva;
import com.aliateck.fact.domaine.ports.api.tva.TvaApiService;
import com.aliateck.util.CommonResource.Resource;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(Resource.TVAS)
public class TvaController implements CommandLineRunner{
  
  private TvaApiService tvaApiService;
  

  @GetMapping(value = "/{exercice}")
  public Tva getTva( @PathVariable  String exercice) {    
    return tvaApiService.findByExercice(exercice);
  }
  
  @PutMapping(consumes = "application/json", produces = "application/json")
  public void updateTva( @RequestBody Tva tvaRequest ) {    
    tvaApiService.updateTva(tvaRequest);
  }
  
  @GetMapping(value = "/{id}")
  public void deleteTva( @PathVariable Long id ) {    
    tvaApiService.deleteTva(id);
  }
  
  @PostMapping(value = "/{id}")
  public void addTva( @PathVariable Tva tvaRequest ) {    
    tvaApiService.addTva(tvaRequest);
  }

@Override
public void run(String... args) throws Exception {
	Tva tva = Tva.builder()
			.datePayment("21/11/2021")
			.montantPayment(1000f)
			.exercice("2021-2022")
			.build();
	tvaApiService.addTva(tva)	;  	
	
	}
  
}
