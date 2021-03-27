package com.aliateck.fact.infrastructure.repository.edition.commun;

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class XSSFExcel {

    /**
     * Constructeur par défaut
     */
    public XSSFExcel() {
    }

    /**
     * Retourn la cellule de l'excel, au format XSS
     * 
     * @param sheet
     * @param cellRef
     * @return
     */
    public static final XSSFCell getCellFromReference(XSSFSheet sheet, String cellRef) {
	CellReference cellReference = new CellReference(cellRef);
	return sheet.getRow(cellReference.getRow()).getCell(cellReference.getCol());
    }
}
