package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.MainMenuViewManager;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

/**
 * Main class that launches the JavaFX application. 
 * It initializes the main stage and displays the main menu through the MainMenuViewManager.
 * 
 * @author Grant Von Hagen 
 * @version 1.0
 * 
 */
public class Main extends Application {

    /**
     * The start method is called when the application is launched. 
     * It sets up the main stage by using MainMenuViewManager and shows it to the user.
     * 
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Create an instance of MainMenuViewManager
            MainMenuViewManager manager = new MainMenuViewManager();

            // Get the main stage from the manager
            stage = manager.getMainStage();

            // Show the stage
            stage.show();

        } catch (Exception e) {
            // Print stack trace in case of an exception
            e.printStackTrace();
        }
    }

    /**
     * The main method to launch the JavaFX application.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
