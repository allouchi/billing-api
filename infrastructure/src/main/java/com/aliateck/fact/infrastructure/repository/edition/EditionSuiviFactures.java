package com.aliateck.fact.infrastructure.repository.edition;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.infrastructure.repository.edition.commun.HSSFiSelectionColors;


public class EditionSuiviFactures extends Excel{
    private static final Logger logger = Logger.getLogger(EditionSuiviFactures.class.getName());
    short ligneNb = 0;
    HSSFCellStyle columnHeaderStyle;
    HSSFCellStyle titleStyle;
    HSSFCellStyle normalStyle;
    HSSFCellStyle dateStyle;
    private File file;

    public EditionSuiviFactures(File file)  {
	super();
	this.file = file;
    }

    /**
     * 
     * @param numDossiers
     * @param totalTtc
     * @param dossiers
     * @param idContact
     * @throws Exception
     */
    public void build(Facture dto) throws Exception {

	//
	// creation du contenu
	//

	// le document
	HSSFWorkbook wb = new HSSFWorkbook();

	// create Styles
	createStyles(wb);

	// la feuille de travail
	HSSFSheet sheet1 = wb.createSheet("Lots");
	sheet1.setDefaultColumnWidth((short) 25);
	createHeaders(sheet1, "Lots", null, null);
	// titre
	HSSFRow row = getOrCreateRow(sheet1, ligneNb++);

	ligneNb = (short) (ligneNb + 1);

	row = getOrCreateRow(sheet1, ligneNb++);
	// create Headers
	createHeaders(sheet1, wb);
	ligneNb++;

	writeLots(dto, sheet1, wb);

	//
	// transfer du contenu dans le fichier temporaire
	//
	// creation d'un output stream
	FileOutputStream fileOut = null;

	try {
	    fileOut = new FileOutputStream(file);
	    wb.write(fileOut);
	} finally {
	    //
	    // fermeture du fichier temporaire
	    //
	    try {
		if (fileOut != null) {
		    fileOut.close();
		}
	    } catch (Exception e) {
		logger.warning("Error closing output stream: " + e);
	    }
	}
    }

    private void writeLots(Facture dto, HSSFSheet sheet1, HSSFWorkbook wb) throws Exception {
	HSSFRow row = null;
	short cellIdx = 0;
	cellIdx = 0;
	row = getOrCreateRow(sheet1, ligneNb++);

	createCell(wb, row, cellIdx++, applyBorder(dateStyle)).setCellValue(dto.getNumeroFacture());

	createCell(wb, row, cellIdx++, applyBorder(normalStyle)).setCellValue(dto.getPrixTotalHT());

	createCell(wb, row, cellIdx++, applyBorder(normalStyle)).setCellValue(dto.getPrixTotalTTC());

	createCell(wb, row, cellIdx++, applyBorder(normalStyle)).setCellValue(dto.getDateFacturation());

	createCell(wb, row, cellIdx++, applyBorder(normalStyle)).setCellValue(dto.getDelaiPaiement());

	createCell(wb, row, cellIdx++, applyBorder(normalStyle)).setCellValue(dto.getDateEcheance());

	createCell(wb, row, cellIdx++, applyBorder(normalStyle)).setCellValue(dto.getNbJourRetard());

	createCell(wb, row, cellIdx++, applyBorder(normalStyle)).setCellValue(dto.getFactureStatus());

	createCell(wb, row, cellIdx++, applyBorder(normalStyle)).setCellValue(dto.getDateEncaissement());

	createCell(wb, row, cellIdx++, applyBorder(normalStyle)).setCellValue(dto.getFraisRetard());	

    }

    @Override
    protected void createStyles(HSSFWorkbook wb) {
	super.createStyles(wb);
	// fonts
	HSSFFont tFont = getTitleFont(wb, "Arial", (short) 8, HSSFiSelectionColors.BLACK.getIndex());
	HSSFFont normalFont = getTitleFont(wb, "Arial", (short) 8, HSSFiSelectionColors.BLACK.getIndex());
	normalFont.setBold(false);

	// styles
	titleStyle = getTitleStyle(wb, HSSFiSelectionColors.WHITE.getIndex());
	titleStyle.setFont(tFont);
	titleStyle.setWrapText(true);

	columnHeaderStyle = getTitleStyle(wb, HSSFiSelectionColors.WHITE.getIndex());
	columnHeaderStyle.setFont(tFont);
	columnHeaderStyle.setWrapText(true);

	normalStyle = getTitleStyle(wb, HSSFiSelectionColors.WHITE.getIndex());
	normalStyle.setFont(normalFont);
	normalStyle.setWrapText(true);

	dateStyle = getTitleStyle(wb, HSSFiSelectionColors.WHITE.getIndex());
	dateStyle.setDataFormat(wb.createDataFormat().getFormat("dd/mm/yyyy hh:mm:ss"));
	dateStyle.setAlignment(HorizontalAlignment.CENTER);
	dateStyle.setVerticalAlignment(VerticalAlignment.CENTER);
	dateStyle.setFont(normalFont);

    }

    private HSSFCellStyle applyBorder(HSSFCellStyle style) {
	return super.applyBorder(style, BorderStyle.THIN, HSSFiSelectionColors.BLACK.getIndex());
    }

    private void createHeaders(HSSFSheet sheet1, HSSFWorkbook wb) {
	HSSFRow row = getOrCreateRow(sheet1, ligneNb++);
	short cellIdx = 0;
	createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle)).setCellValue(new HSSFRichTextString("Numéro de facture"));
	createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle)).setCellValue(new HSSFRichTextString("Montant HT"));
	createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle)).setCellValue(new HSSFRichTextString("Montant TTC"));
	createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle)).setCellValue(new HSSFRichTextString("Date de facture"));
	createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle)).setCellValue(new HSSFRichTextString("Délai (j)"));
	createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle)).setCellValue(new HSSFRichTextString("Date échéance"));
	createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle)).setCellValue(new HSSFRichTextString("Jours retard"));
	createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle)).setCellValue(new HSSFRichTextString("Facture réglée"));
	createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle)).setCellValue(new HSSFRichTextString("Date encaissement"));
	createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle)).setCellValue(new HSSFRichTextString("Frais de retard"));	

    }
}
