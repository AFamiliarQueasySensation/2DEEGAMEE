package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * The inventoryLabel class is a custom JavaFX Label used for displaying text in the inventory UI.
 * It customizes the appearance of the label by setting a specific font and padding, as well as enabling
 * text wrapping. If the specified font cannot be loaded, a fallback font (Arial) is applied instead.
 * 
 * This class extends the JavaFX Label class and is typically used in the inventory system to represent
 * items or descriptions within the game.
 * 
 * <p><strong>Constants:</strong></p>
 * <ul>
 *   <li><code>FONT_PATH</code> - The path to the custom font file (Sofadi).</li>
 *   <li><code>BACKGROUND_IMAGE</code> - A placeholder for any potential background image (currently unused).</li>
 * </ul>
 * 
 * <p><strong>Constructor:</strong></p>
 * <ul>
 *   <li><code>inventoryLabel(String string)</code> - Constructs an inventoryLabel with the given text string, 
 *       setting the font, padding, and other display properties.</li>
 * </ul>
 * 
 * @author Grant Von Hagen
 * @version 1.0
 */
public class inventoryLabel extends Label {

    /** The file path to the custom font for the label. */
    public final static String FONT_PATH = "src/model/resources/sofadi.ttf";
    
    /** The background image for the label (currently unused). */
    public final static String BACKGROUND_IMAGE = "";

    /**
     * Constructs an inventoryLabel with the specified text string.
     * 
     * Sets the preferred width and height of the label, applies padding, enables text wrapping,
     * and sets the font to a custom font (Sofadi) if available, or defaults to Arial if the font cannot be loaded.
     * 
     * @param string the text to display on the label
     */
    public inventoryLabel(String string) {
        setPrefWidth(20);
        setPrefHeight(20);
        setPadding(new Insets(40, 40, 40, 40));
        setText(string);
        setWrapText(true);
        setLabelFont();
    }

    /**
     * Sets the font for the label to the custom font located at FONT_PATH.
     * If the font file cannot be found, it defaults to using Arial.
     */
    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Arial", 23));
        }
    }
}
