package com.aliateck.fact.infrastructure.repository.edition;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import com.aliateck.fact.domaine.business.object.Facture;


public class EditionSuiviFactures {
  private static final Logger logger = Logger.getLogger(EditionSuiviFactures.class.getName());
  short ligneNb = 0;
  HSSFCellStyle columnHeaderStyle;
  HSSFCellStyle titleStyle;
  HSSFCellStyle normalStyle;
  HSSFCellStyle dateStyle;

  public EditionSuiviFactures() {

  }

  /**
   * Création du fichier
   *
   * @param liste de programmes
   * @param le fichier template
   * @param répertoire du fichier
   * @throws Exception
   */
  public void build(List<Facture> factures, InputStream template, String path) throws Exception {
    POIFSFileSystem fs = null;
    HSSFWorkbook wb = null;

    try {

      fs = new POIFSFileSystem(template);
      wb = new HSSFWorkbook(fs);
      HSSFSheet sheet1 = wb.getSheetAt(0);
      int rowCounter = 3;
      short cellIdx = 0;

      for (Facture facture : factures) {
        // Création d'une ligne
        // Création d'une ligne
        HSSFRow row = getOrCreateRow(sheet1, rowCounter++);
        createCell(wb, row, cellIdx++).setCellValue(facture.getNumeroFacture());
        createCell(wb, row, cellIdx++).setCellValue(facture.getPrixTotalHT());
        createCell(wb, row, cellIdx++).setCellValue(facture.getPrixTotalTTC());
        createCell(wb, row, cellIdx++).setCellValue(facture.getDateFacturation());
        createCell(wb, row, cellIdx++).setCellValue(facture.getDelaiPaiement());
        createCell(wb, row, cellIdx++).setCellValue(facture.getDateEcheance());
        createCell(wb, row, cellIdx++).setCellValue(facture.getNbJourRetard());
        createCell(wb, row, cellIdx++).setCellValue(facture.getFactureStatus());
        createCell(wb, row, cellIdx++).setCellValue(facture.getDateEncaissement());
        createCell(wb, row, cellIdx++).setCellValue(facture.getFraisRetard());
        // retour à la ligne suivante
        cellIdx = 0;
      }
      // sauvegarder le fichier de sortie
      saveWorkBook(wb, path);

    } catch (Exception exception) {
      logger.info("Une exception s'est produite : " + exception);
    } finally {
      if (fs != null) {
        fs.close();
      }
      if (wb != null) {
        wb.close();
      }
    }
  }

  /**
   * Sauvegarde du fichier
   *
   * @param le workbook
   * @param le nom du fichier de sortie
   * @throws Exception
   */
  private void saveWorkBook(HSSFWorkbook wb, String fileName) {

    logger.info("Saving excel file...");
    // transfer du contenu dans le fichier temporaire
    // creation d'un output stream
    FileOutputStream fileOut = null;

    try {
      fileOut = new FileOutputStream(fileName);
      wb.write(fileOut);
    } catch (Exception e) {
      logger.info("Error creating EXCEL output stream: " + e);
    } finally {
      //
      // fermeture du fichier temporaire
      //
      try {
        if (fileOut != null) {
          fileOut.close();
        }
      } catch (Exception e) {
        logger.info("Error closing EXCEL output stream: " + e);

      }
    }
  }

  /**
   * Création d'une cellule
   *
   * @param wb
   * @param row
   * @param column
   * @return
   */
  private HSSFCell createCell(HSSFWorkbook wb, HSSFRow row, int column) {
    return row.createCell((short) column);

  }

  /**
   * Création d'une ligne
   *
   * @param sheet
   * @param lineNb
   * @return
   */

  private HSSFRow getOrCreateRow(HSSFSheet sheet, int lineNb) {
    HSSFRow row = sheet.getRow(lineNb);
    if (row == null) {
      row = sheet.createRow(lineNb);
    }
    return row;
  }

}
