package com.aliateck.fact.infrastructure.repository.edition;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.infrastructure.repository.edition.commun.HSSFiSelectionColors;


public class EditionSuiviFactures {
  private static final Logger logger = Logger.getLogger(EditionSuiviFactures.class.getName());
  short ligneNb = 5;
  HSSFCellStyle columnHeaderStyle;
  HSSFCellStyle titleStyle;
  HSSFCellStyle normalStyle;
  HSSFCellStyle statutStyleOk;
  HSSFCellStyle statutStyleKo;
  protected static final String DEFAULT_FONT_NAME = "Arial";
  protected HSSFCellStyle titre;
  protected HSSFCellStyle ctitre;



  protected static HSSFFont getFont(HSSFWorkbook wb, String fontName, short fontSize,
      short fontColor, boolean bold) {
    HSSFFont font = wb.createFont();
    font.setFontHeightInPoints(fontSize);
    font.setFontName(fontName);
    font.setBold(bold);
    font.setItalic(false);
    font.setStrikeout(false);
    font.setColor(fontColor);
    return font;
  }

  /**
   * Creates and returns a cellstyle font that is Black Aerial 9 normal boldweight.
   * 
   * @param wb the workbook
   */
  protected static HSSFFont getNormalFont(HSSFWorkbook wb) {
    return getFont(wb, DEFAULT_FONT_NAME, (short) 9, HSSFiSelectionColors.BLACK.getIndex(), false);
  }


  /**
   * Creates and returns a cellstyle font that is Black Aerial 9 bold.
   * 
   * @param wb the workbook
   */
  protected static HSSFFont getBoldFont(HSSFWorkbook wb) {
    return getFont(wb, DEFAULT_FONT_NAME, (short) 9, HSSFiSelectionColors.BLACK.getIndex(), true);
  }

  protected static HSSFFont getTitleFont(HSSFWorkbook wb, short fontSize, short fontColor) {
    return getTitleFont(wb, DEFAULT_FONT_NAME, fontSize, fontColor);
  }

  protected static HSSFCellStyle getTitleStyle(HSSFWorkbook wb, short cellColor, short policeColor,
      short policeSize) {
    HSSFCellStyle titre = wb.createCellStyle();
    titre.setAlignment(HorizontalAlignment.CENTER);
    titre.setVerticalAlignment(VerticalAlignment.CENTER);
    titre.setWrapText(false);
    titre.setFillForegroundColor(cellColor);
    titre.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    titre.setFont(getTitleFont(wb, policeSize, policeColor));

    return titre;
  }

  /**
   * Creates and returns a cellstyle font that is bold with the requested font, size and color.
   * 
   * @param wb the workbook
   * @param fontName name of the font eg Arial.
   * @param fontSize size of the font eg 12.
   * @param fontColor color of the font eg HSSFiSelectionColors.BLACK.index.
   */
  protected static HSSFFont getTitleFont(HSSFWorkbook wb, String fontName, short fontSize,
      short fontColor) {
    return getFont(wb, fontName, fontSize, fontColor, true);
  }

  /**
   * Creates and returns a cellstyle that is aligned centre and with the requested color.
   * 
   * @param wb the workbook
   * @param color color of the forground for the cell.
   */
  protected static HSSFCellStyle getTitleStyle(HSSFWorkbook wb, short color) {
    HSSFCellStyle titre = wb.createCellStyle();
    titre.setAlignment(HorizontalAlignment.CENTER);
    titre.setVerticalAlignment(VerticalAlignment.CENTER);
    titre.setWrapText(false);

    titre.setFillForegroundColor(color);
    titre.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    return titre;
  }

  /**
   *
   * @param wb
   */
  protected void createStyles(HSSFWorkbook wb) {

    // fonts
    HSSFFont tFont =
        getTitleFont(wb, DEFAULT_FONT_NAME, (short) 8, HSSFiSelectionColors.BLACK.getIndex());
    tFont.setBold(true);
    HSSFFont normalFont =
        getTitleFont(wb, DEFAULT_FONT_NAME, (short) 8, HSSFiSelectionColors.BLACK.getIndex());
    // normalFont.setBold(true);

    columnHeaderStyle = getTitleStyle(wb, HSSFiSelectionColors.LIGHT_CORNFLOWER_BLUE.getIndex());
    columnHeaderStyle.setFont(tFont);
    columnHeaderStyle.setWrapText(true);
    columnHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
    columnHeaderStyle.setVerticalAlignment(VerticalAlignment.CENTER);


    normalStyle = getTitleStyle(wb, HSSFiSelectionColors.WHITE.getIndex());
    normalStyle.setFont(normalFont);
    normalStyle.setWrapText(true);
    normalStyle.setAlignment(HorizontalAlignment.CENTER);
    normalStyle.setVerticalAlignment(VerticalAlignment.CENTER);

    statutStyleOk = getTitleStyle(wb, HSSFiSelectionColors.GREEN.getIndex());
    statutStyleOk.setFont(normalFont);
    statutStyleOk.setWrapText(true);
    statutStyleOk.setAlignment(HorizontalAlignment.CENTER);
    statutStyleOk.setVerticalAlignment(VerticalAlignment.CENTER);

    statutStyleKo = getTitleStyle(wb, HSSFiSelectionColors.RED.getIndex());
    statutStyleKo.setFont(normalFont);
    statutStyleKo.setWrapText(true);
    statutStyleKo.setAlignment(HorizontalAlignment.CENTER);
    statutStyleKo.setVerticalAlignment(VerticalAlignment.CENTER);

    // titre de la feuille
    titre = wb.createCellStyle();
    titre.setAlignment(HorizontalAlignment.LEFT);
    titre.setVerticalAlignment(VerticalAlignment.CENTER);
    titre.setWrapText(false);
    HSSFFont font = wb.createFont();
    font.setFontHeightInPoints((short) 12);
    font.setFontName(DEFAULT_FONT_NAME);
    font.setBold(true);
    font.setItalic(false);
    font.setStrikeout(false);
    titre.setFont(font);

    // commentaire titre feuille
    ctitre = wb.createCellStyle();
    ctitre.setAlignment(HorizontalAlignment.LEFT);
    ctitre.setVerticalAlignment(VerticalAlignment.CENTER);
    ctitre.setWrapText(false);
    font = wb.createFont();
    font.setFontHeightInPoints((short) 10);
    font.setFontName(DEFAULT_FONT_NAME);
    font.setBold(false);
    font.setItalic(true);
    font.setStrikeout(false);
    ctitre.setFont(font);
  }


  private void createTitle(HSSFSheet sheet1, HSSFWorkbook wb) {
    HSSFRow row = getOrCreateRow(sheet1, 1);
    createCell(wb, row, 0, applyBorder(titre)).setCellValue(
        new HSSFRichTextString("Fichier de suivi facturation : " + LocalDate.now().getYear()));
  }

  private void createHeaders(HSSFSheet sheet1, HSSFWorkbook wb) {
    HSSFRow row = getOrCreateRow(sheet1, 4);
    short cellIdx = 0;
    createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle))
        .setCellValue(new HSSFRichTextString("Numéro de facture"));
    createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle))
        .setCellValue(new HSSFRichTextString("Montant HT"));
    createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle))
        .setCellValue(new HSSFRichTextString("Montant TTC"));
    createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle))
        .setCellValue(new HSSFRichTextString("Date de facture"));
    createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle))
        .setCellValue(new HSSFRichTextString("Délai"));
    createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle))
        .setCellValue(new HSSFRichTextString("Date échéance"));
    createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle))
        .setCellValue(new HSSFRichTextString("Jours retard"));
    createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle))
        .setCellValue(new HSSFRichTextString("Statut facture"));
    createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle))
        .setCellValue(new HSSFRichTextString("Date encaissement"));
    createCell(wb, row, cellIdx++, applyBorder(columnHeaderStyle))
        .setCellValue(new HSSFRichTextString("Frais de retard"));

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


    try (POIFSFileSystem fs = new POIFSFileSystem(template);
        HSSFWorkbook wb = new HSSFWorkbook(fs)) {

      createStyles(wb);
      HSSFSheet sheet1 = wb.getSheetAt(0);
      createTitle(sheet1, wb);
      createHeaders(sheet1, wb);

      float totalTva = 0;
      short cellIdx = 0;

      for (Facture facture : factures) {
        // Création d'une ligne
        HSSFRow row = getOrCreateRow(sheet1, ligneNb++);
        createCell(wb, row, cellIdx++, applyBorder(normalStyle))
            .setCellValue(facture.getNumeroFacture());
        createCell(wb, row, cellIdx++, applyBorder(normalStyle))
            .setCellValue(facture.getPrixTotalHT() + " €");
        createCell(wb, row, cellIdx++, applyBorder(normalStyle))
            .setCellValue(facture.getPrixTotalTTC() + " €");
        createCell(wb, row, cellIdx++, applyBorder(normalStyle))
            .setCellValue(facture.getDateFacturation());
        createCell(wb, row, cellIdx++, applyBorder(normalStyle))
            .setCellValue(facture.getDelaiPaiement());
        createCell(wb, row, cellIdx++, applyBorder(normalStyle))
            .setCellValue(facture.getDateEcheance());
        createCell(wb, row, cellIdx++, applyBorder(normalStyle))
            .setCellValue(facture.getNbJourRetard());
        if (facture.getFactureStatus().equalsIgnoreCase("OK")) {
          createCell(wb, row, cellIdx++, applyBorder(statutStyleOk))
              .setCellValue(facture.getFactureStatus());
          createCell(wb, row, cellIdx++, applyBorder(statutStyleOk))
              .setCellValue(facture.getDateEncaissement());
        } else {
          createCell(wb, row, cellIdx++, applyBorder(statutStyleKo))
              .setCellValue(facture.getFactureStatus());
          createCell(wb, row, cellIdx++, applyBorder(statutStyleKo))
              .setCellValue(facture.getDateEncaissement());
        }


        createCell(wb, row, cellIdx++, applyBorder(normalStyle))
            .setCellValue(facture.getFraisRetard());
        // retour à la ligne suivante
        cellIdx = 0;
        totalTva += facture.getMontantTVA();
      }
      ligneNb++;
      HSSFRow row = getOrCreateRow(sheet1, ligneNb++);
      createCell(wb, row, 0, applyBorder(normalStyle)).setCellValue("Total TVA");
      createCell(wb, row, 1, applyBorder(normalStyle)).setCellValue(totalTva + "€");
      // sauvegarder le fichier de sortie
      saveWorkBook(wb, path);

    } catch (Exception exception) {
      logger.info("Une exception s'est produite : " + exception);
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

    try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
      wb.write(fileOut);
    } catch (Exception fnfe) {
      logger.info("Error creating EXCEL output stream: " + fnfe);
    }

  }

  protected static HSSFCell createCell(HSSFWorkbook wb, HSSFRow row, int column,
      HSSFCellStyle cellStyle) {
    HSSFCell cell = row.createCell((short) column);
    cell.setCellStyle(cellStyle);
    return cell;
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

  /**
   * Applies the border.
   * 
   * @param wb the workbook
   * @param thickness thickness style of the border eg BorderStyle.THIN
   * @param color color of the border HSSFiSelectionColors.BLACK.index
   */
  protected static HSSFCellStyle applyBorder(HSSFCellStyle style, BorderStyle thickness,
      short color) {
    HSSFCellStyle rstyle = style;
    // Style the cell with borders all around.
    rstyle.setBorderBottom(thickness);
    rstyle.setBottomBorderColor(color);
    rstyle.setBorderLeft(thickness);
    rstyle.setLeftBorderColor(color);
    rstyle.setBorderRight(thickness);
    rstyle.setRightBorderColor(color);
    rstyle.setBorderTop(thickness);
    rstyle.setTopBorderColor(color);
    return rstyle;
  }

  private HSSFCellStyle applyBorder(HSSFCellStyle style) {
    return applyBorder(style, BorderStyle.THIN, HSSFiSelectionColors.BLACK.getIndex());
  }


}
