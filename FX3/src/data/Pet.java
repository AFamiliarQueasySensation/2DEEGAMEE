package data;

import javafx.application.Platform;

/**
* Pet entity used for creating a new pet with attributes such as health, hunger, sleep, and happiness.
* <br><br>
* Provides methods to interact with the pet, such as feeding {@link #feed(FoodItem)}, 
* gifting presents {@link #gift(GiftItem)}, and checking status {@link #checkState()}.<br><br>
*
* @version 0
* @author Maya Al Hourani
* @author Shivansh Kushwaha
* @author Grant Von Hagen 
*/

public class Pet {
    /** The pet's name. */
    private String name;
    
    /** The user's score for the pet. */
    private int score;
    
    /** The pet's health points (starts with 100). */
    private int health;
    
    /** The pet's hunger level. */
    private int hunger;
    
    /** The pet's sleep score (100 means wide awake, 0 is sleepy). */
    private int sleep;
    
    /** The pet's current state. */
    private PetState currentState;
    
    /** Flag indicating if the game is over. */
    private boolean isGameOver;
    
    /** The pet's happiness (starts at half). */
    private int happiness;

    /**
    * Pet constructor. Creates a new pet object.
    * @param Name the pet's name
    */
    public Pet(String Name){
        this.name = Name;
        this.score = 0;
        this.health = 100;
        this.hunger = 50;
        this.happiness = 50;
        this.sleep = 100;
    }

    /**
    * Feed method. Feeds the pet using the provided food item.
    */
    public void feed(FoodItem food) {
        if (isGameOver) return;
        
        // Make food more effective
        hunger = Math.min(100, hunger + (int)(food.getNutritionValue() * 100));
        happiness = Math.min(100, happiness + 5); // Small happiness boost from eating
        updateStatus();
    }

    /**
    * Gift method. Allows user to give the pet a gift boosting its happiness and increasing the player's score.
    *
    * @param gift the gift item being given to the pet
    */
    public void gift(GiftItem gift) {
        if (isGameOver) {
            return;
        }
        happiness = Math.min(100, happiness + gift.getHappinessBoost());
        score += gift.getScoreBoost();
        updateStatus();
    }

    /**
    * Sleep method. Makes the pet sleep, restoring its energy and slightly boosting happiness.
    */
    public void sleep() {
        if (isGameOver) {
            return;
        }
        sleep = 100;
        happiness = Math.min(100, happiness + 10);
        updateStatus();
    }

    /**
    * Updates the pet's status based on its current attributes.
    */
    public void updateStatus() {
        if (health <= 0 || hunger <= 0 || sleep <= 0 || happiness <= 0) {
            isGameOver = true;
        } else {
            isGameOver = false;
        }
        checkState();
    }

    /**
    * Checks the pet's current state and updates the isGameOver flag if necessary.
    */
    public void checkState() {
        if (health <= 0) {
            currentState = PetState.DEAD;
        } else if (hunger <= 0) {
            currentState = PetState.STARVING;
        } else if (sleep <= 0) {
            currentState = PetState.EXHAUSTED;
        } else if (happiness <= 0) {
            currentState = PetState.DEPRESSED;
        } else {
            currentState = PetState.HEALTHY;
        }
    }

    /**
    * Allows the user to play with the pet, increasing its happiness and decreasing its sleep.
    */
    public void playWithPet() {
        if (isGameOver) {
            return;
        }
        happiness = Math.min(100, happiness + 20);
        sleep = Math.max(0, sleep - 20);
        updateStatus();
    }

    /**
    * Gets the pet's name.
    * @return the pet's name
    */
    public String getName() {
        return name;
    }

    /**
    * Gets the user's score for the pet.
    * @return the user's score
    */
    public int getScore() {
        return score;
    }

    /**
    * Gets the pet's health points.
    * @return the pet's health points
    */
    public int getHealth() {
        return health;
    }

    /**
    * Gets the pet's hunger level.
    * @return the pet's hunger level
    */
    public int getHunger() {
        return hunger;
    }

    /**
    * Gets the pet's sleep score.
    * @return the pet's sleep score
    */
    public int getSleep() {
        return sleep;
    }

    /**
    * Gets the pet's happiness level.
    * @return the pet's happiness level
    */
    public int getHappiness() {
        return happiness;
    }

    /**
    * Gets the pet's current state.
    * @return the pet's current state
    */
    public PetState getCurrentState() {
        return currentState;
    }

    /**
    * Checks if the game is over.
    * @return true if the game is over, false otherwise
    */
    public boolean isGameOver() {
        return isGameOver;
    }
    
    /**
     * Sets it so pet is not dead
     */
    public void setGameOver(boolean value) {
    	this.isGameOver = value;
    }
    
    /**
     * Sets the Pet's Sleep
     * @param sleep
     */
    public void setSleep(int sleep) {
    	this.sleep = sleep;
    }
    
    /**
     * Sets the Pet's Hunger
     * @param hunger
     */
    public void setHunger(int hunger) {
    	this.hunger = hunger;
    }
    

    public void setHealth(int health) {
        this.health = Math.min(100, Math.max(0, health));  // Ensure health stays between 0 and 100
    }

    public void setHappiness(int happiness) {
        this.happiness = Math.min(100, Math.max(0, happiness));
    }
}