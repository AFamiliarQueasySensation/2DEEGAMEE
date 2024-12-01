package model;

// TODO: Auto-generated Javadoc
/**
 * Enum to represent various types of animals available in the game.
 * Each animal has a corresponding image URL for the pet icon.
 * 
 * @author Grant Von Hagen 
 * @version 1.0
 */
public enum ANIMAL {
    
    /** The cat. */
    CAT("view/resources/animals/cat.png"), 
    
    /** The dog. */
    DOG("view/resources/animals/dog.png"), 
    
    /** The fish. */
    FISH("view/resources/animals/Fish.png"), 
    
    /** The bird. */
    BIRD("view/resources/animals/goofy_ah_lookin_bird.png");

    /** The url animal. */
    private String urlAnimal;

    /**
     * Constructor to associate an image URL with each animal type.
     * 
     * @param urlAnimal The URL of the animal's icon image.
     */
    private ANIMAL(String urlAnimal) {
        this.urlAnimal = urlAnimal;
    }

    /**
     * Retrieves the URL to the animal's icon image.
     * 
     * @return the path to the animal icon image file.
     */
    public String getURL() {
        return this.urlAnimal;
    }

    /**
     * Compares a string to the enum values and returns the corresponding enum constant.
     * If no match is found, it returns null.
     * 
     * @param animal the string representation of an animal.
     * @return the corresponding ANIMAL enum constant, or null if no match is found.
     */
    public static ANIMAL compareStringToEnum(String animal) {
        for (ANIMAL a : ANIMAL.values()) {
            if (a.name().equalsIgnoreCase(animal)) {
                return a;
            }
        }
        return null;
    }
}
