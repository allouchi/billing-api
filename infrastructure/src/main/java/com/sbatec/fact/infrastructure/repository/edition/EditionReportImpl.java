package com.sbatec.fact.infrastructure.repository.edition;

import com.lowagie.text.DocumentException;
import com.sbatec.fact.domaine.business.object.Adresse;
import com.sbatec.fact.domaine.business.object.Company;
import com.sbatec.fact.domaine.business.object.Facture;
import com.sbatec.fact.domaine.business.object.Prestation;
import com.sbatec.util.Utils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EditionReportImpl implements EditionReportService {

    private static final String TYPE_FILE = ".pdf";
    private static final String IBAN = "IBAN: ";
    private static final String BIC = "BIC: ";
    private static final String FACTURE_LIBELLE = "Facture";
    private static final String ESPACE_BLANC = " ";
    private static final String UNDERSCORE = "_";
    private static final String RETURN = "\n";

    @Override
    public Map<String, Object> buildParamJasper(Company company,
                                                Prestation prestation, Facture facture) {

        // infos company
        String rsCompany = company.getSocialReason();
        String statutCompany = company.getStatus();
        Adresse adresseCompany = company.getCompanyAdresse();
        String adresseCompleteCompany = adresseCompany.getNumero() + " " + adresseCompany.getRue()
                + "\n" + adresseCompany.getCodePostal() + " " + adresseCompany.getLocalite() + "\n"
                + adresseCompany.getPays();
        String numeroRcs = company.getRcsName();
        String numeroSiret = company.getSiret().trim();
        String numeroApe = company.getCodeApe();
        String numeroTva = company.getNumeroTva().trim();
        String numeroBic = BIC + company.getNumeroBic();
        String numeroIban = company.getNumeroIban();

        // IBAN: FR17 2004 1010 1254 0796 1J03 367
        // IBAN FR33 3000 2008 9700 0000 5896 J14

        String ibanAffichage = IBAN + numeroIban.substring(0, 4) + ESPACE_BLANC
                + numeroIban.substring(4, 8) + ESPACE_BLANC + numeroIban.substring(8, 12) + ESPACE_BLANC
                + numeroIban.substring(12, 16) + ESPACE_BLANC + numeroIban.substring(16, 20) + ESPACE_BLANC
                + numeroIban.substring(20, 24) + ESPACE_BLANC + numeroIban.substring(24, 27);

        String siretAffichage = null;
        String tvaAffichage = null;
        if (numeroSiret != null) {
            siretAffichage =
                    numeroSiret.substring(0, 3) + ESPACE_BLANC + numeroSiret.substring(3, 6) + ESPACE_BLANC
                            + numeroSiret.substring(6, 9) + ESPACE_BLANC + numeroSiret.substring(9, 13);

        }

        if (numeroTva != null) {
            tvaAffichage = numeroTva.trim().substring(0, 2) + ESPACE_BLANC
                    + numeroTva.trim().substring(2, 5) + ESPACE_BLANC + numeroTva.trim().substring(5, 8)
                    + ESPACE_BLANC + numeroTva.trim().substring(8, 11) + ESPACE_BLANC
                    + numeroTva.trim().substring(11, 13);
        }

        // infos factures
        String dateFacturation = facture.getDateFacturation();
        String numeroFacture = facture.getNumeroFacture();
        float montantHT = facture.getPrixTotalHT();
        float montantTTC = facture.getPrixTotalTTC();
        float montantTva = facture.getMontantTVA();
        float quantite = facture.getQuantite();
        String moisPrestation = facture.getMoisFacture();
        String communeDateEdition = adresseCompany.getLocalite() + ", le " + dateFacturation;

        // infos prestation
        float tarifHT = prestation.getTarifHT();
        String numeroCommande = prestation.getNumeroCommande();
        long delaiPaiement = prestation.getDelaiPaiement();
        String clientPrestation = prestation.getClientPrestation();
        String designation = prestation.getDesignation();
        String consultantFonction = prestation.getConsultant().getFonction();
        String consultantIdentite = prestation.getConsultant().getFirstName() + ESPACE_BLANC
                + prestation.getConsultant().getLastName().toUpperCase();
        String article;
        if (moisPrestation != null && (moisPrestation.startsWith("O") || moisPrestation.startsWith("A"))) {
            article = "Mois d'";
        } else {
            article = "Mois de ";
        }

        String designationLigne1 =
                "Prestation pour" + ESPACE_BLANC + clientPrestation.toUpperCase();
        String designationLigne2 = article + moisPrestation + ESPACE_BLANC + "par " + consultantIdentite;

        // infos client
        Adresse adresseClient = prestation.getClient().getAdresseClient();
        String adresseCompleteClient = adresseClient.getNumero() + ESPACE_BLANC + adresseClient.getRue()
                + RETURN + adresseClient.getCodePostal() + ESPACE_BLANC + adresseClient.getLocalite()
                + RETURN + adresseClient.getPays();
        String rsClient = prestation.getClient().getSocialReason();

        String moisFacture = Utils.buildMoisFacture(facture.getMoisFacture());
        String fileName =
                FACTURE_LIBELLE + UNDERSCORE + formatString(rsCompany) + UNDERSCORE + formatString(rsClient)
                        + UNDERSCORE + moisFacture + UNDERSCORE + numeroFacture.split("-")[1] + TYPE_FILE;

        // - Parametres envoyes au rapport
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("rs_company", rsCompany);
        parameters.put("statut_company", statutCompany);
        parameters.put("adresse_company", adresseCompleteCompany);
        parameters.put("numero_rcs", numeroRcs);
        parameters.put("numero_siret", siretAffichage);
        parameters.put("numero_tva", tvaAffichage);
        parameters.put("numero_ape", numeroApe);
        parameters.put("code_iban", ibanAffichage);
        parameters.put("code_bic", numeroBic);
        parameters.put("date_facturation", dateFacturation);
        parameters.put("rs_client", rsClient);
        parameters.put("adresse_client", adresseCompleteClient);
        parameters.put("mois_facture", moisPrestation);
        parameters.put("numero_commande", numeroCommande);
        parameters.put("quantite", quantite);
        parameters.put("montantHT", montantHT);
        parameters.put("montantTTC", montantTTC);
        parameters.put("montantTva", montantTva);
        parameters.put("tarifHT", tarifHT);
        parameters.put("numero_facture", numeroFacture);
        parameters.put("commune_company", communeDateEdition);
        parameters.put("designation", designation);
        parameters.put("delai_paiement", delaiPaiement);
        parameters.put("client_prestation", clientPrestation);
        parameters.put("designation_ligne1", designationLigne1);
        parameters.put("designation_ligne2", designationLigne2);
        parameters.put("fonction_consultant", consultantFonction);
        parameters.put("fileName", fileName);
        return parameters;
    }

    @Override
    public byte[] buildPdfFacture(Map<String, Object> paramJasper,
                                  String path, boolean storeFile) {
        byte[] file = null;
        try {
            File templateFile = null;
            Map<String, File> mapFiles = Utils.loadFilesResources();
            String outputFileName = (String) paramJasper.get("fileName");
            //templateFile = mapFiles.get("Custom");
            templateFile = mapFiles.get("Default");

            log.info("********************* Début du génération du fichier pdf *********************");
            JasperReport jasperDesign = JasperCompileManager.compileReport(templateFile.getPath());
            JRDataSource dataSource = new JREmptyDataSource();
            // - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, paramJasper, dataSource);
            // - Creation du rapport au format PDF
            if (storeFile) {
                JasperExportManager.exportReportToPdfFile(jasperPrint,
                        path + File.separator + outputFileName);
            }

            // JasperExportManager.exportReportToXmlFile(path+"\\", outputFileName, false);
            file = JasperExportManager.exportReportToPdf(jasperPrint);
            log.info("********************* Fin génération du fichier pdf *********************");

        } catch (Exception e) {
            log.info("Problème lors du chargement des fichiers templates");
            //throw e;
        }
        return file;

    }

    /**
     * @param factures
     * @param path
     */
    @Override
    public void buildSuiviFactures(List<Facture> factures, String path) {
        InputStream targetStream = null;
        EditionSuiviFactures editionFacture = new EditionSuiviFactures();
        try {
            Map<String, File> resources = Utils.loadFilesResources();
            File templateSuivi = resources.get("Suivi");
            targetStream = new FileInputStream(templateSuivi);
            editionFacture.build(factures, targetStream, path);

        } catch (Exception e) {
            log.info("Problème lors de la création du fichier Excel : " + e.getMessage());
        } finally {
            if (targetStream != null) {
                try {
                    targetStream.close();
                } catch (IOException e) {
                    log.info("Problème lors de la fermeture du fichier Excel : " + e.getMessage());
                }
            }
        }

    }

    /**
     * @param company
     * @param prestation
     * @param facture
     * @return
     */
    @Override
    public Map<String, Object> buildParamsNewTemplate(Company company, Prestation prestation, Facture facture) {
        // infos company
        String rsCompany = company.getSocialReason();
        String companyStatus = company.getStatus();
        Adresse adresseCompany = company.getCompanyAdresse();
        String adresse1Company = adresseCompany.getNumero() + ESPACE_BLANC + adresseCompany.getRue();
        String adresse2Company = adresseCompany.getCodePostal() + ESPACE_BLANC + adresseCompany.getLocalite() + ESPACE_BLANC
                + adresseCompany.getPays();
        String numeroRcs = company.getRcsName();
        String numeroSiret = company.getSiret().trim();
        String numeroApe = company.getCodeApe();
        String numeroTva = company.getNumeroTva().trim();
        String numeroBic = BIC + company.getNumeroBic();
        String numeroIban = company.getNumeroIban();

        String ibanAffichage = IBAN + numeroIban.substring(0, 4) + ESPACE_BLANC
                + numeroIban.substring(4, 8) + ESPACE_BLANC + numeroIban.substring(8, 12) + ESPACE_BLANC
                + numeroIban.substring(12, 16) + ESPACE_BLANC + numeroIban.substring(16, 20) + ESPACE_BLANC
                + numeroIban.substring(20, 24) + ESPACE_BLANC + numeroIban.substring(24, 27);

        String siretAffichage = null;
        String tvaAffichage = null;
        if (numeroSiret != null) {
            siretAffichage =
                    numeroSiret.substring(0, 3) + ESPACE_BLANC + numeroSiret.substring(3, 6) + ESPACE_BLANC
                            + numeroSiret.substring(6, 9) + ESPACE_BLANC + numeroSiret.substring(9, 14);

        }

        if (numeroTva != null) {
            tvaAffichage = numeroTva.trim().substring(0, 2) + ESPACE_BLANC
                    + numeroTva.trim().substring(2, 5) + ESPACE_BLANC + numeroTva.trim().substring(5, 8)
                    + ESPACE_BLANC + numeroTva.trim().substring(8, 11) + ESPACE_BLANC
                    + numeroTva.trim().substring(11, 13);
        }

        // infos factures
        String dateFacturation = facture.getDateFacturation();
        String numeroFacture = facture.getNumeroFacture();
        float montantHT = facture.getPrixTotalHT();
        float montantTTC = facture.getPrixTotalTTC();
        float montantTva = facture.getMontantTVA();
        float quantite = facture.getQuantite();
        String moisPrestation = facture.getMoisFacture();
        String communeDateEdition = adresseCompany.getLocalite() + ", le " + dateFacturation;

        // infos prestation
        float tarifHT = prestation.getTarifHT();
        String numeroCommande = prestation.getNumeroCommande();
        long delaiPaiement = prestation.getDelaiPaiement();
        String clientPrestation = prestation.getClientPrestation();
        String designation = prestation.getDesignation();
        String consultantFonction = prestation.getConsultant().getFonction();
        String consultantIdentite = prestation.getConsultant().getFirstName() + ESPACE_BLANC
                + prestation.getConsultant().getLastName().toUpperCase();
        String article;
        if (moisPrestation != null && (moisPrestation.startsWith("O") || moisPrestation.startsWith("A"))) {
            article = "Mois d'";
        } else {
            article = "Mois de ";
        }

        String designationLigne1 =
                "Prestation pour" + ESPACE_BLANC + clientPrestation.toUpperCase();
        String designationLigne2 = article + moisPrestation + ESPACE_BLANC + "par" + ESPACE_BLANC + consultantIdentite;

        // infos client
        Adresse adresseClient = prestation.getClient().getAdresseClient();
        String adresse1Client = adresseClient.getNumero() + ESPACE_BLANC + adresseClient.getRue();
        String adresse2Client = adresseClient.getCodePostal() + ESPACE_BLANC + adresseClient.getLocalite()
                + ESPACE_BLANC + adresseClient.getPays();

        String rsClient = prestation.getClient().getSocialReason();

        String moisFacture = Utils.buildMoisFacture(facture.getMoisFacture());
        String fileName =
                FACTURE_LIBELLE + UNDERSCORE + formatString(rsCompany) + UNDERSCORE + formatString(rsClient)
                        + UNDERSCORE + moisFacture + UNDERSCORE + numeroFacture.split("-")[1] + TYPE_FILE;

        // - Parametres envoyes au rapport
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("rs_company", rsCompany);
        parameters.put("statut_company", companyStatus);
        parameters.put("adresse1_company", adresse1Company);
        parameters.put("adresse2_company", adresse2Company);
        parameters.put("numero_rcs", numeroRcs);
        parameters.put("numero_siret", siretAffichage);
        parameters.put("numero_tva", tvaAffichage);
        parameters.put("numero_ape", numeroApe);
        parameters.put("code_iban", ibanAffichage);
        parameters.put("code_bic", numeroBic);
        parameters.put("date_facturation", dateFacturation);
        parameters.put("rs_client", rsClient);
        parameters.put("adresse1_client", adresse1Client);
        parameters.put("adresse2_client", adresse2Client);
        parameters.put("mois_facture", moisPrestation);
        parameters.put("numero_commande", numeroCommande);
        parameters.put("quantite", String.format("%.2f", quantite));
        parameters.put("montantHT", String.format("%.2f", montantHT));
        parameters.put("montantTTC", String.format("%.2f", montantTTC));
        parameters.put("montantTva", String.format("%.2f", montantTva));
        parameters.put("tarifHT", String.format("%.2f", tarifHT));
        parameters.put("numero_facture", numeroFacture);
        parameters.put("commune_company", communeDateEdition);
        parameters.put("designation", designation);
        parameters.put("delai_paiement", delaiPaiement);
        parameters.put("client_prestation", clientPrestation);
        parameters.put("designation_ligne1", designationLigne1);
        parameters.put("designation_ligne2", designationLigne2);
        parameters.put("fonction_consultant", consultantFonction);
        parameters.put("fileName", fileName);
        return parameters;
    }

    @Override
    public byte[] buildFacturePdFSaucer(Map<String, Object> parameters,
                                        String pathParam, boolean storeFile) throws IOException, DocumentException {
        Map<String, File> mapFiles = Utils.loadFilesResources();
        File htmlTemplate = mapFiles.get("Html");

        String rsCompany = (String) parameters.get("rs_company");
        String adresse1Company = (String) parameters.get("adresse1_company");
        String adresse2Company = (String) parameters.get("adresse2_company");
        String status = (String) parameters.get("statut_company");
        String numeroRcs = (String) parameters.get("numero_rcs");
        String siretAffichage = (String) parameters.get("numero_siret");
        String tvaAffichage = (String) parameters.get("numero_tva");
        String numeroApe = (String) parameters.get("numero_ape");
        String ibanAffichage = (String) parameters.get("code_iban");
        String numeroBic = (String) parameters.get("code_bic");
        String dateFacturation = (String) parameters.get("date_facturation");
        String rsClient = (String) parameters.get("rs_client");
        String adresse1Client = (String) parameters.get("adresse1_client");
        String adresse2Client = (String) parameters.get("adresse2_client");
        String moisFacture = (String) parameters.get("mois_facture");
        String numeroCommande = (String) parameters.get("numero_commande");
        String quantite = (String) parameters.get("quantite");
        String montantHT = (String) parameters.get("montantHT");
        String montantTTC = (String) parameters.get("montantTTC");
        String montantTva = (String) parameters.get("montantTva");
        String tarifHT = (String) parameters.get("tarifHT");
        String numeroFacture = (String) parameters.get("numero_facture");
        String communeCompany = (String) parameters.get("commune_company");
        String designation = (String) parameters.get("designation");
        long delaiPaiement = (Long) parameters.get("delai_paiement");
        String clientPrestation = (String) parameters.get("client_prestation");
        String designationLigne1 = (String) parameters.get("designation_ligne1");
        String designationLigne2 = (String) parameters.get("designation_ligne2");
        String consultantFonction = (String) parameters.get("fonction_consultant");

        log.info("********************* Début du génération du fichier pdf *********************");

        String template = new String(Files.readAllBytes(Paths.get(htmlTemplate.getPath())));
        LocalDate dateActuelle = LocalDate.now();
        int strDateJour = dateActuelle.getYear();

        String html = template
                .replace("${rsCompany}", rsCompany)
                .replace("${statutCompany}", status)
                .replace("${adresse1Company}", adresse1Company)
                .replace("${adresse2Company}", adresse2Company)
                .replace("${numeroRcs}", numeroRcs)
                .replace("${numeroSiret}", siretAffichage)
                .replace("${numeroTva}", tvaAffichage)
                .replace("${numeroApe}", numeroApe)
                .replace("${codeIban}", ibanAffichage)
                .replace("${codeBic}", numeroBic)
                .replace("${dateFacturation}", dateFacturation)
                .replace("${rsClient}", rsClient.toUpperCase())
                .replace("${moisFacture}", moisFacture)
                .replace("${numeroCommande}", numeroCommande)
                .replace("${quantite}", String.valueOf(quantite))
                .replace("${montantHT}", String.valueOf(montantHT))
                .replace("${montantTTC}", String.valueOf(montantTTC))
                .replace("${montantTva}", String.valueOf(montantTva))
                .replace("${tarifHT}", String.valueOf(tarifHT))
                .replace("${numeroFacture}", numeroFacture)
                .replace("${communeCompany}", communeCompany)
                .replace("${designation}", designation)
                .replace("${delaiPaiement}", String.valueOf(delaiPaiement))
                .replace("${clientPrestation}", clientPrestation)
                .replace("${designationLigne1}", designationLigne1)
                .replace("${designationLigne2}", designationLigne2)
                .replace("${fonctionConsultant}", consultantFonction)
                .replace("${exercice}", String.valueOf(strDateJour))
                .replace("${adresse1Client}", adresse1Client)
                .replace("${adresse2Client}", adresse2Client);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(out);

        String outputFileName = (String) parameters.get("fileName");

        String outputPath = pathParam + File.separator + outputFileName;

        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            out.writeTo(fos);
            fos.flush();
            log.info("✅ Fichier PDF écrit sur le disque : {}", outputPath);
        } catch (IOException e) {
            log.error("❌ Erreur lors de l'écriture du fichier PDF : {}", e.getMessage());
        }

        log.info("********************* Fin génération du fichier pdf *********************");
        return out.toByteArray();
    }


    private String formatString(String s) {
        String format = s.split(" ")[0];
        String start = format.substring(0, 1);
        format = start.toUpperCase() + format.substring(1).toLowerCase();
        return format;

    }

}
