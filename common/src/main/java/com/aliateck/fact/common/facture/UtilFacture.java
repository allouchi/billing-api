package com.aliateck.fact.common.facture;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.aliateck.fact.domaine.business.object.Client;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.business.object.Prestation;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class UtilFacture {
	   private static String FILE = "c:/temp/facture.pdf";
	    private static Font companyRsFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	            Font.BOLD);
	    private static Font companyInfoBoldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
	            Font.BOLD);
	    private static Font companyInfoFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
	            Font.NORMAL);
	    
	    private static Font factureTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20,
	            Font.BOLD);
	    private static Font factureNumeroFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
	            Font.BOLD);
	    
	    private static Font adresseClientFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL);
	    
	    private static Font rsClientFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	            Font.BOLD);
	    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL, BaseColor.RED);
	    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	            Font.BOLD);
	    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.BOLD);
		
  private UtilFacture() {}

  public static Facture calculerFacture(Prestation prestation) {
    float tarifHT = prestation.getTarifHT();

    Facture facture =  prestation.getFacture();
    float prixTotalHT = tarifHT * facture.getNbJoursEffectue();
    float tva = prixTotalHT * 0.2f;
    facture.setPrixTotalHT(prixTotalHT);
    facture.setPrixTotalTTC(prixTotalHT + tva);
    facture.setTva(tva);   
    facture.setDateFacturation(convertToDate(LocalDate.now()));
    facture.setDateEcheance(calculerDateEcheance(prestation));

    return facture;
  }

  private static String calculerDateEcheance(Prestation prestation) {
    int delai = prestation.getDelaiPaiement();
    LocalDate dateEcheance = LocalDate.now().plusDays(delai);
    return convertToDate(dateEcheance);
  }

  public static String convertToDate(LocalDate dateToConvert) {
    final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return formaterDate.format(dateToConvert);
  }
  
  public static boolean editerFacture(Company company, Prestation prestation) { 
		
		
	  try {
		  Document document = new Document();
		  PdfWriter.getInstance(document, new FileOutputStream(FILE));
		  document.open();
		  addMetaData(document);
		  addCompanyInfo(company, document);
		  addFactureContent(document, prestation);
		  document.close();
		 
	} catch (Exception e) {
	    e.printStackTrace();
	}   

	  return true;
	  
  } 
  

  private static void addMetaData(Document document) {
      document.addTitle("Facturation");
      document.addAuthor("SBATEC");
      document.addCreator("SBATEC");
  }
  
  
  private static void addCompanyInfo(Company company, Document document)
          throws DocumentException {
      Paragraph companyInfo = new Paragraph();        
      // paragraphe company infos
      companyInfo.add(new Paragraph(company.getSocialReason(), companyRsFont));
      companyInfo.add(new Paragraph(company.getStatus(), companyInfoFont));
      companyInfo.add(new Paragraph(company.getCompanyAdresse().getNumero() + " " + company.getCompanyAdresse().getVoie() , companyInfoFont));
      companyInfo.add(new Paragraph(company.getCompanyAdresse().getCodePostal() + " " + company.getCompanyAdresse().getCommune() , companyInfoFont));
      companyInfo.add(new Paragraph(company.getRcsName(), companyInfoFont));
      companyInfo.add(new Paragraph("SIRET : " + company.getSiret() + " - APE : " + company.getApe(), companyInfoBoldFont));
      companyInfo.add(new Paragraph("TVA : " + company.getTvaName(), companyInfoBoldFont));

      addEmptyLine(companyInfo, 2);              
      
      // date d'édition
      companyInfo.add(new Paragraph(
      		company.getCompanyAdresse().getCommune() + ", le " + UtilFacture.convertToDate(LocalDate.now()),
              companyInfoFont));
      document.add(companyInfo);
     
  }
  
  private static void addFactureContent(Document document,  Prestation prestation) throws DocumentException {
      
	Facture facture =   prestation.getFacture();
  	Client client = prestation.getClient();  	
  	Paragraph factureInfo = new Paragraph();
  	factureInfo.setAlignment(Element.ALIGN_LEFT);
  	factureInfo.add(new Paragraph("Facture", factureTitleFont));
  	factureInfo.add(new Paragraph("N° " + facture.getNumeroFacture(), factureNumeroFont));
  	
  	Paragraph clientInfo = new Paragraph();
  	clientInfo.setAlignment(Element.ALIGN_RIGHT);
  	clientInfo.add(new Paragraph(client.getSocialReason(), rsClientFont));
  	
  	document.add(factureInfo);
  	String adresseClient =  client.getAdresse().getNumero()
  			+ " " +  client.getAdresse().getVoie()
  			+ " " + client.getAdresse().getCodePostal()
  			+ " " +  client.getAdresse().getCommune() 
  			+  " " + client.getAdresse().getPays();
  			
  	clientInfo.add(new Paragraph(adresseClient, adresseClientFont));
  	document.add(clientInfo);  
       
  	
  }
  private static void createTable(Section subCatPart)
          throws BadElementException {
      PdfPTable table = new PdfPTable(3);

      // t.setBorderColor(BaseColor.GRAY);
      // t.setPadding(4);
      // t.setSpacing(4);
      // t.setBorderWidth(1);

      PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(c1);

      c1 = new PdfPCell(new Phrase("Table Header 2"));
      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(c1);

      c1 = new PdfPCell(new Phrase("Table Header 3"));
      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(c1);
      table.setHeaderRows(1);

      table.addCell("1.0");
      table.addCell("1.1");
      table.addCell("1.2");
      table.addCell("2.1");
      table.addCell("2.2");
      table.addCell("2.3");

      subCatPart.add(table);

  }

 
  
  private static void createList(Section subCatPart) {
      List list = new List(true, false, 10);
      list.add(new ListItem("First point"));
      list.add(new ListItem("Second point"));
      list.add(new ListItem("Third point"));
      subCatPart.add(list);
  }

  private static void addEmptyLine(Paragraph paragraph, int number) {
      for (int i = 0; i < number; i++) {
          paragraph.add(new Paragraph(" "));
      }
  }
}
