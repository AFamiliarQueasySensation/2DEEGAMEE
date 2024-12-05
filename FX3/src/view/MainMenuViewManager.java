	
package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import data.Pet;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ANIMAL;
import model.RedButton;
import model.AnimalLabel;
import model.BlueSubSceneBox;
import model.PermissionLabel;
import model.animalPicker;
import data.CharacterStats;
import data.CharacterStatsApp;
import data.Game;

/**
 * MainMenuViewManager manages the main menu scene, including creating buttons, subscenes,
 * and handling user interactions for the pet game. Also manages Loading and saving game states, and 
 * extra settings for Character like revival.
 * 
 * @author Grant Von Hagen
 * @author Mahip Tulsi Varlani
 * @author Shvansh
 * @version 2.0
 */
public class MainMenuViewManager {

	/** The Constant HEIGHT. */
	private static final int HEIGHT = 1024;
	
	/** The Constant WIDTH. */
	private static final int WIDTH = 768;
	
	/** The main pane. */
	private AnchorPane mainPane;
	
	/** The main scene. */
	private Scene mainScene;
	
	/** The main stage. */
	private Stage mainStage;

	/** The Constant MENU_BUTTON_START_X. */
	private final static int MENU_BUTTON_START_X = 100;
	
	/** The Constant MENU_BUTTON_START_Y. */
	private final static int MENU_BUTTON_START_Y = 100;
	
	/** The button spacing. */
	private static int BUTTON_SPACING = 100;

	/** The Constant MENU_TITLE_X. */
	private final static int MENU_TITLE_X = 300;
	
	/** The Constant MENU_TITLE_Y. */
	private final static int MENU_TITLE_Y = 30;

	/** The Constant BACKGROUND_IMAGE. */
	private final static String BACKGROUND_IMAGE = "view/resources/main_menu_background.png";
	
	/** The default password. */
	private String DEFAULT_PASSWORD = "password";
	
	/** The Constant filePath. */
	private File filePath;

	/** The choose animal sub scene. */
	private BlueSubSceneBox chooseAnimalSubScene;
	
	/** The load game. */
	private BlueSubSceneBox loadGame;
	
	/** The tutorial sub scene. */
	private BlueSubSceneBox tutorialSubScene;
	
	/** The settings. */
	private BlueSubSceneBox settings;
	
	/** The parent sub screen. */
	private BlueSubSceneBox parentSubScreen;

	/** The current sub scene. */
	private BlueSubSceneBox currentSubScene;

	/** The menu buttons. */
	List<RedButton> menuButtons;

	/** The animal list. */
	// Holds an array of all the animal possible animals
	List<animalPicker> animalList;
	
	/** The animal load list. */
	List<animalPicker> animalLoadList;
	
	/** The animal load stats. */
	List<Integer> animalLoadStats;
	
	/** The chosen animal. */
	// Holds the current animal they chose
	private ANIMAL chosenAnimal;
	
	/** The chosen pet. */
	private Pet chosenPet;
	
	/** The music volume. */
	private int music_volume;
	
	/** The playtime limit. */
	private int playtime_limit;

	/** The game. */
	private Game game; // Add this as a class field



	/**
	 * Initializes a new main menu view manager.
	 * This sets up the main menu interface, including the stage, scene,
	 * buttons, subscenes, and additional UI elements like the background and title.
	 */
	public MainMenuViewManager() {
		
		String dir = System.getProperty("user.dir");

        File csvFile = new File(dir, "resources/stats.csv");

        filePath = csvFile;
		
	    menuButtons = new ArrayList<>();
	    mainPane = new AnchorPane();
	    mainScene = new Scene(mainPane, HEIGHT, WIDTH);
	    mainStage = new Stage();
	    mainStage.setScene(mainScene);
	    createDefaultSettings();
	    createSubScene();
	    createButtons();
	    createBackgroundColor();
	    createTitle();
	    addIcon();
	    addTitle();
	    
	}

	/**
	 * Initializes all the buttons used in the main menu.
	 * This includes buttons for new game, load game, tutorial, settings, and exit.
	 */
	private void createButtons() {
	    createNewGameButton();
	    createLoadGameButton();
	    createTutorialButton();
	    createSettingsButton();
	    createCreditsButton();
	    createExitButton();
	}

	/**
	 * Creates a new instance of the selected pet based on the chosen animal.
	 * This is used to initialize the pet object when starting a new game.
	 */
	private void createPet() {
	    chosenPet = new Pet(chosenAnimal.name());
	}

	/**
	 * Sets the default settings for the game.
	 * These include the default music volume and playtime limit.
	 */
	private void createDefaultSettings() {
	    this.music_volume = 8; // Default music volume level
	    this.playtime_limit = 0; // No playtime limit by default
	}

	/**
	 * Literally just moves the current subscreen out of the way, and shows the new
	 * one.
	 *
	 * @param subScene the sub scene
	 */
	private void showSubScene(BlueSubSceneBox subScene) {
		if (subScene == currentSubScene) {
			currentSubScene.moveSubScene();
			currentSubScene = null;
			return;
		}

		else if (currentSubScene != null) {
			currentSubScene.moveSubScene();
		}
		subScene.moveSubScene();
		currentSubScene = subScene;

	}

	/**
	 * Initializes all the animals from ANIMAL.java (enum), and outputs them on the
	 * New Player Subscene
	 * 
	 * @return HBox of animal choice
	 */
	private HBox createAnimalToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		animalList = new ArrayList<>();
		for (ANIMAL animal : ANIMAL.values()) {
			animalPicker animalToPick = new animalPicker(animal);

			// Drop Shadow on Character choice image
			animalToPick.getAnimalImage().setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					animalToPick.setEffect(new DropShadow());
					// TODO Auto-generated method stub
				}
			});
			animalToPick.getAnimalImage().setOnMouseExited(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					animalToPick.setEffect(null);
				}
			});

			animalList.add(animalToPick); // Causes no duplicates in choices
			box.getChildren().add(animalToPick); // this displays the images
			animalToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					for (animalPicker animal : animalList) {
						animal.setAnimalTaken(false);
					}
					animalToPick.setAnimalTaken(true);
					chosenAnimal = animalToPick.getAnimal();
					// TODO Auto-generated method stub

				}

			});

		}
		box.setLayoutX(300 - (118 * 2));
		box.setLayoutY(100);

		return box;
	}

	/**
	 * Creates the animal selection interface for loading saved animals.
	 * This method dynamically generates clickable elements for each saved animal,
	 * allowing the player to load a saved game state.
	 *
	 * @return the HBox containing animal choices for loading.
	 * @throws IOException if there is an issue reading the saved data from the file.
	 */
	private HBox createLoadAnimalToChoose() {
	    // Create a horizontal box to hold animal choices
	    HBox box = new HBox();
	    box.setSpacing(20); // Add spacing between animal choices
	    animalLoadList = new ArrayList<>();
	    animalLoadStats = new ArrayList<>(Collections.nCopies(15, 0)); // Initialize stats list with default values

	    try {
	        // Read saved character stats from the CSV file
	        List<CharacterStats> loadedStats = CharacterStatsApp.readFromCSV(filePath);

	        for (CharacterStats stats : loadedStats) {
	            // Match the saved animal data to the ANIMAL enum
	            ANIMAL matchedAnimal = ANIMAL.compareStringToEnum(stats.getValue1());
	            if (matchedAnimal == null) // Skip invalid or unmatched animals
	                continue;

	            // Create a visual picker for the animal
	            animalPicker animalToPick = new animalPicker(matchedAnimal);

	            // Add a drop shadow effect when hovering over the animal image
	            animalToPick.getAnimalImage().setOnMouseEntered(new EventHandler<MouseEvent>() {
	                @Override
	                public void handle(MouseEvent arg0) {
	                    animalToPick.setEffect(new DropShadow());
	                }
	            });
	            // Remove the drop shadow effect when the mouse exits the image
	            animalToPick.getAnimalImage().setOnMouseExited(new EventHandler<MouseEvent>() {
	                @Override
	                public void handle(MouseEvent arg0) {
	                    animalToPick.setEffect(null);
	                }
	            });

	            // Add the animal picker to the list and display it
	            animalLoadList.add(animalToPick); // Prevents duplicates
	            box.getChildren().add(animalToPick); // Displays the animal images in the HBox

	            // Add a click event to select an animal
	            animalToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
	                @Override
	                public void handle(MouseEvent arg0) {
	                    // Deselect all animals and select the clicked one
	                    for (animalPicker animal : animalLoadList) {
	                        animal.setAnimalTaken(false);
	                    }
	                    animalToPick.setAnimalTaken(true); // Mark the clicked animal as selected
	                    chosenAnimal = animalToPick.getAnimal(); // Update the chosen animal

	                    // Update the animal stats from the selected save data
	                    animalLoadStats.set(0, stats.getValue2()); // Score
	                    animalLoadStats.set(1, stats.getValue3()); // Health
	                    animalLoadStats.set(2, stats.getValue4()); // Happiness
	                    animalLoadStats.set(3, stats.getValue5()); // Hunger
	                    animalLoadStats.set(4, stats.getValue6());
	                    animalLoadStats.set(5, stats.getValue7());
	                    animalLoadStats.set(6, stats.getValue8());
	                    animalLoadStats.set(7, stats.getValue9());
	                    animalLoadStats.set(8, stats.getValue10());
	                    animalLoadStats.set(9, stats.getValue11());
	                    animalLoadStats.set(10, stats.getValue12());
	                    animalLoadStats.set(11, stats.getValue13());
	                    animalLoadStats.set(12, stats.getValue14());
	                }
	            });
	        }
	    } catch (IOException e) {
	        // Print the stack trace if there is an issue reading the file
	        e.printStackTrace();
	    }

	    // Position the HBox on the screen
	    box.setLayoutX(300 - (118 * 2));
	    box.setLayoutY(100);

	    return box; // Return the HBox containing the animal selection options
	}


	/**
	 * Start Button on New Game.
	 *
	 * @return The Red Button found throughout the main menu
	 */
	private RedButton createStartButton() {
		RedButton startButton = new RedButton("Start");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);

		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				// Stupid thing was throwing errors cuz it was null
				if (chosenAnimal != null) {
					createPet();
					GameViewManager newGame = new GameViewManager();
					newGame.createNewGame(mainStage, chosenAnimal, chosenPet, music_volume, playtime_limit);
				}
			}
		});

		return startButton;
	}

	/**
	 * Creates the load start button.
	 *
	 * @return the red button
	 */
	private RedButton createLoadStartButton() {
		RedButton startButton = new RedButton("Start");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);

		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				// Stupid thing was throwing errors cuz it was null
				if (chosenAnimal != null) {
					createPet();
					GameViewManager newGame = new GameViewManager();

					newGame.loadnewGame(mainStage, chosenAnimal, chosenPet, music_volume, playtime_limit,
							(ArrayList<Integer>) animalLoadStats);
				}
			}
		});

		return startButton;
	}

	/**
	 * Creates multiple subscenes in main menu === If you are going to make them do
	 * different things remove them andplace them into helper functions like I did
	 * in createAnimalChooseSubScene() below.
	 */
	private void createSubScene() {

		createTutorialSubScrene();

		createLoadSubScrene();

		createAnimalSettingSubScene();

		createAnimalChooseSubScene();

	}

	/**
	 * Creates the Load Screen SubScene where players can select saved games to load.
	 * This subscene displays the available saves and allows the player to choose one
	 * to resume their progress.
	 */
	private void createLoadSubScrene() {
	    loadGame = new BlueSubSceneBox(); // Initialize the load game subscene
	    mainPane.getChildren().add(loadGame); // Add it to the main pane

	    // Label to instruct the player to choose a save file
	    AnimalLabel chooseSaveFile = new AnimalLabel("Choose Save");
	    chooseSaveFile.setLayoutX(180); // Position the label horizontally
	    chooseSaveFile.setLayoutY(160); // Position the label vertically
	    loadGame.getPane().getChildren().add(chooseSaveFile); // Add the label to the subscene pane

	    // Add a list of saved games to the subscene
	    loadGame.getPane().getChildren().add(createLoadAnimalToChoose());

	    // Add a start button to load the selected game
	    loadGame.getPane().getChildren().add(createLoadStartButton());
	}

	/**
	 * Creates the Tutorial SubScene, which explains how to play the game.
	 * This subscene provides step-by-step instructions on managing the pet
	 * and using the game's shortcut commands.
	 */
	private void createTutorialSubScrene() {
	    tutorialSubScene = new BlueSubSceneBox(); // Initialize the tutorial subscene
	    mainPane.getChildren().add(tutorialSubScene); // Add it to the main pane

	    // Instructions label for the tutorial

		PermissionLabel instructions = new PermissionLabel( "Welcome to PetPals!\n\n"
				+ "1. Choose your pet from the available animals\n" + "2. Feed your pet to maintain their hunger\n"
				+ "3. Play with your pet to keep them happy\n" 
				+ "4. Make sure your pet gets enough rest\n" + "5. Keep all stats above 0 to keep your pet healthy!\n" + "6. Use Shortcut Commands WASD to move, v/V to visit vet, p/P to play, i/I for using Items and\n y/Y for pet to sleep!\n"
						);
		instructions.setLayoutX(20);
		instructions.setLayoutY(-25);

		tutorialSubScene.getPane().getChildren().addAll(instructions);
	}

	/**
	 * Creates the Main Menu Settings SubScene, which includes settings and parental controls.
	 * The scene allows users to adjust the volume and input a parental control password
	 * to access further settings.
	 */
	private void createAnimalSettingSubScene() {
	    // Initialize the settings subscene
	    settings = new BlueSubSceneBox();
	    settings.setPickOnBounds(false); // Ensures interactions are only within the subscene
	    mainPane.getChildren().add(settings);

	    // Add a label for the volume slider
	    PermissionLabel volumeLabel = new PermissionLabel("Volume");
	    volumeLabel.setLayoutX(-15);
	    volumeLabel.setLayoutY(-135);
	    settings.getPane().getChildren().add(volumeLabel);

	    // Add a label for the settings title
	    PermissionLabel settingsLabel = new PermissionLabel("Settings/Parental Controls");
	    settingsLabel.setLayoutX(220);
	    settingsLabel.setLayoutY(-175);
	    settings.getPane().getChildren().add(settingsLabel);

	    // Add a label for the parental password field
	    PermissionLabel permissionLabel = new PermissionLabel("Input Password");
	    permissionLabel.setLayoutX(180);
	    permissionLabel.setLayoutY(-25);
	    settings.getPane().getChildren().add(permissionLabel);

	    // Add a text field for password input
	    TextField passwordInput = new TextField();
	    passwordInput.setLayoutX(175);
	    passwordInput.setLayoutY(200);
	    passwordInput.setPrefSize(250, 40);
	    settings.getPane().getChildren().add(passwordInput);

	    // Add a button to submit the password
	    RedButton submitButton = new RedButton("Enter");
	    submitButton.setLayoutX(204);
	    submitButton.setLayoutY(280);
	    settings.getPane().getChildren().add(submitButton);

	    // Add a volume slider to adjust the music volume
	    Slider volumeSlider = new Slider(-15, 105, 50); // Slider range from -15 to 105 with default value 50
	    volumeSlider.setMajorTickUnit(10); // Major tick every 10 units
	    volumeSlider.setMinorTickCount(5); // Minor tick count between major ticks
	    volumeSlider.setSnapToTicks(true); // Snap slider to tick marks
	    volumeSlider.setLayoutX(120);
	    volumeSlider.setLayoutY(60);
	    settings.getPane().getChildren().add(volumeSlider);

	    // Event handler for the submit button
	    submitButton.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent arg0) {
	            // Check if the input password matches the default password
	            if (passwordInput.getText().equals(DEFAULT_PASSWORD)) {
	                parentSubScreen(); // Navigate to the parental control screen
	            } else {
	                shakeInputBox(passwordInput); // Shake the input box for incorrect password
	            }
	        }
	    });

	    // Event handler for the volume slider
	    volumeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent arg0) {
	            music_volume = (int) volumeSlider.getValue(); // Update the music volume
	            volumeSlider.adjustValue(volumeSlider.getValue() + 5); // Slightly adjust slider value
	        }
	    });
	}


	/**
	 * Animates a "shaking" effect on the given text field to indicate an error, 
	 * such as a wrong password input. The text field will move left and right 
	 * rapidly to grab the user's attention.
	 *
	 * @param passwordInput the {@link TextField} to apply the shake animation to
	 */
	private void shakeInputBox(TextField passwordInput) {
	    int shakeSteps = 10; // Number of steps for the shaking animation
	    double shakeDistance = 4; // Distance the text field moves during the shake
	    Timeline shakeTimeline = new Timeline(); // Timeline for managing the animation
	    PauseTransition pause = new PauseTransition(Duration.millis(50)); // Pause between shakes

	    // Create the shake animation in steps
	    for (int i = 0; i < shakeSteps; i++) {
	        int step = i;

	        KeyFrame shakeKeyFrame = new KeyFrame(Duration.millis(i * 50), event -> {
	            // Move the text field left or right alternately based on the step number
	            if (step % 2 == 0) {
	                passwordInput.setLayoutX(passwordInput.getLayoutX() - shakeDistance);
	            } else {
	                passwordInput.setLayoutX(passwordInput.getLayoutX() + shakeDistance);
	            }
	        });

	        // Add the current keyframe to the timeline
	        shakeTimeline.getKeyFrames().add(shakeKeyFrame);
	    }

	    // Play the timeline to execute the shaking effect
	    shakeTimeline.play();
	}

	/**
	 * Generates the Parent Subscreen method where you can implement whatever you
	 * like.
	 */
	private void parentSubScreen() {
		parentSubScreen = new BlueSubSceneBox();
		mainPane.getChildren().add(parentSubScreen);
		// Add methods like

		PermissionLabel playTimeLimitsLabel = new PermissionLabel("Set PlayTime Limit");
		PermissionLabel totalPlayTime = new PermissionLabel("Total Play Time:");

		playTimeLimitsLabel.setPickOnBounds(false);
		totalPlayTime.setPickOnBounds(false);
		playTimeLimitsLabel.setLayoutX(0);
		playTimeLimitsLabel.setLayoutY(-170);
		totalPlayTime.setLayoutX(0);
		totalPlayTime.setLayoutY(-120);

		TextField playTimeLimitInput = new TextField();
		playTimeLimitInput.setLayoutY(17);
		playTimeLimitInput.setLayoutX(250);
		playTimeLimitInput.setText(String.valueOf(playtime_limit));

		RedButton playTimeLimitButton = new RedButton("Save");
		playTimeLimitButton.setLayoutY(330);
		playTimeLimitButton.setLayoutX(380);
		playTimeLimitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {
					if (Integer.parseInt(playTimeLimitInput.getText()) > 0) {
						playtime_limit = Integer.parseInt(playTimeLimitInput.getText());
					}

					// Breaks out the subscene
					showSubScene(settings);
				} catch (NumberFormatException e) {
					shakeInputBox(playTimeLimitInput);
				}

			}

		});

		RedButton revive = new RedButton("Revive");
		revive.setLayoutY(270);
		revive.setLayoutX(380);
		revive.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {

					int csvLoadLine = animalLoadStats.get(12);
					List<CharacterStats> statList = new ArrayList<>();

					CharacterStats character = new CharacterStats(chosenAnimal.name(), animalLoadStats.get(0), 100, 100, 100,
							100, animalLoadStats.get(5), animalLoadStats.get(6),
							animalLoadStats.get(7), animalLoadStats.get(8), animalLoadStats.get(9), 0, 0, csvLoadLine);

					statList.add(character);
					try {
						CharacterStatsApp.modifyLine(filePath, csvLoadLine, statList);
						shakeInputBox(revive);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("here");
						e.printStackTrace();
					}

				} catch (Exception e) {

				}

			}

		});

		parentSubScreen.getPane().getChildren().add(playTimeLimitsLabel);
		parentSubScreen.getPane().getChildren().add(totalPlayTime);
		parentSubScreen.getPane().getChildren().add(playTimeLimitInput);
		parentSubScreen.getPane().getChildren().add(playTimeLimitButton);
		parentSubScreen.getPane().getChildren().add(createLoadAnimalToChoose());
		parentSubScreen.getPane().getChildren().add(revive);

		showSubScene(parentSubScreen);
	}

	/**
	 * Shake input box.
	 *
	 * @param passwordInput the password input
	 */
	private void shakeInputBox(RedButton passwordInput) {

		int shakeSteps = 10;
		double shakeDistance = 4;
		Timeline shakeTimeline = new Timeline();
		PauseTransition pause = new PauseTransition(Duration.millis(50));

		for (int i = 0; i < shakeSteps; i++) {
			int step = i;

			KeyFrame shakeKeyFrame = new KeyFrame(Duration.millis(i * 50), event -> {

				if (step % 2 == 0) {
					passwordInput.setLayoutY(passwordInput.getLayoutY() - shakeDistance);
				} else {
					passwordInput.setLayoutY(passwordInput.getLayoutY() + shakeDistance);
				}
			});

			shakeTimeline.getKeyFrames().add(shakeKeyFrame);

		}
		shakeTimeline.play();
	}

	/**
	 * This is the subscene in new game where you choose your animal.
	 */
	private void createAnimalChooseSubScene() {
		chooseAnimalSubScene = new BlueSubSceneBox();
		mainPane.getChildren().add(chooseAnimalSubScene);

		// Choose animal LABEL BOTTOM
		AnimalLabel chooseAnimalLabel = new AnimalLabel("Choose Animal");
		chooseAnimalLabel.setLayoutX(180);
		chooseAnimalLabel.setLayoutY(160);
		chooseAnimalSubScene.getPane().getChildren().add(chooseAnimalLabel);

		// Animals to choose add to plane
		chooseAnimalSubScene.getPane().getChildren().add(createAnimalToChoose());
		// Start button
		chooseAnimalSubScene.getPane().getChildren().add(createStartButton());

	}

	/**
	 * Returns a stage from the main menu.
	 * 
	 * @return Stage
	 */
	public Stage getMainStage() {
		return mainStage;
	}

	/**
	 * This generates buttons for the main Screen then places them 100 pixels apart
	 * from each other.
	 *
	 * @param button the button
	 */

	private void addMenuButton(RedButton button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		// 100 because it spaces them 100 pixels away from each other
		button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * BUTTON_SPACING);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}

	/**
	 * Adds the icon.
	 */
	private void addIcon() {
		Image icon = new Image("view/resources/icon.png");
		mainStage.getIcons().add(icon);
	}

	/**
	 * Adds the title.
	 */
	private void addTitle() {
		mainStage.setTitle("Main Menu");
	}

	/**
	 * Method for Create New Game Button.
	 */
	private void createNewGameButton() {
		RedButton newGameButton = new RedButton("New Game");
		addMenuButton(newGameButton);
		newGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(chooseAnimalSubScene);
			}
		});
	}

	/**
	 * Method for Create Load Game Button.
	 */
	private void createLoadGameButton() {
		RedButton loadGameButton = new RedButton("Load Game");
		addMenuButton(loadGameButton);
		loadGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(loadGame);
			}
		});
	}

	/**
	 * Creates the "Tutorial" button for the main menu.
	 * When clicked, it opens a subscene that provides instructions 
	 * on how to play the game.
	 */
	private void createTutorialButton() {
	    // Create the "Tutorial" button
	    RedButton tutorialButton = new RedButton("Tutorial");
	    addMenuButton(tutorialButton); // Add the button to the main menu

	    // Set the action to be performed when the button is clicked
	    tutorialButton.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	            // Display the tutorial subscene
	            showSubScene(tutorialSubScene);
	        }
	    });
	}

	/**
	 * Creates the "Settings" button for the main menu.
	 * When clicked, it opens a subscene that allows the user 
	 * to access and configure parental controls and volume settings.
	 */
	private void createSettingsButton() {
	    // Create the "Settings" button
	    RedButton settingsButton = new RedButton("Settings");
	    addMenuButton(settingsButton); // Add the button to the main menu

	    // Set the action to be performed when the button is clicked
	    settingsButton.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent arg0) {
	            // Display the settings subscene
	            showSubScene(settings);
	        }
	    });
	}


	/**
	 * Creates the "Exit" button for the main menu. 
	 * When clicked, it displays a confirmation dialog to the user, 
	 * allowing them to save and exit the game.
	 */
	private void createExitButton() {
	    // Create the "Exit" button
	    RedButton exitButton = new RedButton("Exit");
	    addMenuButton(exitButton); // Add the button to the main menu

	    // Set the action to be performed when the button is clicked
	    exitButton.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent arg0) {
	            // Create a confirmation alert dialog
	            Alert alert = new Alert(AlertType.CONFIRMATION);
	            alert.setTitle("Exit Game");
	            alert.setHeaderText("Would you like to leave and save the game?");

	            // Perform the exit operation if the user confirms
	            if (alert.showAndWait().get() == ButtonType.OK) {
	                // Placeholder for saving game functionality
	                // ==================================
	                // Add ways to save the game file here
	                // ==================================

	                // Close the main stage
	                mainStage.close();
	            }
	        }
	    });
	}

	/**
	 * Creates a "Credits" button for the main menu. 
	 * When clicked, it opens a pop-up dialog displaying information 
	 * about the project team members and their contributions.
	 */
	private void createCreditsButton() {
	    // Create the "Credits" button
	    RedButton creditsButton = new RedButton("Credits");
	    addMenuButton(creditsButton); // Add the button to the main menu

	    // Set the action to be performed when the button is clicked
	    creditsButton.setOnAction(event -> {
	        // Create an information alert dialog for credits
	        Alert creditsAlert = new Alert(Alert.AlertType.INFORMATION);
	        creditsAlert.setTitle("Credits");
	        creditsAlert.setHeaderText("Meet the Team");
	        creditsAlert.setContentText(
	            "This project was created by Group 37 for the CS2212 Final Project in the Fall Semester of 2024 at Western University!\n\n" +
	            "Team Developers:\n" +
	            "1. Keito Hyde\n" +
	            "2. Mahip Varlani\n" +
	            "3. Shivansh Kushwaha\n" +
	            "4. Grant Von Hagen\n" +
	            "5. Maya Al Hourani"
	        );

	        // Display the credits alert
	        creditsAlert.showAndWait();
	    });
	}



	/**
	 * Creates the main menu title with a drop shadow effect. 
	 * The title has a fixed position and changes appearance when hovered over.
	 * Adds the title to the main pane of the main menu.
	 */
	private void createTitle() {
	    // Create an image view for the title with a predefined resource
	    ImageView title = new ImageView("view/resources/main_menu_title.png");

	    // Set the position of the title on the screen
	    title.setLayoutX(MENU_TITLE_X);
	    title.setLayoutY(MENU_TITLE_Y);

	    // Add a drop shadow effect when the mouse hovers over the title
	    title.setOnMouseEntered(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent arg0) {
	            title.setEffect(new DropShadow());
	        }
	    });

	    // Remove the drop shadow effect when the mouse exits
	    title.setOnMouseExited(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent arg0) {
	            title.setEffect(null);
	        }
	    });

	    // Add the title to the main menu pane
	    mainPane.getChildren().add(title);
	}

	/**
	 * Sets the background image for the main menu. 
	 * The background image repeats to fill the screen.
	 * Provides the ability to use a custom background via `BackgroundImage`.
	 */
	private void createBackgroundColor() {
	    // Create an image for the background using the predefined resource
	    Image image = new Image(BACKGROUND_IMAGE, 128, 86, false, true);

	    // Configure the background image to repeat and center
	    BackgroundImage background = new BackgroundImage(
	        image, 
	        BackgroundRepeat.REPEAT, 
	        BackgroundRepeat.REPEAT, 
	        BackgroundPosition.CENTER, 
	        null
	    );

	    // Set the background for the main pane
	    mainPane.setBackground(new Background(background));
	}


}