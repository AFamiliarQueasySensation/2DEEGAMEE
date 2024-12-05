package data;

import java.lang.ModuleLayer.Controller;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Alert;
import javafx.scene.control.ContentDisplay;

/**
 * The Game class represents the main gameplay logic, including pet care, inventory management, and saving/loading game data.
 * It also manages periodic updates to the pet's stats (e.g., hunger, energy, happiness).
 * 
 * @author Mahip Tulsi Varlani
 * @version 1.0
 */
public class Game {

    private Pet currentPet;
    private String gameState;
    private Inventory inventory;
    private Controller uiController;
    private ContentDisplay displayScreen;
    private Timer statusTimer;

    /**
     * Constructor for the Game class. Initializes the current pet, inventory, and starts a timer
     * to periodically update the pet's status every 5 seconds.
     */
    public Game() {
        this.currentPet = new Pet("MyPet");
        this.inventory = new Inventory();
        
        // Create timer to update pet stats every few seconds
        statusTimer = new Timer();
        statusTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentPet != null) {
                    currentPet.updateStatus();
                }
            }
        }, 0, 5000); // Update every 5 seconds
    }

    /**
     * Retrieves the current pet.
     * 
     * @return the current pet
     */
    public Pet getCurrentPet() {
        return currentPet;
    }

    /**
     * Feeds the current pet with the specified food item.
     * 
     * @param food the food item to feed the pet
     */
    public void feedPet(FoodItem food) {
        if (currentPet != null) {
            currentPet.feed(food);
        }
    }

    /**
     * Makes the pet play to increase its happiness and energy.
     */
    public void playWithPet() {
        if (currentPet != null) {
            currentPet.playWithPet();
        }
    }

    /**
     * Loads a game from a file.
     * 
     * @param fileName the file name to load the game from
     */
    public void loadFiles(String fileName) {
        // Implementation for loading game data from file
    }

    /**
     * Saves the current game to a file.
     * 
     * @param fileName the file name to save the game to
     */
    public void saveGame(String fileName) {
        // Implementation for saving game data to file
    }

    /**
     * Exits the current game and performs any necessary cleanup.
     * 
     * @param fileName the file name (if applicable) for saving or logging
     */
    public void exitGame(String fileName) {
        // Implementation for exiting the game
    }

    /**
     * Displays a tutorial to help the user understand how to play the game.
     * 
     * @param fileName the file name (if needed) to load specific tutorial data
     */
    public void tutorial(String fileName) {
        String[] instructions = {
            "Welcome to PetPals!",
            "Here's how to take care of your virtual pet:",
            "",
            "1. Health Bar: Keep your pet healthy by maintaining other stats",
            "2. Hunger Bar: Feed your pet regularly using food items",
            "3. Energy Bar: Let your pet rest when energy is low",
            "4. Happiness Bar: Play with your pet and give gifts to keep them happy",
            "",
            "Tips:",
            "- Feed your pet when hunger is low",
            "- Play with your pet to increase happiness",
            "- Watch your pet's energy level",
            "- Keep all stats above 20% to maintain good health",
            "",
            "Have fun with your new pet!"
        };
        
        // Build the message string
        StringBuilder message = new StringBuilder();
        for (String instruction : instructions) {
            message.append(instruction).append("\n");
        }
        
        // Create and show the tutorial alert
        Alert tutorial = new Alert(Alert.AlertType.INFORMATION);
        tutorial.setTitle("Tutorial");
        tutorial.setHeaderText("How to Play");
        tutorial.setContentText(message.toString());
        tutorial.showAndWait();
    }
}

