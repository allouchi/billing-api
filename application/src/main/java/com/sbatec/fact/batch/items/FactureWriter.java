package com.sbatec.fact.batch.items;

//@Slf4j
/*
public class FactureWriter implements ItemWriter<Facture> {

    private final BatchApiService batchApiService;

    public FactureWriter(BatchApiService batchApiService) {
        this.batchApiService = batchApiService;
    }


    public void write(List<? extends Facture> factures) throws Exception {

        factures.stream().forEach(facture -> {
            log.info("Enregistrement en base de l'objet {} ", facture.getMoisFacture());
            batchApiService.updateFactures(facture);
        });

    }


} */
