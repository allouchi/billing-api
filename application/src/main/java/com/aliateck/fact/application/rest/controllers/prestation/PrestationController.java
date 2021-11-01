package com.aliateck.fact.application.rest.controllers.prestation;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aliateck.fact.application.rest.util.StorageProperties;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.aliateck.fact.domaine.ports.api.facture.FactureApiService;
import com.aliateck.fact.domaine.ports.api.prestation.PrestationApiService;
import com.aliateck.util.CommonResource.Resource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Resource.PRESTATIONS)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrestationController {

  private PrestationApiService prestationApiService;
  FactureApiService factureApiService;
  @Autowired
  StorageProperties resources;

  @GetMapping(value = "/{siret}")
  public ResponseEntity<List<Prestation>> getAllPrestations(@PathVariable String siret) {
    log.info("get all prestations");
    List<Prestation> listPrestats = prestationApiService.findAll(siret);
    listPrestats =
        listPrestats.stream().sorted(Comparator.comparingLong(Prestation::getId).reversed())
            .collect(Collectors.toList());
    return ResponseEntity.ok(listPrestats);
  }

  @PostMapping(value = "/{siret}/{templateChoice}/{moisPrestaId}")
  public ResponseEntity<Prestation> addPrestation(@RequestBody Prestation prestation,
      @PathVariable String siret, @PathVariable boolean templateChoice,
      @PathVariable Long moisPrestaId) {
    log.info("Create new Prestation");
    Prestation presta =
        prestationApiService.addPrestation(prestation, templateChoice, siret, moisPrestaId);
    return ResponseEntity.ok(presta);
  }

  
  @PutMapping(value = "/{siret}")
  public ResponseEntity<Prestation> updatePrestation(@RequestBody Prestation prestation,
      @PathVariable String siret

  ) {
    log.info("Create new Prestation");
    Prestation presta = prestationApiService.updatePrestation(prestation, siret);
    return ResponseEntity.ok(presta);
  }

  @PutMapping(value = "/{siret}/{templateChoice}/{moisPrestaId}")
  public ResponseEntity<Prestation> createFacture(@RequestBody Prestation prestationRequest,
      @PathVariable String siret, @PathVariable boolean templateChoice,
      @PathVariable Long moisPrestaId) {
    log.info("Update prestation");
    Map<String, String> params = new HashMap<String, String>();
    params.put("storage-pathRoot-pdf",  resources.getPathRoot());
    params.put("storage-pathRoot-libelle-facture", resources.getPathLibelleFacture());
    params.put("storage-pathRoot-libelle-charges", resources.getPathLibelleCharges());
    params.put("storage-pathRoot-libelle-releve", resources.getPathLibelleReleve());
    params.put("fichier_suivi_name", resources.getFichierSuiviFactures());   
       
    Prestation presta = factureApiService.addFacture(siret, templateChoice, prestationRequest,
        resources.getPathRoot(), moisPrestaId, resources.saveFileLocalDisque(),
        resources.getFichierSuiviFactures());
    return ResponseEntity.ok(presta);
  }

  @DeleteMapping(value = "/{id}")
  public void deletePrestation(@PathVariable long id) {
    log.info("delete prestation by id :" + id);
    prestationApiService.deletePrestation(id);
  }
}
