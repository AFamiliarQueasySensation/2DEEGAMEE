package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

// TODO: Auto-generated Javadoc
/**
 * For Creating Choosing Animal Subscene.
 *
 * @author Grant Von Hagen
 * @version 1.0
 */
public class AnimalLabel extends Label {
    
    /** The Constant FONT_PATH. */
    public final static String FONT_PATH = "src/model/resources/sofadi.ttf";

    /** The Constant BACKGROUND_IMAGE. */
    public final static String BACKGROUND_IMAGE = "";

    /**
     * Instantiates a new animal label.
     *
     * @param text the text
     */
    public AnimalLabel(String text) {
        setPrefWidth(600);
        setPrefHeight(400);
        setPadding(new Insets(40, 40, 40, 40));
        setText(text);
        setWrapText(true);
        setLabelFont();
    }

    /**
     * Sets the label font.
     */
    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Arial", 23));
        }
    }
}