package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * PermissionLabel class creates a custom label used to display permission-related messages.
 * It loads a specific font and handles text display with padding and wrapping.
 * 
 * @author Grant Von Hagen
 * @version 1.0
 */
public class PermissionLabel extends Label {

    public final static String FONT_PATH = "src/model/resources/sofadi.ttf";

    /**
     * Constructor to initialize the PermissionLabel with text and specific styles.
     * 
     * @param string the text to be displayed on the label
     */
    public PermissionLabel(String string) {
        setPrefWidth(600);
        setPrefHeight(400);
        setPadding(new Insets(40, 40, 40, 40));
        setText(string);
        setWrapText(true);
        setLabelFont();
    }

    /**
     * Sets the font for the label. If the custom font is not found, it defaults to Arial.
     */
    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Arial", 23));
        }
    }
}
