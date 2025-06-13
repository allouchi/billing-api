package com.sbatec.fact.infrastructure.repository.edition.commun;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;

/**
 * 
 * Couleurs prédéfinies iSelection.
 * 
 * Cette class peut serviur aussi à renseigner d'autres couleur pouvrant être associées à {@link HSSFFont}
 * 
 * @author lbaye
 * @see HSSFColorPredefined
 *
 */
public enum HSSFiSelectionColors {

    // PREDEFINED COLORS FROM HSSFColors

    BLACK(0x08, -1, 0x000000),
    WHITE(0x09, -1, 0xFFFFFF),
    RED(0x0A, -1, 0xFF0000),
    BRIGHT_GREEN(0x0B, -1, 0x00FF00),
    BLUE(0x0C, 0x27, 0x0000FF),
    YELLOW(0x0D, 0x22, 0xFFFF00),
    PINK(0x0E, 0x21, 0xFF00FF),
    TURQUOISE(0x0F, 0x23, 0x00FFFF),
    DARK_RED(0x10, 0x25, 0x800000),
    GREEN(0x11, -1, 0x008000),
    DARK_BLUE(0x12, 0x20, 0x000080),
    DARK_YELLOW(0x13, -1, 0x808000),
    VIOLET(0x14, 0x24, 0x800080),
    TEAL(0x15, 0x26, 0x008080),
    GREY_25_PERCENT(0x16, -1, 0xC0C0C0),
    GREY_50_PERCENT(0x17, -1, 0x808080),
    CORNFLOWER_BLUE(0x18, -1, 0x9999FF),
    MAROON(0x19, -1, 0x7F0000),
    LEMON_CHIFFON(0x1A, -1, 0xFFFFCC),

    ORCHID(0x1C, -1, 0x660066),
    CORAL(0x1D, -1, 0xFF8080),
    ROYAL_BLUE(0x1E, -1, 0x0066CC),
    LIGHT_CORNFLOWER_BLUE(0x1F, -1, 0xCCCCFF),

    SKY_BLUE(0x28, -1, 0x00CCFF),
    LIGHT_TURQUOISE(0x29, 0x1B, 0xCCFFFF),
    LIGHT_GREEN(0x2A, -1, 0xCCFFCC),
    LIGHT_YELLOW(0x2B, -1, 0xFFFF99),
    PALE_BLUE(0x2C, -1, 0x99CCFF),
    ROSE(0x2D, -1, 0xFF99CC),
    LAVENDER(0x2E, -1, 0xCC99FF),
    TAN(0x2F, -1, 0xFFCC99),
    LIGHT_BLUE(0x30, -1, 0x3366FF),
    AQUA(0x31, -1, 0x33CCCC),
    LIME(0x32, -1, 0x99CC00),
    GOLD(0x33, -1, 0xFFCC00),
    LIGHT_ORANGE(0x34, -1, 0xFF9900),
    ORANGE(0x35, -1, 0xFF6600),
    BLUE_GREY(0x36, -1, 0x666699),
    GREY_40_PERCENT(0x37, -1, 0x969696),
    DARK_TEAL(0x38, -1, 0x003366),
    SEA_GREEN(0x39, -1, 0x339966),
    DARK_GREEN(0x3A, -1, 0x003300),
    OLIVE_GREEN(0x3B, -1, 0x333300),
    BROWN(0x3C, -1, 0x993300),
    PLUM(0x3D, 0x19, 0x993366),
    INDIGO(0x3E, -1, 0x333399),
    GREY_80_PERCENT(0x3F, -1, 0x333333),

    // CUSTOM COLORS --> doivent être ajoutée dans les slots vide (index1 non
    // existant).
    // 8 < index < 0x40
    CUSTOM_COLOR_VERY_LIGHT_GRAY(0x20, -1, 0xE6E6E6, true),

    // MAXIMUM AUTORISE DANS LA PALETTE STANDARD
    AUTOMATIC(0x40, -1, 0x000000);

    private HSSFColor color;

    private int rgbValue;

    private boolean custom;

    private HSSFiSelectionColors(int index, int index2, int rgb) {
	this(index, index2, rgb, false);
    }

    private HSSFiSelectionColors(int index, int index2, int rgb, boolean custom) {
	this.color = new HSSFColor(index, index2, new java.awt.Color(rgb));
	this.rgbValue = rgb;
	this.custom = custom;
    }

    /**
     * 
     * Return the list of customized colors
     * 
     * @return
     */
    public static List<HSSFiSelectionColors> getCustomColors() {
	return Arrays.stream(HSSFiSelectionColors.values()).filter(c -> c.isCustom()).collect(Collectors.toList());
    }

    /**
     * Returns the red component in the range 0-255 in the default sRGB space.
     * 
     * @return the red component.
     * @see #getRGB
     */
    public byte getRed() {
	return (byte) ((this.rgbValue >> 16) & 0xFF);
    }

    /**
     * Returns the green component in the range 0-255 in the default sRGB space.
     * 
     * @return the green component.
     * @see #getRGB
     */
    public byte getGreen() {
	return (byte) ((this.rgbValue >> 8) & 0xFF);
    }

    /**
     * Returns the blue component in the range 0-255 in the default sRGB space.
     * 
     * @return the blue component.
     * @see #getRGB
     */
    public byte getBlue() {
	return (byte) ((this.rgbValue >> 0) & 0xFF);
    }

    ///////////////////////////////////////////////////////////////////////////

    /**
     * @see HSSFColor#getIndex()
     */
    public short getIndex() {
	return color.getIndex();
    }

    /**
     * @see HSSFColor#getIndex2()
     */
    public short getIndex2() {
	return color.getIndex2();
    }

    /**
     * @see HSSFColor#getTriplet()
     */
    public short[] getTriplet() {
	return color.getTriplet();
    }

    /**
     * @see HSSFColor#getHexString()
     */
    public String getHexString() {
	return color.getHexString();
    }

    /**
     * @return (a copy of) the HSSFColor assigned to the enum
     */
    public HSSFColor getColor() {
	return new HSSFColor(getIndex(), getIndex2(), new java.awt.Color(this.rgbValue));
    }

    /**
     * @return true if this is a color added manually (ie different from the {@link HSSFColorPredefined} color list)
     */
    public boolean isCustom() {
	return custom;
    }

    /**
     * @return rgb value as an int (ex : 0xff0000 for red)
     */
    public int getRgbValue() {
	return rgbValue;
    }

}
