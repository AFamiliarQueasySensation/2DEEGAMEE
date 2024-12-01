package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/** 
 * This class represents an Inventory Button that triggers a subscene for inventory actions when clicked.
 * It uses custom button styles for different states (normal and pressed) and manages the visibility of the subscene.
 * 
 * @author Grant Von Hagen
 * @version 1.0
 */
public class InventoryButton extends Button {

    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/inventory_selected.png');";
    private final String BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/inventory.png');";

    private final static int BUTTON_HEIGHT = 100;
    private final static int BUTTON_WIDTH = 100;

    private boolean showing;
    
    BlueSubSceneBox subScene;

    /**
     * Constructor that initializes the button and the associated subscene.
     * Adds the subscene to the provided main pane and sets up button functionality.
     * 
     * @param mainPane the main pane where the subscene will be added
     */
    public InventoryButton(Pane mainPane) {
        
        subScene = new BlueSubSceneBox(); // this is just the blue subscene
        mainPane.getChildren().add(subScene);
        
        // Set button style and size
        setStyle(BUTTON_STYLE);
        setPrefHeight(BUTTON_HEIGHT);
        setPrefWidth(BUTTON_WIDTH);
        
        // Initially set the button to not show the subscene
        showing = false;
        
        // Add a click listener to toggle the visibility of the subscene
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (showing) {
                        subScene.moveSubScene(); // Hide the subscene
                    } else {
                        subScene.moveSubScene(); // Show the subscene
                    }
                    showing = !showing;
                }
            }
        });
    }

    /**
     * Returns the subscene associated with this button.
     * 
     * @return the BlueSubSceneBox instance
     */
    public BlueSubSceneBox getSubScene() {
        return subScene;
    }

    /**
     * Returns whether the subscene is currently visible or not.
     * 
     * @return true if the subscene is visible, false otherwise
     */
    public boolean subSceneShowing() {
        return this.showing;
    }
}
