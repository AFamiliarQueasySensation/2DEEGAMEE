/** 
 * This class represents a blue subscene with a background image and provides
 * functionality to slide the subscene in and out of view.
 * 
 * @author Grant Von Hagen
 * @author Mahip Varlani
 * @version 1.0
 */


package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;


public class BlueSubSceneBox extends SubScene {

    private final static String FONT_PATH = "src/model/resources/sofadi.ttf";
    private final static String BACKGROUND_IMAGE = "model/resources/blue_page.png";
    
    private boolean isHidden;
    
    /**
     * Creating method for bluescenebox that is used in the main menu
     */
    
    public BlueSubSceneBox() {
        super(new AnchorPane(), 600, 400);
        prefWidth(600);
        prefHeight(400);

        // Set Background
        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        
        isHidden = true;
        
        // Set background image
        root2.setBackground(new Background(image));

        setLayoutX(1024);
        setLayoutY(180);
    }
    
    /**
     * Moves the subscene in and out of view with a sliding animation.
     */
    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        
        if (isHidden) {
            transition.setToX(-675);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        
        transition.play();
    }
    
    /**
     * Returns the AnchorPane root of the subscene.
     * 
     * @return AnchorPane root of the subscene
     */
    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

}
