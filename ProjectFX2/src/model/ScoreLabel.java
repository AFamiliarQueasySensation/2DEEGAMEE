package model;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import java.io.FileInputStream;

/**
 * ScoreLabel class creates a custom label that displays the score with a background image.
 * The label is styled with a custom font and background image.
 * 
 * @author Grant Von Hagen
 * @version 1.0
 */
public class ScoreLabel extends Label {

    private final static String FONT_PATH = "src/model/resources/sofadi.ttf"; // Path to the custom font
    private final static int LABEL_WIDTH = 150; // Width of the label
    private final static int LABEL_HEIGHT = 50; // Height of the label

    /**
     * Constructor for creating a ScoreLabel with the specified text.
     * 
     * @param text the text to display on the label (e.g., score value)
     */
    public ScoreLabel(String text) {
        setPrefWidth(LABEL_WIDTH);
        setPrefHeight(LABEL_HEIGHT);
        
        // Set the background image for the label
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("model/resources/ScoreLabel.png", LABEL_WIDTH, LABEL_HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER); // Center-align the text within the label
        setText(text); // Set the text of the label
        setLabelFont(); // Set the font for the label
    }

    /**
     * Sets the font of the ScoreLabel.
     */
    private void setLabelFont() {
        try {
            // Try to load the custom font
            setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 15));
        } catch (FileNotFoundException e) {
            // If the font is not found, use the default Arial font
            setFont(Font.font("Arial", 15));
        }
    }
}
