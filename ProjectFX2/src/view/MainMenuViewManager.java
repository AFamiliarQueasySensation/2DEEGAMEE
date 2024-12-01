	/**
	 * MainMenuViewManager manages the main menu scene, including creating buttons, subscenes,
	 * and handling user interactions for the pet game. Also manages Loading and saving game states, and 
	 * extra settings for Character like revival.
	 * 
	 * @author Grant Von Hagen
	 * @Author Mahip Tulsi Varlani
	 * @Author Shivansh Kushwaha
	 * @email gvonhage@uwo.ca
	 * @email mvarlani@uwo.ca
	 * @email skushwah@uwo.ca
	 * @version 1.0
	 */
package view;

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

// TODO: Auto-generated Javadoc
/**
 * The Class MainMenuViewManager.
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
	private final static String filePath = "src/data/saves/stats.csv";

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
	 * Instantiates a new main menu view manager.
	 */
	public MainMenuViewManager() {
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
	 * Creates the buttons.
	 */
	/*
	 * Initialize all the buttons
	 */
	private void createButtons() {
		createNewGameButton();
		createLoadGameButton();
		createTutorialButton();
		createSettingsButton();
		createExitButton();

	}

	/**
	 * Creates the pet.
	 */
	private void createPet() {
		chosenPet = new Pet(chosenAnimal.name());

	}

	/**
	 * Creates the default settings.
	 */
	private void createDefaultSettings() {
		this.music_volume = 55;
		this.playtime_limit = 0;
	}

	/**
	 * Literally just moves the current subscrene out of the way, and shows the new
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
	 * Creates the load animal to choose.
	 *
	 * @return the h box
	 */
	private HBox createLoadAnimalToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		animalLoadList = new ArrayList<>();
		animalLoadStats = new ArrayList<>(Collections.nCopies(15, 0));

		try {
			List<CharacterStats> loadedStats = CharacterStatsApp.readFromCSV(filePath);
			loadedStats = CharacterStatsApp.readFromCSV(filePath);
			for (CharacterStats stats : loadedStats) {
				ANIMAL matchedAnimal = ANIMAL.compareStringToEnum(stats.getValue1());
				if (matchedAnimal == null)
					continue;
				animalPicker animalToPick = new animalPicker(matchedAnimal);

				// Drop Shadow on Character choice image
				animalToPick.getAnimalImage().setOnMouseEntered(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						animalToPick.setEffect(new DropShadow());

					}
				});
				animalToPick.getAnimalImage().setOnMouseExited(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						animalToPick.setEffect(null);
					}
				});

				animalLoadList.add(animalToPick); // Causes no duplicates in choices
				box.getChildren().add(animalToPick); // this displays the images
				animalToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						for (animalPicker animal : animalLoadList) {
							animal.setAnimalTaken(false);
						}
						animalToPick.setAnimalTaken(true);
						chosenAnimal = animalToPick.getAnimal();
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

			e.printStackTrace();
		}

		box.setLayoutX(300 - (118 * 2));
		box.setLayoutY(100);

		return box;

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
	 * Creates the Load Screne SubScene.
	 */
	private void createLoadSubScrene() {
		loadGame = new BlueSubSceneBox();
		mainPane.getChildren().add(loadGame);

		AnimalLabel chooseSaveFile = new AnimalLabel("Choose Save");
		chooseSaveFile.setLayoutX(180);
		chooseSaveFile.setLayoutY(160);
		loadGame.getPane().getChildren().add(chooseSaveFile);

		// Load Game instances
		loadGame.getPane().getChildren().add(createLoadAnimalToChoose());

		// Start Button
		loadGame.getPane().getChildren().add(createLoadStartButton());

	}

	/**
	 * Creates the Tutorial Subscrene.
	 */
	private void createTutorialSubScrene() {

		tutorialSubScene = new BlueSubSceneBox();
		mainPane.getChildren().add(tutorialSubScene);

		// Add tutorial content
		PermissionLabel tutorialTitle = new PermissionLabel("How to Play");
		tutorialTitle.setLayoutX(180);
		tutorialTitle.setLayoutY(-175);

		PermissionLabel instructions = new PermissionLabel("\n" + "\n" + "\n" + "\n" + "\n" + "Welcome to PetPals!\n"
				+ "1. Choose your pet from the available animals\n" + "2. Feed your pet to maintain their hunger\n"
				+ "3. Play with your pet to keep them happy\n" + "4. Clean up after your pet regularly\n"
				+ "5. Make sure your pet gets enough rest\n\n" + "Keep all stats above 0 to keep your pet healthy!");
		instructions.setLayoutX(20);
		instructions.setLayoutY(-100);

		tutorialSubScene.getPane().getChildren().addAll(tutorialTitle, instructions);
	}

	/**
	 * Main Menu Settings SubScene.
	 */
	private void createAnimalSettingSubScene() {
		settings = new BlueSubSceneBox();
		settings.setPickOnBounds(false);
		mainPane.getChildren().add(settings);

		PermissionLabel volumeLabel = new PermissionLabel("Volume");
		volumeLabel.setLayoutX(-15);
		volumeLabel.setLayoutY(-135);
		settings.getPane().getChildren().add(volumeLabel);

		PermissionLabel settingsLabel = new PermissionLabel("Settings");
		settingsLabel.setLayoutX(220);
		settingsLabel.setLayoutY(-175);
		settings.getPane().getChildren().add(settingsLabel);

		// Parent Password Field
		PermissionLabel permissionLabel = new PermissionLabel("Input Password");
		permissionLabel.setLayoutX(180);
		permissionLabel.setLayoutY(-25);
		settings.getPane().getChildren().add(permissionLabel);

		TextField passwordInput = new TextField();
		passwordInput.setLayoutX(175);
		passwordInput.setLayoutY(200);
		passwordInput.setPrefSize(250, 40);
		settings.getPane().getChildren().add(passwordInput);

		RedButton submitButton = new RedButton("Enter");
		submitButton.setLayoutX(204);
		submitButton.setLayoutY(280);
		settings.getPane().getChildren().add(submitButton);

		// Volume Slider Field
		Slider volumeSlider = new Slider(-15, 105, 50);
		volumeSlider.setMajorTickUnit(10);
		volumeSlider.setMinorTickCount(5);
		volumeSlider.setSnapToTicks(true);
		volumeSlider.setLayoutX(120);
		volumeSlider.setLayoutY(60);

		settings.getPane().getChildren().add(volumeSlider);

		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (passwordInput.getText().equals(DEFAULT_PASSWORD)) {
					parentSubScreen();

				} else {
					shakeInputBox(passwordInput);
				}
			}
		});

		volumeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				music_volume = (int) volumeSlider.getValue();
				volumeSlider.adjustValue(volumeSlider.getValue() + 5);

			}

		});

	}

	/**
	 * Shakes the text field, used in wrong password input.
	 *
	 * @param passwordInput TextField you want to shake
	 */
	private void shakeInputBox(TextField passwordInput) {

		int shakeSteps = 10;
		double shakeDistance = 4;
		Timeline shakeTimeline = new Timeline();
		PauseTransition pause = new PauseTransition(Duration.millis(50));

		for (int i = 0; i < shakeSteps; i++) {
			int step = i;

			KeyFrame shakeKeyFrame = new KeyFrame(Duration.millis(i * 50), event -> {

				if (step % 2 == 0) {
					passwordInput.setLayoutX(passwordInput.getLayoutX() - shakeDistance);
				} else {
					passwordInput.setLayoutX(passwordInput.getLayoutX() + shakeDistance);
				}
			});

			shakeTimeline.getKeyFrames().add(shakeKeyFrame);

		}
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

					CharacterStats character = new CharacterStats(chosenAnimal.name(), 100, 100, 100, 100,
							animalLoadStats.get(4), animalLoadStats.get(5), animalLoadStats.get(6),
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
	 * Method for Create Tutorial Button.
	 */
	private void createTutorialButton() {
		RedButton tutorialButton = new RedButton("Tutorial");
		addMenuButton(tutorialButton);

		tutorialButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(tutorialSubScene);
			}
		});
	}

	/**
	 * Method for Create Settings Button.
	 */
	private void createSettingsButton() {
		RedButton settingsButton = new RedButton("Settings");
		addMenuButton(settingsButton);
		settingsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				showSubScene(settings);
			}
		});
	}

	/**
	 * Method for Create Exit Button.
	 */
	private void createExitButton() {
		RedButton exitButton = new RedButton("Exit");
		addMenuButton(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Exit Game");
				alert.setHeaderText("Would you like to leave and save the game?");
				if (alert.showAndWait().get() == ButtonType.OK) {

					// ==================================Add ways to save the game file HERE
					// <--------------
					mainStage.close();
				}
			}
		});
	}

	/**
	 * ==== OPTIONAL METHOD === Create a button that will display credits for all
	 * the members in our group.
	 * 
	 */
	private void createCreditsButton() {
		RedButton settingsButton = new RedButton("Credits");
		addMenuButton(settingsButton);

	}

	/**
	 * This is just the main menu title, has a drop shadow, positioning, and nothing
	 * much else.
	 */
	private void createTitle() {
		ImageView title = new ImageView("view/resources/main_menu_title.png");
		title.setLayoutX(MENU_TITLE_X);
		title.setLayoutY(MENU_TITLE_Y);

		// Drop Shadow the title
		title.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				title.setEffect(new DropShadow());
			}
		});
		title.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				title.setEffect(null);
			}
		});
		mainPane.getChildren().add(title);
	}

	/**
	 * Sets the background color in Main Menu If you want you can create a dedicated
	 * background using Imagem, then BackgroundImage.
	 */
	private void createBackgroundColor() {
		// mainPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,
		// null, null)));
		Image image = new Image(BACKGROUND_IMAGE, 128, 86, false, true);
		BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.CENTER, null);
		mainPane.setBackground(new Background(background));
	}

}
