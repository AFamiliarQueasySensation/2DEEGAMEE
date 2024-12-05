package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/**
 * RedButton class creates a custom button with special styling and hover effects.
 * The button's appearance changes when pressed and hovered over, providing visual feedback to the user.
 * 
 * @author Grant Von Hagen
 * @version 1.0
 */
public class RedButton extends Button {

    private static final String FONT_PATH = "src/model/resources/sofadi.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/red_button_pressed.png');";
    private final String BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/red_button.png');";

    /**
     * Constructor for creating a RedButton with the specified text.
     * 
     * @param text the name of the button
     */
    public RedButton(String text) {
        setText(text);
        setButtonFont();
        setPrefHeight(49); // Red_Button = 190x49
        setPrefWidth(190); // Red_Button_Pressed = 190x45 (need to have offset)
        setStyle(BUTTON_STYLE);
        initializeButtonListener();
    }

    /**
     * Sets the font for the button, defaults to Arial if the custom font is not found.
     */
    private void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Arial", 23));
        }
    }

    /**
     * Changes the button's style when pressed (makes it look "pressed").
     */
    private void setButtonPressed() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    /**
     * Resets the button's style when released (restores original height and position).
     */
    private void setButton() {
        setStyle(BUTTON_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    /**
     * Initializes the button's mouse event listeners for press, release, and hover effects.
     */
    private void initializeButtonListener() {
        // When the mouse button is pressed, check if it's the left button (primary), and change style to pressed
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressed();
                }
            }
        });

        // When the mouse button is released, check if it's the left button (primary), and reset button style
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButton();
                }
            }
        });

        // When mouse enters the button, apply a drop shadow effect
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });

        // When mouse exits the button, remove the drop shadow effect
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }
}
