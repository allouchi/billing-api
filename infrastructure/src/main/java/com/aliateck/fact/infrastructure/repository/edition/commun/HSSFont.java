package com.aliateck.fact.infrastructure.repository.edition.commun;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * 
 * Annotation based cell styling for HSSF.
 * 
 * 
 * @author lbaye
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HSSFont {

    public HSSFiSelectionColors background() default HSSFiSelectionColors.WHITE;

    public HSSFiSelectionColors color() default HSSFiSelectionColors.BLACK;

    public boolean bold() default false;

    public FORMAT format() default FORMAT.text;

    public String customFormat() default "";

    public ALIGNEMENT alignment() default ALIGNEMENT.ALIGN_CENTER;

    public short size() default -1;

    public BorderStyle border() default BorderStyle.NONE;

    public HSSFiSelectionColors borderColor() default HSSFiSelectionColors.BLACK;

    public boolean italic() default false;

    public boolean unlock() default false;

    public boolean wrapText() default true;

    public static enum FORMAT {
	text,
	percent,
	currency,
	date
    }

    public static enum ALIGNEMENT {
	ALIGN_GENERAL(HorizontalAlignment.CENTER),
	ALIGN_LEFT(HorizontalAlignment.LEFT),
	ALIGN_CENTER(HorizontalAlignment.CENTER),
	ALIGN_RIGHT(HorizontalAlignment.RIGHT),
	ALIGN_FILL(HorizontalAlignment.FILL),
	ALIGN_JUSTIFY(HorizontalAlignment.JUSTIFY),
	ALIGN_CENTER_SELECTION(HorizontalAlignment.CENTER_SELECTION);

	private HorizontalAlignment value;

	ALIGNEMENT(HorizontalAlignment i) {
	    this.value = i;
	}

	public HorizontalAlignment getAlignement() {
	    return this.value;
	}
    }

}
