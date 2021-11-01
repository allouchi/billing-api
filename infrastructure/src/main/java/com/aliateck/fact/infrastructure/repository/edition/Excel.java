package com.aliateck.fact.infrastructure.repository.edition;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.poi.hssf.eventusermodel.HSSFUserException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;
import com.aliateck.fact.infrastructure.repository.edition.commun.HSSFiSelectionColors;

/**
 * 
 */

public class Excel {

  public static final String COMMON_XLS_PROTECTION_KEY = "kjDBZ+gL$wSYFPYKTrD!";
  protected static final String DEFAULT_FONT_NAME = "Arial";
  protected HSSFCellStyle titre;
  protected HSSFCellStyle ctitre;
  private NumberFormat currencyFormat;
  private NumberFormat currencyKFormat;
  private NumberFormat kFormat;
  private NumberFormat percentFormat;

  /**
   * 
   */
  public Excel() {
    // currency
    Locale defaultLocale = new Locale("fr", "FR", "EURO");
    currencyFormat = NumberFormat.getCurrencyInstance(defaultLocale);
    currencyKFormat = NumberFormat.getCurrencyInstance(defaultLocale);
    currencyKFormat.setMaximumFractionDigits(0);
    kFormat = NumberFormat.getNumberInstance(defaultLocale);
    kFormat.setMaximumFractionDigits(0);
    percentFormat = NumberFormat.getNumberInstance();
    percentFormat.setMaximumFractionDigits(2);

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
   * @param cellColor
   * @param bgColor
   * @return
   */
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
   * Creates and returns a cellstyle that is aligned centre and with the requested color.
   * 
   * @param wb the workbook
   * @param color color of the forground for the cell.
   */
  protected static HSSFCellStyle getTitleStyleWithBorder(HSSFWorkbook wb, short color) {
    HSSFCellStyle titre = wb.createCellStyle();
    titre.setAlignment(HorizontalAlignment.CENTER);
    titre.setVerticalAlignment(VerticalAlignment.CENTER);
    titre.setWrapText(false);

    titre.setTopBorderColor(HSSFiSelectionColors.BLACK.getIndex());
    titre.setBottomBorderColor(HSSFiSelectionColors.BLACK.getIndex());
    titre.setLeftBorderColor(HSSFiSelectionColors.BLACK.getIndex());
    titre.setRightBorderColor(HSSFiSelectionColors.BLACK.getIndex());
    titre.setBorderTop(BorderStyle.MEDIUM);
    titre.setBorderBottom(BorderStyle.MEDIUM);
    titre.setBorderLeft(BorderStyle.MEDIUM);
    titre.setBorderRight(BorderStyle.MEDIUM);

    titre.setFillForegroundColor(color);
    titre.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    return titre;
  }

  protected static HSSFCellStyle setBorderWidth(HSSFCellStyle style, BorderStyle borderWidth) {
    style.setBorderTop(borderWidth);
    style.setBorderBottom(borderWidth);
    style.setBorderLeft(borderWidth);
    style.setBorderRight(borderWidth);
    return style;
  }

  /**
   * Creates and returns a cellstyle that is aligned centre and with the white color.
   * 
   * @param wb the workbook
   */
  protected static HSSFCellStyle getStyle(HSSFWorkbook wb) {
    HSSFCellStyle style = wb.createCellStyle();

    style.setTopBorderColor(HSSFiSelectionColors.BLACK.getIndex());
    style.setBottomBorderColor(HSSFiSelectionColors.BLACK.getIndex());
    style.setLeftBorderColor(HSSFiSelectionColors.BLACK.getIndex());
    style.setRightBorderColor(HSSFiSelectionColors.BLACK.getIndex());
    style.setBorderTop(BorderStyle.THIN);
    style.setBorderBottom(BorderStyle.THIN);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);
    style.setAlignment(HorizontalAlignment.CENTER);

    return style;
  }

  protected static HSSFCellStyle getStyle(HSSFWorkbook wb, HorizontalAlignment align, short color) {

    HSSFCellStyle style = wb.createCellStyle();

    style.setAlignment(align);
    style.setFillForegroundColor(color);
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    style.setFont(getNormalFont(wb));

    return style;
  }

  /**
   * Creates and returns a cellstyle font that is bold with the requested font, size and color.
   * 
   * @param wb the workbook
   * @param fontName name of the font eg Arial.
   * @param fontSize size of the font eg 12.
   * @param fontColor color of the font eg HSSFiSelectionColors.BLACK.index.
   */
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
   * Creates a cell and applies the style.
   * 
   * </br>
   * NB : prefer using {@link #createCell(HSSFRow, short, HSSFCellStyle)} instead.
   * 
   * @param wb the workbook
   * @param row the row to create the cell in
   * @param column the column number to create the cell in
   * @param cellStyle the cell style.
   * 
   */
  protected static HSSFCell createCell(HSSFWorkbook wb, HSSFRow row, int column,
      HSSFCellStyle cellStyle) {
    HSSFCell cell = row.createCell((short) column);
    cell.setCellStyle(cellStyle);
    return cell;
  }

  /**
   * 
   * Converts a String in acceptable reference name. This method must be used when using a string
   * that contains illegal characters as a reference name for a cell. </br>
   * Illegal characters are (space is a character) : [<b><code>\. ()</code></b>]
   * 
   * @param name
   * @return
   */
  protected static String refName(String name) {
    return name.replaceAll("[\\(\\)\\.\\s%]", "_");
  }

  protected static HSSFCell createCell(HSSFWorkbook wb, HSSFRow row, short column,
      HSSFCellStyle cellStyle, CellType cellType) {
    HSSFCell cell = row.createCell(column);
    cell.setCellStyle(cellStyle);
    cell.setCellType(cellType);
    return cell;
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

  protected static HSSFCellStyle getStyle_LargeNumber(HSSFWorkbook wb) {
    HSSFCellStyle style = wb.createCellStyle();

    style = getTitleStyle(wb, HSSFiSelectionColors.WHITE.getIndex());

    HSSFFont normalFont =
        getFont(wb, DEFAULT_FONT_NAME, (short) 8, HSSFiSelectionColors.BLACK.getIndex(), false);
    style.setFont(normalFont);

    style.setWrapText(true);
    style.setBorderBottom(BorderStyle.NONE);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);
    style.setAlignment(HorizontalAlignment.RIGHT);

    HSSFDataFormat format = wb.createDataFormat();
    style.setDataFormat(format.getFormat("#,##0.00"));

    return style;
  }

  protected static HSSFCellStyle getStyle_Pourcentage(HSSFWorkbook wb) {
    HSSFCellStyle style = wb.createCellStyle();

    style = getTitleStyle(wb, HSSFiSelectionColors.WHITE.getIndex());

    HSSFFont normalFont =
        getFont(wb, DEFAULT_FONT_NAME, (short) 8, HSSFiSelectionColors.BLACK.getIndex(), false);
    style.setFont(normalFont);

    style.setWrapText(true);
    style.setBorderBottom(BorderStyle.NONE);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);
    style.setAlignment(HorizontalAlignment.RIGHT);

    HSSFDataFormat format = wb.createDataFormat();
    style.setDataFormat(format.getFormat("#,##0.00 %"));

    return style;
  }

  protected static HSSFCellStyle getStyle_Date_JJMMAAAA(HSSFWorkbook wb) {
    HSSFCellStyle style = wb.createCellStyle();

    style = getTitleStyle(wb, HSSFiSelectionColors.WHITE.getIndex());

    HSSFFont normalFont =
        getFont(wb, DEFAULT_FONT_NAME, (short) 8, HSSFiSelectionColors.BLACK.getIndex(), false);
    style.setFont(normalFont);

    style.setWrapText(true);
    style.setBorderBottom(BorderStyle.NONE);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);
    style.setAlignment(HorizontalAlignment.RIGHT);

    HSSFDataFormat format = wb.createDataFormat();
    style.setDataFormat(format.getFormat("dd/mm/yyyy"));

    return style;
  }

  /**
   * Merges cells from column1 to cloumn2 in row.
   * 
   * @param wb the workbook
   * @param sheet the sheet
   * @param row row
   * @param column1 column1
   * @param column2 column2
   * @border
   */
  public static void mergeCells(HSSFWorkbook wb, HSSFSheet sheet, int row, int column1, int column2,
      BorderStyle border) {
    // region
    CellRangeAddress region = new CellRangeAddress(row, row, column1, column2);
    if (region.getNumberOfCells() > 1) {
      setRegionBorders(border, region, sheet, wb);
      sheet.addMergedRegion(region);
    }
  }

  public static void mergeCells(HSSFSheet sheet, int row, int column1, int column2) {
    // region
    CellRangeAddress region = new CellRangeAddress(row, row, column1, column2);
    if (region.getNumberOfCells() > 1) {
      sheet.addMergedRegion(region);
    }
  }

  /**
   * 
   * Clones the style but NOT the associated font (use {@link #cloneFont(HSSFFont)} for that
   * purpose).
   * 
   * <b>WARNING</b> : do not overuse this function as there is a limit on the number of individual
   * styles that can be present on a single sheet.
   * 
   */
  protected static HSSFCellStyle cloneStyle(HSSFCellStyle style, HSSFWorkbook wb) {
    HSSFCellStyle newStyle = wb.createCellStyle();

    newStyle.setAlignment(style.getAlignment());
    newStyle.setBorderBottom(style.getBorderBottom());
    newStyle.setBorderLeft(style.getBorderLeft());
    newStyle.setBorderRight(style.getBorderRight());
    newStyle.setBorderTop(style.getBorderTop());
    newStyle.setBottomBorderColor(style.getBottomBorderColor());
    newStyle.setDataFormat(style.getDataFormat());
    newStyle.setFillBackgroundColor(style.getFillBackgroundColor());
    newStyle.setFillForegroundColor(style.getFillForegroundColor());
    newStyle.setFillPattern(style.getFillPattern());
    newStyle.setFont(style.getFont(wb));
    newStyle.setHidden(style.getHidden());
    newStyle.setIndention(style.getIndention());
    newStyle.setLeftBorderColor(style.getLeftBorderColor());
    newStyle.setLocked(style.getLocked());
    newStyle.setRightBorderColor(style.getRightBorderColor());
    newStyle.setRotation(style.getRotation());
    newStyle.setTopBorderColor(style.getTopBorderColor());
    newStyle.setVerticalAlignment(style.getVerticalAlignment());
    newStyle.setWrapText(style.getWrapText());

    return newStyle;
  }

  public static void setRegionBorders(BorderStyle border, CellRangeAddress region, HSSFSheet sheet,
      HSSFWorkbook wb) {

    // bottom top
    HSSFRow bottomRow = getOrCreateRow(sheet, region.getLastRow());
    HSSFRow topRow = getOrCreateRow(sheet, region.getFirstRow());
    for (int i = region.getFirstColumn(); i <= region.getLastColumn(); i++) {
      CellUtil.setCellStyleProperty(CellUtil.getCell(bottomRow, i), "borderBottom", border);
      CellUtil.setCellStyleProperty(CellUtil.getCell(topRow, i), "borderTop", border);
    }

    // left right
    for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
      HSSFRow row = getOrCreateRow(sheet, i);
      CellUtil.setCellStyleProperty(CellUtil.getCell(row, region.getFirstColumn()), "borderLeft",
          border);
      CellUtil.setCellStyleProperty(CellUtil.getCell(row, region.getLastColumn()), "borderRight",
          border);
    }

  }

  /**
   * Merges cells from column1 to cloumn2 and row1 to row2.
   * 
   * @param wb the workbook
   * @param sheet the sheet
   * @param row1 row1
   * @param row2 row2
   * @param column1 column1
   * @param column2 column2
   * @border
   */
  protected static void mergeMultiRowCells(HSSFWorkbook wb, HSSFSheet sheet, int row1, int row2,
      int column1, int column2, BorderStyle border) {
    // region
    CellRangeAddress region = new CellRangeAddress(row1, row2, column1, column2);
    if (region.getNumberOfCells() > 1) {
      setRegionBorders(border, region, sheet, wb);
      sheet.addMergedRegion(region);
    }
  }

  protected static void copyRange(HSSFWorkbook wb, AreaReference area, HSSFSheet toSheet,
      CellReference startCell) {
    CellReference[] crefs = area.getAllReferencedCells();

    int startRow = startCell.getRow() - area.getFirstCell().getRow();
    int startCol = startCell.getCol() - area.getFirstCell().getCol();

    HSSFSheet fromSheet = wb.getSheet(area.getFirstCell().getSheetName());

    for (CellReference cellToCopy : crefs) {

      HSSFRow fromRow = fromSheet.getRow(cellToCopy.getRow());
      HSSFCell fromCell = fromRow.getCell(cellToCopy.getCol());

      HSSFRow rowToCreate = getOrCreateRow(toSheet, cellToCopy.getRow() + startRow);

      // Dont need really to copy height
      // rowToCreate.setHeight(fromRow.getHeight());

      if (fromCell != null) {
        HSSFCell cellToCreate = rowToCreate.createCell((short) (cellToCopy.getCol() + startCol));
        cellToCreate.setCellStyle(fromCell.getCellStyle());

        cellToCreate.setCellType(fromCell.getCellType());
        switch (fromCell.getCellType()) {
          case BOOLEAN:
            cellToCreate.setCellValue(fromCell.getBooleanCellValue());
            break;
          case ERROR:
            cellToCreate.setCellValue(fromCell.getErrorCellValue());
            break;
          case FORMULA:
            cellToCreate.setCellFormula(fromCell.getCellFormula());
            break;
          case NUMERIC:
            cellToCreate.setCellValue(fromCell.getNumericCellValue());
            break;
          case STRING:
          default:
            cellToCreate.setCellValue(fromCell.getRichStringCellValue());
        }
      }
    }
  }

  /**
   * Copy a range of cells that are in the same sheet. Does not require a sheet name. It handles
   * empty cells by ignoring them during the copy process.
   * 
   * @param area
   * @param sheet
   * @param startCell
   */
  protected static void copyRangeSameSheet(AreaReference area, HSSFSheet sheet,
      CellReference startCell) {
    CellReference[] crefs = area.getAllReferencedCells();

    int startRow = startCell.getRow() - area.getFirstCell().getRow();
    int startCol = startCell.getCol() - area.getFirstCell().getCol();

    for (CellReference cellToCopy : crefs) {
      if (cellToCopy != null) {
        HSSFRow fromRow = sheet.getRow(cellToCopy.getRow());

        if (fromRow != null) {
          HSSFCell fromCell = fromRow.getCell(cellToCopy.getCol());

          HSSFRow rowToCreate = getOrCreateRow(sheet, cellToCopy.getRow() + startRow);

          if (fromCell != null) {
            HSSFCell cellToCreate =
                rowToCreate.createCell((short) (cellToCopy.getCol() + startCol));

            cellToCreate.setCellType(fromCell.getCellType());
            cellToCreate.setCellStyle(fromCell.getCellStyle());

            switch (fromCell.getCellType()) {
              case BOOLEAN:
                cellToCreate.setCellValue(fromCell.getBooleanCellValue());
                break;
              case ERROR:
                cellToCreate.setCellValue(fromCell.getErrorCellValue());
                break;
              case FORMULA:
                cellToCreate.setCellFormula(fromCell.getCellFormula());
                break;
              case NUMERIC:
                cellToCreate.setCellValue(fromCell.getNumericCellValue());
                break;
              case STRING:
              default:
                cellToCreate.setCellValue(fromCell.getRichStringCellValue());
            }
          }
        }
      }
    }
  }



  /**
   * 
   * Puts a reference name on a cell.
   * 
   * @param wb
   * @param sheetName
   * @param cellName
   * @param cellXlCoord
   * @return
   */
  public static final HSSFName nameCell(HSSFWorkbook wb, String sheetName, String cellName, int row,
      int col) {
    return nameCell(wb, cellName, new CellReference(row, col, true, true));
  }

  /**
   * 
   * Puts a reference on a cell using a {@link CellReference}.
   * 
   * @param wb
   * @param cellName
   * @param cellReference
   * @return
   */
  public static final HSSFName nameCell(HSSFWorkbook wb, String cellName,
      CellReference cellReference) {
    HSSFName namedCell = wb.createName();
    namedCell.setNameName(refName(cellName));
    namedCell.setRefersToFormula(cellReference.formatAsString());
    return namedCell;
  }

  /**
   * 
   * 
   * Creates a reference of a cell coordinates (eg : $A2)
   * 
   * @param wb
   * @param sheetName
   * @param cellName
   * @param cellXlCoord
   * @return
   * @throws HSSFUserException
   */
  public static final String reference(HSSFWorkbook wb, int row, int col) throws HSSFUserException {
    return new CellReference(row, col, true, true).formatAsString();
  }

  /**
   * 
   * 
   * Creates an Area and returns its range as String (eg : $A1:$B10).
   * 
   * @param wb
   * @param sheetName
   * @param cellName
   * @param cellXlCoord
   * @return
   * @throws HSSFUserException
   */
  public static final String area(HSSFWorkbook wb, int startRow, int startCol, int endRow,
      int endCol) throws HSSFUserException {
    AreaReference areaReference =
        new AreaReference(new CellReference(startRow, startCol, true, true),
            new CellReference(endRow, endCol, true, true), SpreadsheetVersion.EXCEL2007);
    return areaReference.formatAsString();
  }

  /**
   * 
   * Puts a reference name on a <b>Range</b>.
   * 
   * @param wb
   * @param cellName
   * @param cellReference
   * @return
   */
  public static final HSSFName nameRange(HSSFWorkbook wb, String cellName,
      AreaReference areaReference) {
    HSSFName namedCell = wb.createName();
    namedCell.setNameName(refName(cellName));
    namedCell.setRefersToFormula(areaReference.formatAsString());
    return namedCell;
  }

  /**
   * 
   * 
   * @param wb
   * @param sheetName
   * @param cellName
   * @param cellXlCoord
   * @return
   * @throws HSSFUserException
   */
  public static final HSSFName nameRange(HSSFWorkbook wb, String cellName, int startRow,
      int startCol, int endRow, int endCol) throws HSSFUserException {
    AreaReference areaReference =
        new AreaReference(new CellReference(startRow, startCol, true, true),
            new CellReference(endRow, endCol, true, true), SpreadsheetVersion.EXCEL2007);
    return nameRange(wb, cellName, areaReference);
  }

  /**
   * 
   * Puts a reference name on a <b>Range</b>.
   * 
   * @param wb
   * @param sheetName
   * @param cellName
   * @param cellXlCoord
   * @return
   * @throws HSSFUserException
   */
  public static final HSSFName nameRange(HSSFWorkbook wb, String cellName,
      CellReference startCellRef, CellReference endCellRef) throws HSSFUserException {
    AreaReference areaReference =
        new AreaReference(startCellRef, endCellRef, SpreadsheetVersion.EXCEL2007);
    return nameRange(wb, cellName, areaReference);
  }

  public static final HSSFName getHSSFName(HSSFWorkbook wb, String cellName) {
    // retrieve the named range
    int cellNameIdx = wb.getNameIndex(cellName);
    return wb.getNameAt(cellNameIdx);
  }

  public static final HSSFCell getNamedCell(HSSFWorkbook wb, String cellName) {
    return getCellFromReference(wb,
        new CellReference(getHSSFName(wb, cellName).getRefersToFormula()));
  }

  public static final HSSFCell getCellFromReference(HSSFWorkbook wb, CellReference cellReference) {
    HSSFSheet sheet = wb.getSheet(cellReference.getSheetName());
    HSSFRow row = sheet.getRow(cellReference.getRow());
    return row.getCell(cellReference.getCol());
  }

  public static final HSSFCell getCellFromReference(HSSFSheet sheet, String cellRef) {
    CellReference cellReference = new CellReference(cellRef);
    return sheet.getRow(cellReference.getRow()).getCell(cellReference.getCol());
  }

  /**
   * 
   * This method should be preferred to {@link HSSFSheet#createRow(int)} as it gets an existing row
   * in the HSSF Model rather than creating a new one for each call. This enables retrieving cells
   * already created.
   * 
   * @param sheet
   * @param lineNb
   * @return
   */
  public static final HSSFRow getOrCreateRow(HSSFSheet sheet, int lineNb) {
    HSSFRow row = sheet.getRow(lineNb);
    if (row == null) {
      row = sheet.createRow(lineNb);
    }
    return row;
  }

  /*
   * ============================================================================= =
   */
  /* FORMULAS */
  /*
   * ============================================================================= =
   */


  /**
   *
   * Returns ${col1 ,line1} + ${col2 ,line2}
   *
   * @param wb
   * @param line1
   * @param col1
   * @param line2
   * @param col2
   * @return
   * @throws HSSFUserException
   */
  public String formulaAdd(HSSFWorkbook wb, int line1, int col1, int line2, int col2)
      throws HSSFUserException {
    return formulaAdd(wb, reference(wb, line1, col1), reference(wb, line2, col2));
  }

  /**
   *
   * Returns SUM(${column, line1} , ${column, line2})
   *
   * @param wb
   * @param column
   * @param line1
   * @param line2
   * @return
   */
  public String formulaSum(HSSFWorkbook wb, int column, int line1, int line2) {
    CellReference start = new CellReference(line1, column, true, true);
    CellReference end = new CellReference(line2, column, true, true);
    return "SUM(" + start.formatAsString() + ":" + end.formatAsString() + ")";
  }

  /**
   *
   * Returns SUM(${column1, line1} , ${column2, line2})
   *
   * @param wb
   * @param column
   * @param line1
   * @param line2
   * @return
   */
  public String formulaSum(HSSFWorkbook wb, int column1, int line1, int column2, int line2) {
    CellReference start = new CellReference(line1, column1, true, true);
    CellReference end = new CellReference(line2, column2, true, true);
    return "SUM(" + start.formatAsString() + ":" + end.formatAsString() + ")";
  }

  /**
   *
   * Returns SUM(${column, line[0]}, ${column, line[1]}, ... ${column, line[last]})
   *
   * @param wb
   * @param column
   * @param lines
   * @return
   */
  public String formulaSumAllSameColum(HSSFWorkbook wb, int column1, Integer... lines) {

    String formula = "SUM(";
    if (lines.length > 0) {
      for (int i = 0; i < lines.length; i++) {
        formula += new CellReference(lines[i], column1, true, true).formatAsString();
        if (i < lines.length - 1) {
          formula += ",";
        }
      }
    }
    formula += ")";
    return formula;
  }

  /**
   *
   * Returns SUMIF(${ifCol, ifLine1}:${ifCol, ifLine2} , condition, ${column, line1}:${column,
   * line2} )
   *
   * @param wb
   * @param column
   * @param line1
   * @param line2
   * @return
   */
  public String formulaSumIf(HSSFWorkbook wb, int column, int line1, int line2, String ifCondition,
      int ifCol, int ifLine1, int ifLine2) {
    CellReference start = new CellReference(line1, column, true, true);
    CellReference end = new CellReference(line2, column, true, true);

    CellReference startIf = new CellReference(ifLine1, ifCol, true, true);
    CellReference endIf = new CellReference(ifLine2, ifCol, true, true);
    return "SUMIF(" + start.formatAsString() + ":" + end.formatAsString() + ",\"" + ifCondition
        + "\"," + startIf.formatAsString() + ":" + endIf.formatAsString() + ")";
  }

  /**
   *
   * Returns ${col1 ,line1} + ${col2 ,line2}
   *
   *
   * Current version of poi does not support IFERROR. We are therefore using IF(ISERROR())
   *
   * @TODO space missing after comma is taken away by POI. --> should override {@link FormulaParser}
   *
   * @param wb
   * @param line1
   * @param col1
   * @param line2
   * @param col2
   * @return
   * @throws HSSFUserException
   */
  public String formulaError(String formula, String backupValue) throws HSSFUserException {
    return "IF(ISERROR(" + formula + "), \"" + backupValue + "\" , (" + formula + ") )";
  }

  /**
   *
   * @see #formulaAdd(HSSFWorkbook, int, int, int, int)
   *
   * @param wb
   * @param cell1
   * @param cell2
   * @return
   * @throws HSSFUserException
   */
  protected String formulaAdd(HSSFWorkbook wb, String cell1, String cell2)
      throws HSSFUserException {
    return "(" + cell1 + "+" + cell2 + ")";
  }

  /**
   *
   * Returns ${col1 ,line1} / ${col2 ,line2} * 100
   *
   * @param wb
   * @param line1
   * @param col1
   * @param line2
   * @param col2
   * @return
   * @throws HSSFUserException
   */
  protected String formulaPercentage(HSSFWorkbook wb, int line1, int col1, int line2, int col2)
      throws HSSFUserException {
    return formulaPercentage(wb, reference(wb, line1, col1), reference(wb, line2, col2));
  }

  /**
   * @see #formulaPercentage(HSSFWorkbook, int, int, int, int)
   *
   * @param wb
   * @param cell1
   * @param cell2
   * @return
   * @throws HSSFUserException
   */
  protected String formulaPercentage(HSSFWorkbook wb, String cell1, String cell2)
      throws HSSFUserException {
    return cell1 + "/" + cell2 + "* 100"; // formulaError(cell1 + "/" + cell2 + "* 100", "0"); //
                                          // DOES NOT WORK... PB wiht
    // poi version ?
  }

  /*
   * ============================================================================= =
   */
  /* COMMON CELL CREATION HELPERS */
  /*
   * ============================================================================= =
   */

  protected String formulaRank(HSSFWorkbook wb, int cellCol, int cellRow, int startCol,
      int startRow, int endCol, int endRow) throws HSSFUserException {
    return "RANK( " + reference(wb, cellRow, cellCol) + ","
        + area(wb, startRow, startCol, endRow, endCol) + ", 0 )";
  }

  /*
   * ============================================================================= =
   */
  /* COLORS */
  /*
   * ============================================================================= =
   */

  /**
   *
   *
   * Creates a title for a grid with subtitles for each column
   *
   * @param wb
   * @param sheet
   * @param title
   * @param lineNb
   * @param columnNb
   * @param mainTitleStyle
   * @param subTitleStyle
   * @param titles
   */
  protected void createTitlesForGrid(HSSFWorkbook wb, HSSFSheet sheet, int lineNb, int columnNb,
      HSSFCellStyle mainTitleStyle, HSSFCellStyle subTitleStyle, String mainTitle,
      String... titles) {
    HSSFRow row = getOrCreateRow(sheet, lineNb);

    int numberOfColumns = titles.length;

    createCell(wb, row, (short) columnNb, mainTitleStyle)
        .setCellValue(new HSSFRichTextString(mainTitle));
    mergeCells(wb, sheet, (short) lineNb, (short) columnNb,
        (short) (columnNb + numberOfColumns - 1), BorderStyle.THIN);

    row.setHeight((short) (128 * 4));

    lineNb++;
    row = getOrCreateRow(sheet, lineNb);
    row.setHeight((short) (128 * 3));

    for (int i = 0; i < numberOfColumns; i++) {
      createCell(wb, row, (short) columnNb++, subTitleStyle)
          .setCellValue(new HSSFRichTextString(titles[i]));
    }
  }

  public void createColorSheet(HSSFWorkbook wb, HSSFSheet sheet) {
    sheet.setColumnWidth((short) (0), (short) (30 * 256));
    sheet.setColumnWidth((short) (1), (short) (2 * 256));
    sheet.setColumnWidth((short) (2), (short) (30 * 256));

    int i = 0;
    for (HSSFiSelectionColors color : HSSFiSelectionColors.values()) {
      HSSFRow row = getOrCreateRow(sheet, i);
      HSSFCell cell = row.createCell((short) 0);
      cell.setCellValue(new HSSFRichTextString(color.toString()));

      HSSFCellStyle cellStyle = wb.createCellStyle();
      cellStyle.setAlignment(HorizontalAlignment.CENTER);
      cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      cellStyle.setWrapText(false);
      cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      cellStyle.setFont(
          getFont(wb, DEFAULT_FONT_NAME, (short) 10, HSSFiSelectionColors.WHITE.getIndex(), true));
      cellStyle.setFillForegroundColor(color.getIndex());
      cell.setCellStyle(cellStyle);

      cell = row.createCell((short) 2);
      cell.setCellValue(new HSSFRichTextString(color.toString()));

      cellStyle = wb.createCellStyle();
      cellStyle.setAlignment(HorizontalAlignment.CENTER);
      cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      cellStyle.setWrapText(false);
      cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      cellStyle.setFont(
          getFont(wb, DEFAULT_FONT_NAME, (short) 10, HSSFiSelectionColors.BLACK.getIndex(), true));
      cellStyle.setFillForegroundColor(color.getIndex());
      cell.setCellStyle(cellStyle);

    }
  }



  /**
   *
   * @param maxChars
   * @return le nom avec au maximum 4 characteres + ".". Par exemple: <br>
   *         "TOTOBOULA" -> "TOTO." pour appel avec 4 <br>
   *         "VO" -> "VO" pour apple appele avec 4
   */
  protected String getNomMaxChars(int maxChars, String nom) {
    if (nom == null) {
      return null;
    } else {
      if (nom.length() <= maxChars) {
        return nom;
      } else {
        return nom.substring(0, maxChars) + ".";
      }
    }
  }

  /**
   *
   * @param currency
   * @return
   */
  public HSSFRichTextString getCurrency(double currency) {
    HSSFRichTextString formattedCurrency = new HSSFRichTextString(currencyFormat.format(currency));
    return formattedCurrency;
  }

  /**
   *
   * @param currency
   * @return
   */
  public HSSFRichTextString getKCurrency(double currency) {
    HSSFRichTextString formattedCurrency =
        new HSSFRichTextString(currencyKFormat.format(currency / 1000));
    return formattedCurrency;
  }

  /**
   *
   * @param percent
   * @return
   */
  public HSSFRichTextString getPercent(double percent) {
    HSSFRichTextString formattedPercent =
        new HSSFRichTextString(percentFormat.format(percent) + " %");
    return formattedPercent;
  }


  /**
   *
   * @param date
   * @return
   */
  public HSSFRichTextString getDayMonth(Date date) {
    HSSFRichTextString h = new HSSFRichTextString();
    SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM");
    if (date != null) {
      h = new HSSFRichTextString(dateFormat.format(date));
    }
    return h;
  }

  public HSSFRichTextString getDDMMYYYY(Date date) {
    HSSFRichTextString h = new HSSFRichTextString();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    if (date != null) {
      h = new HSSFRichTextString(dateFormat.format(date));
    }
    return h;
  }

  public HSSFRichTextString getDDMMYYYY_HHMMSS(Date date) {
    HSSFRichTextString h = new HSSFRichTextString();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    if (date != null) {
      h = new HSSFRichTextString(dateFormat.format(date));
    }
    return h;
  }



  /**
   *
   * @param date
   * @return
   */
  public String getYear(Date date) {
    String h = "";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
    if (date != null) {
      h = dateFormat.format(date);
    }
    return h;

  }

  /**
   *
   * @param bool
   * @return
   */
  public HSSFRichTextString getIntString(int intValue) {
    return new HSSFRichTextString(Integer.toString(intValue));
  }

  /**
   *
   * @param bool
   * @return
   */
  public HSSFRichTextString getFrenchBoolean(boolean bool) {
    if (bool) {
      return new HSSFRichTextString("Oui");
    } else {
      return new HSSFRichTextString("Non");
    }
  }

  /**
   *
   * @param wb
   */
  protected void createStyles(HSSFWorkbook wb) {
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



  /**
   * create Header for a sheet.
   * 
   * @param sheet
   * @param desc description
   * @param from
   * @param to
   */
  public void createHeaders(HSSFSheet sheet, String desc, Date from, Date to) {
    HSSFRow row = getOrCreateRow(sheet, 2);
    HSSFCell cell = row.createCell((short) 0);
    cell.setCellStyle(titre);
    StringBuilder b = new StringBuilder();
    if (desc != null) {
      b.append(desc);
    }


    cell.setCellValue(new HSSFRichTextString(b.toString()));

    row = getOrCreateRow(sheet, 4);
    cell = row.createCell((short) 0);
    cell.setCellStyle(ctitre);
    b = new StringBuilder();

    cell.setCellValue(new HSSFRichTextString(b.toString()));

  }

  public String getHeader(String desc, Date from, Date to) {
    StringBuilder b = new StringBuilder();
    if (desc != null) {
      b.append(desc);
    }

    return b.toString();
  }

  /**
   * 
   * @param date
   * @return
   */
  public String getDayMonthYear(Date date) {
    String s = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy");
    if (date != null) {
      s = dateFormat.format(date);
    }
    return s;
  }

  /**
   * 
   * @param currency
   * @return
   */
  public HSSFRichTextString getKFormat(double currency) {
    HSSFRichTextString formattedCurrency = new HSSFRichTextString(kFormat.format(currency / 1000));
    return formattedCurrency;
  }

  /**
   * @return the kFormat
   */
  public NumberFormat getkFormat() {
    return kFormat;
  }

  /**
   * @param kFormat the kFormat to set
   */
  public void setkFormat(NumberFormat kFormat) {
    this.kFormat = kFormat;
  }

}
