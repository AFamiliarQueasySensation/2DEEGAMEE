package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

// TODO: Auto-generated Javadoc
/** 
 * This Class deals with when you pick a sprite animal.
 * 
 * @author Grant Von Hagen
 * @version 1.0
 */
public class animalPicker extends VBox {
    
    /** The kuromi image. */
    private ImageView kuromiImage;
    
    /** The animal image. */
    private ImageView animalImage;
    
    /** The kuromi not chosen. */
    //Chosen or not chosen works with kuromiImage
    private String kuromiNotChosen = "model/resources/KuromiHappy.png";
    
    /** The kuromi chosen. */
    private String kuromiChosen = "model/resources/KuromiMoreHappy.png";
    
    /** The animal. */
    private ANIMAL animal;
    
    /** The animal taken. */
    private boolean animalTaken;
    
    /**
     * Instantiates a new animal picker.
     *
     * @param animal the animal
     */
    public animalPicker(ANIMAL animal) {
        kuromiImage = new ImageView(kuromiNotChosen);
        animalImage = new ImageView(animal.getURL());
        this.animal = animal;
        animalTaken = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(animalImage);
        this.getChildren().add(kuromiImage);
    }
    
    /**
     * Gets the animal.
     *
     * @return Return's the animal
     */
    public ANIMAL getAnimal() {
        return animal;
    }
    
    /**
     * Gets the animal taken.
     *
     * @return T/F If the animal is taken
     */
    public boolean getanimalTaken() {
        return animalTaken;
    }
    
    /**
     * Gets the animal image.
     *
     * @return the animal image
     */
    public ImageView getAnimalImage() {
        return animalImage;
    }
    
    /**
     * Sets the animal taken.
     *
     * @param animalTaken the new animal taken
     */
    public void setAnimalTaken(boolean animalTaken) {
        this.animalTaken = animalTaken;
        String animalToSet = this.animalTaken ? kuromiChosen : kuromiNotChosen;
        kuromiImage.setImage(new Image(animalToSet));
    }
}
