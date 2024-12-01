/**
 * This is the main view manager for the actual game scene, 
 * deals with all components like Inventory, Buttons, 
 * Item collison, UI, etc.
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
import java.util.Random;

import data.CharacterStats;
import data.CharacterStatsApp;
import data.FoodItem;
import data.GiftItem;
import data.Pet;
import data.PetState;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ANIMAL;
import model.AnimalLabel;
import model.BlueSubSceneBox;
import model.FOODS;
import data.FoodItem;
import data.Item;
import model.MusicPlayer;
import model.RedButton;
import model.ScoreLabel;
import model.animalPicker;
import model.foodPicker;
import model.InventoryButton;
import data.Game;
import javafx.application.Platform;



// TODO: Auto-generated Javadoc
/**
 * The Class GameViewManager.
 */
public class GameViewManager {

	/** The game pane. */
	private AnchorPane gamePane;
	
	/** The game scene. */
	private Scene gameScene;
	
	/** The game stage. */
	private Stage gameStage;
	
	/** The inventory stage. */
	private Stage inventoryStage;

	/** The Constant GAME_WIDTH. */
	private static final int GAME_WIDTH = 1000;
	
	/** The Constant GAME_HEIGHT. */
	private static final int GAME_HEIGHT = 1000;

	/** The menu stage. */
	private Stage menuStage;
	
	/** The animal image. */
	private ImageView animalImage;

	/** The gridpane 1. */
	// Background
	private GridPane gridpane1;
	
	/** The back ground. */
	private String BACK_GROUND = "view/resources/choose_background.png";

	/** The is W pressed. */
	private boolean isWPressed;
	
	/** The is A pressed. */
	private boolean isAPressed;
	
	/** The is S pressed. */
	private boolean isSPressed;
	
	/** The is D pressed. */
	private boolean isDPressed;

	/** The game timer. */
	private AnimationTimer gameTimer;

	/** The Constant SPAWN_POS_X. */
	private final static int SPAWN_POS_X = GAME_WIDTH / 2;
	
	/** The Constant SPAWN_POS_Y. */
	private final static int SPAWN_POS_Y = GAME_HEIGHT / 2;

	/** The coin count. */
	// coins
	private int COIN_COUNT = 10; // --> Add this to the Setings

	/** The Constant COIN1. */
	private final static String COIN1 = "view/resources/coins/coin1.png";
	
	/** The Constant COIN2. */
	private final static String COIN2 = "view/resources/coins/coin2.png";
	
	/** The Constant COIN3. */
	private final static String COIN3 = "view/resources/coins/coin3.png";
	
	/** The Constant COIN4. */
	private final static String COIN4 = "view/resources/coins/coin4.png";
	
	/** The Constant COIN5. */
	private final static String COIN5 = "view/resources/coins/coin5.png";

	/** The Constant APPLE. */
	// Food
	private final static String APPLE = "view/resources/Food/apple.png";
	
	/** The Constant HK1. */
	private final static String HK1 = "view/resources/Food/hk1.png";
	
	/** The Constant HK2. */
	private final static String HK2 = "view/resources/Food/hk2.png";
	
	/** The Constant SUSHI1. */
	private final static String SUSHI1 = "view/resources/Food/sushi1.png";
	
	/** The Constant SUSHI2. */
	private final static String SUSHI2 = "view/resources/Food/sushi2.png";

	/** The Constant BED. */
	// Bar Icons
	private final static String BED = "model/resources/bar/bed.png";
	
	/** The Constant HEART. */
	private final static String HEART = "model/resources/bar/heart.png";
	
	/** The Constant MEAT. */
	private final static String MEAT = "model/resources/bar/meat.png";
	
	/** The Constant SMILEY_FACE. */
	private final static String SMILEY_FACE = "model/resources/bar/smiley_face.png";

	/** The animal load list. */
	private List<animalPicker> animalLoadList;
	
	/** The animal load stats. */
	private List<Integer> animalLoadStats;

	/** The coins stack pane. */
	private StackPane coinsStackPane;
	
	/** The coins. */
	private ImageView[] coins;
	
	/** The foods. */
	private ImageView[] foods;
	
	/** The food list. */
	private List<foodPicker> foodList;

	/** The player life. */
	private ImageView[] playerLife;
	
	/** The score label. */
	private ScoreLabel scoreLabel;
	
	/** The Score. */
	private Integer Score; // Holds Player Score
	
	/** The Health. */
	private int Health; // Holds Player Health

	/** The stats list. */
	private List<CharacterStats> statsList;

	/** The Constant filePath. */
	private final static String filePath = "src/data/saves/stats.csv";

	// COLLISION LOGIC
	/** The Constant COIN_RADIUS. */
	// PLayer
	private final static int COIN_RADIUS = 12;
	
	/** The Constant PLAYER_RADIUS. */
	private final static int PLAYER_RADIUS = 25;
	
	/** The Constant FOOD_RADIUS. */
	private final static int FOOD_RADIUS = 12;

	/** The random position generator. */
	Random randomPositionGenerator;
	
	/** The inventory button. */
	InventoryButton inventoryButton; // Inventory button

	/** The health bar. */
	// Progress bars
	private ProgressBar healthBar;
	
	/** The hungerbar. */
	private ProgressBar hungerbar;
	
	/** The happinessbar. */
	private ProgressBar happinessbar;
	
	/** The sleep bar. */
	private ProgressBar sleepBar;

	/** The escape sub scene. */
	// Escape Menu SubScene
	private BlueSubSceneBox escapeSubScene;
	
	/** The save sub screne. */
	private BlueSubSceneBox saveSubScrene;
	
	/** The escape menu buttons. */
	private List<RedButton> escapeMenuButtons;
	
	/** The csv load line. */
	private Integer csvLoadLine;

	/** The food selected. */
	private FOODS foodSelected; // This is for when he selects a food item to use to feed
	
	/** The chosen pet. */
	private Pet chosenPet;
	
	/** The chosen animal. */
	private ANIMAL chosenAnimal;
	
	/** The load chosen animal. */
	private ANIMAL loadChosenAnimal;

	/** The music. */
	// Music player
	MusicPlayer music;

	/** The full screen. */
	private boolean fullScreen; // Implement this in settings <----
	
	/** The music volume. */
	private double music_volume;

	/** The current sub scene. */
	// Current Subscene (so they dont overlap)
	private BlueSubSceneBox currentSubScene;

	/** The game. */
	private Game game;

	/** The last warning time. */
	// Add a cooldown for warnings to prevent spam
	private long lastWarningTime = 0;
	
	/** The Constant WARNING_COOLDOWN. */
	private static final long WARNING_COOLDOWN = 5000; // 5 seconds cooldown

	/** The is game over. */
	private boolean isGameOver = false; // Add this field at the class level
	
	/** The stats update timeline. */
	private Timeline statsUpdateTimeline; // Add this as a class field

	/** The coin respawn timeline. */
	private Timeline coinRespawnTimeline; // Add this field at the class level
	
	/** The next coin to respawn. */
	private int nextCoinToRespawn = 0; // Add this field to track which coin to respawn next

	/** Score System Constants - Collecting coins: +10 points - Feeding pet: +20 points - Playing with pet: +15 points - Pet happiness above 80: +5 points every minute - Pet getting sick (health below 20): -30 points - Pet stats critical (any stat below 20): -10 points. */
	private static final int SCORE_COIN_COLLECT = 10;
	
	/** The Constant SCORE_FEEDING. */
	private static final int SCORE_FEEDING = 20;
	
	/** The Constant SCORE_PLAYING. */
	private static final int SCORE_PLAYING = 15;
	
	/** The Constant SCORE_HAPPY_BONUS. */
	private static final int SCORE_HAPPY_BONUS = 5;
	
	/** The Constant SCORE_SICK_PENALTY. */
	private static final int SCORE_SICK_PENALTY = -30;
	
	/** The Constant SCORE_CRITICAL_PENALTY. */
	private static final int SCORE_CRITICAL_PENALTY = -10;

	/**
	 * Instantiates a new game view manager.
	 */
	public GameViewManager() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);

		this.fullScreen = false;
		gameStage.setResizable(false);

		randomPositionGenerator = new Random();

		// WASD listener
		createKeyListener();
	}

	/**
	 * The Loader of the game, takes the menuStage, holds it, and then loads the
	 * game stage. Using chosen animal as param. probably need to implement score
	 * and other things as param later on. 
	 *
	 * @param menuStage    MenuStage
	 * @param chosenAnimal ChosenSprite
	 * @param chosenPet the chosen pet
	 * @param music_volume the music volume
	 * @param playtime_limit the playtime limit
	 */
	public void createNewGame(Stage menuStage, ANIMAL chosenAnimal, Pet chosenPet, int music_volume, int playtime_limit) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		this.chosenAnimal = chosenAnimal;
		this.chosenPet = chosenPet;
		this.music_volume = (double) music_volume / 100;
		

		if (playtime_limit > 0) {
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(playtime_limit), e -> {

				// probably save the game or something
				System.out.println("Your play time is over");
				gameStage.hide();
				music.stopMusic();
				menuStage.show();
			}));
			timeline.setCycleCount(1);
			timeline.play();
		}

		gameBackGround();
		createItems();
		createAnimal(chosenAnimal);
		loadGame(10);
		createGameLoop();
		createEscapeMenu();
		createSaveSubScrene();
		gamePane.getChildren().add(feedButton());
		gamePane.getChildren().add(playButton());
		gamePane.getChildren().add(sleepButton());
		gamePane.getChildren().add(visitVetButton());
		inventoryButton();
		gamePane.getChildren().add(escapeSubScene);
		gamePane.getChildren().add(saveSubScrene);

		// FullScreen Settings
		if (this.fullScreen) {
			gameStage.setFullScreen(this.fullScreen);
			gameStage.setFullScreenExitHint("Press q to exit fullscreen");
			gameStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
		}

		gameStage.show();

		game = new Game();
		// Show tutorial when starting new game
		game.tutorial(""); // The fileName parameter isn't used currently

		startStatusUpdateTimer();
	}

	/**
	 * Loadnew game.
	 *
	 * @param menuStage the menu stage
	 * @param chosenAnimal the chosen animal
	 * @param chosenPet the chosen pet
	 * @param music_volume the music volume
	 * @param playtime_limit the playtime limit
	 * @param list the list
	 */
	public void loadnewGame(Stage menuStage, ANIMAL chosenAnimal, Pet chosenPet, int music_volume, int playtime_limit,
			ArrayList<Integer> list) {
		// list = [score, health, hunger, happiness, sleepiness, Food1, Food2, Food3,
		// Food4, Food5, 0, 0, DEAD = 0 /ALIVE = 1]
		this.menuStage = menuStage;
		this.menuStage.hide();
		this.chosenAnimal = chosenAnimal;
		this.chosenPet = chosenPet;
		this.music_volume = (double) music_volume / 100;

		if (playtime_limit > 0) {
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(playtime_limit), e -> {

				// probably save the game or something
				// Save Game Here
				System.out.println("Your play time is over");
				gameStage.hide();
				music.stopMusic();
				menuStage.show();
			}));
			timeline.setCycleCount(1);
			timeline.play();
		}

		gameBackGround();
		createItems();
		createAnimal(chosenAnimal);
		loadGame(list.get(0));
		createGameLoop();
		createEscapeMenu();
		createSaveSubScrene();
		gamePane.getChildren().add(feedButton());
		gamePane.getChildren().add(playButton());
		gamePane.getChildren().add(sleepButton());
		gamePane.getChildren().add(visitVetButton());
		inventoryButton();
		gamePane.getChildren().add(escapeSubScene);
		gamePane.getChildren().add(saveSubScrene);

		// FullScreen Settings
		if (this.fullScreen) {
			gameStage.setFullScreen(this.fullScreen);
			gameStage.setFullScreenExitHint("Press q to exit fullscreen");
			gameStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
		}

		chosenPet.setHealth(list.get(1));
		chosenPet.setSleep(list.get(2));
		chosenPet.setHunger(list.get(3));
		chosenPet.setHappiness(list.get(4));
		foodList.get(0).getFood().setAmount(list.get(5));
		foodList.get(1).getFood().setAmount(list.get(6));
		foodList.get(2).getFood().setAmount(list.get(7));
		foodList.get(3).getFood().setAmount(list.get(8));
		foodList.get(4).getFood().setAmount(list.get(9));
		foodList.get(0).updateAmount();
		foodList.get(1).updateAmount();
		foodList.get(2).updateAmount();
		foodList.get(3).updateAmount();
		foodList.get(4).updateAmount();

		// SUDHF UIEHS FS
		// --------------------------------------------------------------------------

		if (list.get(12) == 0) {
			chosenPet.setGameOver(false);
		} else {
			chosenPet.setGameOver(true);
		}

		gameStage.show();

		game = new Game();
		// Show tutorial when starting new game
		game.tutorial(""); // The fileName parameter isn't used currently

		startStatusUpdateTimer();

	}

	/**
	 * Load game.
	 *
	 * @param score the score
	 */
	public void loadGame(Integer score) {

		music = new MusicPlayer();
		try {
			music.start(gameStage);
			music.setVolume(music_volume);
		} catch (Exception e) {
			// Just print a warning and continue without music
			// System.out.println("Warning: Could not load music file. Continuing without
			// music.");
			e.printStackTrace();
		}

		loadInterface(score);

	}

	/**
	 * Feed button.
	 *
	 * @return the red button
	 */
	private RedButton feedButton() {
		RedButton button = new RedButton("Feed");
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				showSubSceneGame(inventoryButton.getSubScene());

			}

		});

		button.setLayoutY(GAME_HEIGHT - 50);
		button.setLayoutX(GAME_WIDTH / 2 - 380);
		return button;

	}

	/**
	 * Play button.
	 *
	 * @return the red button
	 */
	private RedButton playButton() {
		RedButton button = new RedButton("Play");
		button.setLayoutY(GAME_HEIGHT - 50);
		button.setLayoutX(GAME_WIDTH / 2 - 190);

		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				playWithPet();
				updateProgressBars();
			}
		});

		return button;
	}

	/**
	 * Sleep button.
	 *
	 * @return the red button
	 */
	private RedButton sleepButton() {
		RedButton button = new RedButton("Sleep");
		button.setLayoutY(GAME_HEIGHT - 50);
		button.setLayoutX(GAME_WIDTH / 2);

		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				chosenPet.sleep();
				updateProgressBars();
			}
		});

		return button;
	}

	/**
	 * Visit vet button.
	 *
	 * @return the red button
	 */
	private RedButton visitVetButton() {
		RedButton button = new RedButton("Visit Vet");
		button.setLayoutY(GAME_HEIGHT - 50);
		button.setLayoutX(GAME_WIDTH / 2 + 190);

		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if (chosenPet != null) {
					// Heal the pet but decrease happiness
					chosenPet.setHealth(100);
					chosenPet.setHappiness(Math.max(0, chosenPet.getHappiness() - 20)); // Decrease happiness by 20

					// Update the UI
					updateProgressBars();

					// Show feedback
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Vet Visit");
					alert.setHeaderText(null);
					alert.setContentText(
							"Your pet has been healed to full health, but they didn't enjoy the vet visit!");
					alert.showAndWait();
				}
			}
		});

		return button;
	}

	/**
	 * Inventory button.
	 *
	 * @return the inventory button
	 */
	private InventoryButton inventoryButton() {
		inventoryButton = new InventoryButton(gamePane);

		inventoryButton.getSubScene().getPane().getChildren().add(createFoodToChoose());
		inventoryButton.setLayoutX(GAME_HEIGHT - 100);
		inventoryButton.setLayoutY(10);

		RedButton Use = new RedButton("Use");
		Use.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			// This is the use functionality in the inventory
			public void handle(MouseEvent arg0) {

				if (foodSelected != null) {
					if (foodSelected.getAmount() > 0) {
						foodSelected.decrementAmount();
						for (int i = 0; i < foodList.size(); i++) {
							foodList.get(i).updateAmount();
						}
						chosenPet.feed(foodSelected.getFoodItem());
						hungerbar.setProgress((double) chosenPet.getHunger() / 100);
					}

				}
			}

		});

		Use.setLayoutX(400);
		Use.setLayoutY(340);
		inventoryButton.getSubScene().getPane().getChildren().add(Use);

		return inventoryButton;

	}

	/**
	 * Creates the food to choose.
	 *
	 * @return the h box
	 */
	private HBox createFoodToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		foodList = new ArrayList<>();
		for (FOODS food : FOODS.values()) {
			foodPicker foodToPick = new foodPicker(food);

			foodToPick.getFoodImage().setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					foodToPick.setEffect(new DropShadow());

				}

			});
			foodToPick.getFoodImage().setOnMouseExited(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					foodToPick.setEffect(null);

				}

			});
			foodList.add(foodToPick);
			box.getChildren().add(foodToPick);
			foodToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					for (foodPicker food : foodList) {
						food.setFoodSelected(false);
					}
					foodToPick.setFoodSelected(true);
					foodSelected = foodToPick.getFood();
				}

			});

		}

		box.setLayoutX(20);
		box.setLayoutY(20);

		return box;
	}

	/**
	 * Adds Buttons to the escape menu subscene.
	 *
	 * @param button the button
	 */
	private void addEscapeMenuButton(RedButton button) {
		button.setLayoutX(200);
		button.setLayoutY(25 + escapeMenuButtons.size() * 100);
		escapeMenuButtons.add(button);
		escapeSubScene.getPane().getChildren().add(button);
	}

	/**
	 * Initializes the EscapeMenu Subscene.
	 */
	private void createEscapeMenu() {
		escapeSubScene = new BlueSubSceneBox();
		escapeMenuButtons = new ArrayList<>();
		createResumeButton();
		createSaveButton();
		// createSettingsButton();
		createExitGameButton();
	}

	/**
	 * Creates the exit game button.
	 */
	private void createExitGameButton() {
		// TODO Auto-generated method stub
		RedButton button = new RedButton("Exit Game");
		addEscapeMenuButton(button);
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				gameStage.close();
				gameTimer.stop();
				music.stopMusic();
				menuStage.show();

				// TODO Auto-generated method stub

			}

		});

	}

	/**
	 * Creates the save button.
	 */
	private void createSaveButton() {
		// TODO Auto-generated method stub
		RedButton button = new RedButton("Save");
		addEscapeMenuButton(button);
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				showSubSceneGame(saveSubScrene);

			}

		});
	}

	/**
	 * Creates the save sub screne.
	 */
	private void createSaveSubScrene() {
		saveSubScrene = new BlueSubSceneBox();

		AnimalLabel chooseSaveFile = new AnimalLabel("Choose Save");
		chooseSaveFile.setLayoutX(180);
		chooseSaveFile.setLayoutY(160);
		saveSubScrene.getPane().getChildren().add(chooseSaveFile);

		// Load Game instances
		saveSubScrene.getPane().getChildren().add(createLoadAnimalToChoose());

		// Start Button
		saveSubScrene.getPane().getChildren().add(createLoadStartButton());

	}

	/**
	 * Creates the load start button.
	 *
	 * @return the h box
	 */
	private HBox createLoadStartButton() {
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
						csvLoadLine = stats.getValue14(); // Returns the line in the csv where it is located

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
	 * Creates the load animal to choose.
	 *
	 * @return the red button
	 */
	private RedButton createLoadAnimalToChoose() {
		RedButton startButton = new RedButton("Save");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);

		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				int temp = 0;
				if (chosenPet.isGameOver()) {
					temp = 1;
				} else {
					temp = 0;
				}

				List<CharacterStats> statList = new ArrayList<>();

				CharacterStats character = new CharacterStats(chosenPet.getName(), (int) Score, chosenPet.getHealth(),
						chosenPet.getHunger(), chosenPet.getHappiness(), chosenPet.getSleep(),
						foodList.get(0).getFood().getAmount(), foodList.get(1).getFood().getAmount(),
						foodList.get(2).getFood().getAmount(), foodList.get(3).getFood().getAmount(),
						foodList.get(4).getFood().getAmount(), 0, temp, csvLoadLine);

				statList.add(character);

				try {
					CharacterStatsApp.modifyLine(filePath, csvLoadLine, statList);
					shakeInputBox(startButton);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("here");
					e.printStackTrace();
				}

			}

		});

		return startButton;
	}

	/**
	 * Literally just shakes a RedButton you input.
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
	 * Creates the settings button.
	 */
	private void createSettingsButton() {
		// TODO Auto-generated method stub
		RedButton button = new RedButton("Settings");
		addEscapeMenuButton(button);
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				// TODO Auto-generated method stub

			}

		});
	}

	/**
	 * Creates the resume button.
	 */
	private void createResumeButton() {
		// TODO Auto-generated method stub
		RedButton button = new RedButton("Resume");
		addEscapeMenuButton(button);
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				displayEscapeMenu();

			}

		});
	}

	/**
	 * Display escape menu.
	 */
	private void displayEscapeMenu() {
		showSubSceneGame(escapeSubScene);
	}

	/**
	 * Creates the key listener.
	 */
	private void createKeyListener() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.W) {
					isWPressed = true;
				} else if (event.getCode() == KeyCode.A) {
					isAPressed = true;
				} else if (event.getCode() == KeyCode.S) {
					isSPressed = true;
				} else if (event.getCode() == KeyCode.D) {
					isDPressed = true;
				} else if (event.getCode() == KeyCode.ESCAPE) {
					displayEscapeMenu();
					// SubScene Escape Menu here IMplementation <-----
				}

			}
		});
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.W) {
					isWPressed = false;
				} else if (event.getCode() == KeyCode.A) {
					isAPressed = false;
				} else if (event.getCode() == KeyCode.S) {
					isSPressed = false;
				} else if (event.getCode() == KeyCode.D) {
					isDPressed = false;
				}
			}
		});
	}

	/**
	 * Spawn the sprite into the world.
	 *
	 * @param chosenAnimal Your Character
	 */
	private void createAnimal(ANIMAL chosenAnimal) {
		animalImage = new ImageView(chosenAnimal.getURL());

		// Spawn Position
		animalImage.setLayoutX(SPAWN_POS_X);
		animalImage.setLayoutY(SPAWN_POS_Y);

		gamePane.getChildren().add(animalImage);

	}

	/**
	 * The game loop.
	 */
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {

				checkIfElementsCollide();
				moveAnimal();

			}

		};
		gameTimer.start();
	}

	/**
	 * GameBackGround Setter Method.
	 */
	private void gameBackGround() {
		gridpane1 = new GridPane();

		// Background Duplicator

		ImageView backgroundimage1 = new ImageView(BACK_GROUND);
		backgroundimage1.setFitHeight(GAME_HEIGHT);
		backgroundimage1.setPreserveRatio(true);
		GridPane.setConstraints(backgroundimage1, 3, 3);
		gridpane1.getChildren().add(backgroundimage1);

		gridpane1.setLayoutY(0);

		gamePane.getChildren().add(gridpane1);

	}

	/**
	 * Show sub scene game.
	 *
	 * @param subScene the sub scene
	 */
	public void showSubSceneGame(BlueSubSceneBox subScene) {
		if (subScene == currentSubScene) {
			currentSubScene.moveSubScene();
			currentSubScene = null;
			return;
		} else if (currentSubScene != null) {

			currentSubScene.moveSubScene();
		}
		subScene.moveSubScene();
		currentSubScene = subScene;
	}

	/**
	 * Key Controller for moving sprite around.
	 */
	private void moveAnimal() {
		// Teleport if going out of bounds
		animalImage.setLayoutY(animalImage.getLayoutY() % GAME_HEIGHT);
		animalImage.setLayoutX(animalImage.getLayoutX() % GAME_WIDTH);

		if (animalImage.getLayoutY() < 0) {
			animalImage.setLayoutY(GAME_HEIGHT - 1);
		}
		if (animalImage.getLayoutX() < 0) {
			animalImage.setLayoutX(GAME_HEIGHT - 1);
		}

		// Main Function

		if (isWPressed && !isSPressed) {
			animalImage.setLayoutY(animalImage.getLayoutY() - 1);
		}
		// Backwards
		if (isSPressed && !isWPressed) {
			animalImage.setLayoutY(animalImage.getLayoutY() + 1);
		}
		// Left
		if (isAPressed && !isDPressed) {
			animalImage.setLayoutX(animalImage.getLayoutX() - 1);
		}
		// Right
		if (isDPressed && !isAPressed) {
			animalImage.setLayoutX(animalImage.getLayoutX() + 1);
		}
	}

	/**
	 * Creates multiple instances of coins, call them as many times as you want
	 * coins in the game.
	 *
	 * @return Instance of Coin
	 */
	private ImageView CoinCreator() {
		// Coin Animation
		coinsStackPane = new StackPane();
		Image coin1 = new Image(COIN1);
		Image coin2 = new Image(COIN2);
		Image coin3 = new Image(COIN3);
		Image coin4 = new Image(COIN4);
		Image coin5 = new Image(COIN5);

		ImageView coinInstance = new ImageView(coin1);

		coinsStackPane.getChildren().add(coinInstance);

		setNewElementPosition(coinInstance);

		// This is just animation of coins
		Timeline animateCoin = new Timeline(new KeyFrame(Duration.seconds(0.2), e -> {

			coinInstance.setImage(coin1);
			coinInstance.setLayoutX(coinInstance.getLayoutX() + 1);
			coinInstance.setLayoutY(coinInstance.getLayoutY() + 1);

		}), new KeyFrame(Duration.seconds(0.4), e -> {

			coinInstance.setImage(coin2);
			coinInstance.setLayoutX(coinInstance.getLayoutX() - 1);
			coinInstance.setLayoutY(coinInstance.getLayoutY() - 1);

		}), new KeyFrame(Duration.seconds(0.6), e -> {

			coinInstance.setImage(coin3);
			coinInstance.setLayoutX(coinInstance.getLayoutX() + 1);
			coinInstance.setLayoutY(coinInstance.getLayoutY() + 1);

		}), new KeyFrame(Duration.seconds(0.8), e -> {

			coinInstance.setImage(coin4);
			coinInstance.setLayoutX(coinInstance.getLayoutX() - 1);
			coinInstance.setLayoutY(coinInstance.getLayoutY() - 1);

		}), new KeyFrame(Duration.seconds(1), e -> {

			coinInstance.setImage(coin5);
			coinInstance.setLayoutX(coinInstance.getLayoutX() + 1);
			coinInstance.setLayoutY(coinInstance.getLayoutY() + 1);

		}), new KeyFrame(Duration.seconds(1.2), e -> {

			coinInstance.setImage(coin1);
			coinInstance.setLayoutX(coinInstance.getLayoutX() - 1);
			coinInstance.setLayoutY(coinInstance.getLayoutY() - 1);

		}));

		animateCoin.setCycleCount(Timeline.INDEFINITE);

		animateCoin.play();

		return coinInstance;
	}

	/**
	 * Initializes all the item instances.
	 */

	private void createItems() {
		// Initialize foods array
		foods = new ImageView[5]; // Assuming you have 5 food types
		foods[0] = new ImageView(APPLE);
		foods[1] = new ImageView(HK1);
		foods[2] = new ImageView(HK2);
		foods[3] = new ImageView(SUSHI1);
		foods[4] = new ImageView(SUSHI2);

		// Set size for all food items
		for (ImageView food : foods) {
			food.setFitWidth(25);
			food.setFitHeight(25);
			setNewElementPosition(food);
		}

		// Create coins
		coins = new ImageView[COIN_COUNT];
		Score = 0; // Initialize score to 0

		// Coin Collection scoring
		for (int i = 0; i < COIN_COUNT; i++) {
			ImageView temp = CoinCreator();
			coins[i] = temp;

			temp.setOnMouseClicked(event -> {
				if (temp.isVisible()) {
					updateScore(SCORE_COIN_COLLECT, "Coin collected!");
					temp.setVisible(false);
				}
			});

			gamePane.getChildren().add(temp);
		}

		// Start coin respawn timeline
		startCoinRespawnTimer();

		// Add all food items to the game pane
		for (ImageView food : foods) {
			gamePane.getChildren().add(food);
		}
	}

	/**
	 * Randomizes position of items on the map.
	 *
	 * @param image Item to Randomize Position
	 */
	private void setNewElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(0, GAME_WIDTH));
		image.setLayoutY(randomPositionGenerator.nextInt(0, GAME_HEIGHT));
	}

	// COLLISION LOGIC

	/**
	 * Calculate distance.
	 *
	 * @param x1 the x 1
	 * @param x2 the x 2
	 * @param y1 the y 1
	 * @param y2 the y 2
	 * @return the double
	 */
	private double calculateDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	/**
	 * Removes the life.
	 */
	private void removeLife() {

		gamePane.getChildren().remove(playerLife[playerLife.length - 1]);
		Health--;
		if (Health <= 0) {
			// =========================
			// Implement way to save the game here

			// =========================
			gameStage.close();
			gameTimer.stop();
			music.stopMusic();
			menuStage.show();
		}
	}

	/**
	 * Uses radius of sprites to calculate whether they have collided, if collision
	 * happend, values are changed and object is moved to a new spot.
	 */
	private void checkIfElementsCollide() {

		// Player Food Collision
		for (int i = 0; i < foods.length; i++) {
			if (PLAYER_RADIUS + FOOD_RADIUS > calculateDistance(animalImage.getLayoutX() + 20,
					foods[i].getLayoutX() + 20, animalImage.getLayoutY() + 20, foods[i].getLayoutY() + 20)) {
				foodList.get(i).getFood().incrementAmount();
				foodList.get(i).updateAmount();
				setNewElementPosition(foods[i]);
				Score++;
				scoreLabel.setText("Points : " + Score.toString());
			}
		}

		// Remove or comment out the coin collision check since we're using clicks now

		for (int i = 0; i < coins.length; i++) {
			if (PLAYER_RADIUS + COIN_RADIUS > calculateDistance(animalImage.getLayoutX() + 20,
					coins[i].getLayoutX() + 20, animalImage.getLayoutY() + 20, coins[i].getLayoutY() + 20)) {
				// Move the Item if contact with player
				setNewElementPosition(coins[i]);

				// Change this to increase hunger <----------------------------------------
				Score += 10;
				// making it so when you run over coins it slowly dies
				chosenPet.setHealth(Math.max(0, chosenPet.getHealth() - 10));
				scoreLabel.setText("Points : " + Score.toString());
			}
		}

	}

	/**
	 * This Builds the interface and loads all the graphics.
	 *
	 * @param score Score of the player.
	 */
	private void loadInterface(Integer score) {
		Score = score;
		scoreLabel = new ScoreLabel("Points : " + score.toString());
		scoreLabel.setLayoutX(0);
		scoreLabel.setLayoutY(0);
		gamePane.getChildren().add(scoreLabel);

		healthBar = new ProgressBar();
		hungerbar = new ProgressBar();
		happinessbar = new ProgressBar();
		sleepBar = new ProgressBar();

		healthBar.setLayoutX(20);
		healthBar.setLayoutY(50);
		hungerbar.setLayoutX(20);
		hungerbar.setLayoutY(70);
		happinessbar.setLayoutX(20);
		happinessbar.setLayoutY(90);
		sleepBar.setLayoutX(20);
		sleepBar.setLayoutY(110);

		healthBar.setProgress((double) chosenPet.getHealth());
		hungerbar.setProgress((double) chosenPet.getHunger() / 100);
		happinessbar.setProgress((double) chosenPet.getHappiness() / 100);
		sleepBar.setProgress((double) chosenPet.getSleep() / 100);

		healthBar.setStyle("-fx-accent: #4CAF50;");
		hungerbar.setStyle("-fx-accent: #FF9800;");
		happinessbar.setStyle("-fx-accent: #FFEB3B;");
		sleepBar.setStyle("-fx-accent: #2196F3;");

		ImageView healthIcon = new ImageView(HEART);
		ImageView hungerIcon = new ImageView(MEAT);
		ImageView happinessIcon = new ImageView(SMILEY_FACE);
		ImageView sleepIcon = new ImageView(BED);

		healthIcon.setLayoutX(healthBar.getLayoutX() - 20);
		hungerIcon.setLayoutX(hungerbar.getLayoutX() - 20);
		happinessIcon.setLayoutX(happinessbar.getLayoutX() - 20);
		sleepIcon.setLayoutX(sleepBar.getLayoutX() - 20);

		healthIcon.setLayoutY(healthBar.getLayoutY());
		hungerIcon.setLayoutY(hungerbar.getLayoutY());
		happinessIcon.setLayoutY(happinessbar.getLayoutY());
		sleepIcon.setLayoutY(sleepBar.getLayoutY());

		gamePane.getChildren().add(healthIcon);
		gamePane.getChildren().add(hungerIcon);
		gamePane.getChildren().add(happinessIcon);
		gamePane.getChildren().add(sleepIcon);

		gamePane.getChildren().add(healthBar);
		gamePane.getChildren().add(hungerbar);
		gamePane.getChildren().add(happinessbar);
		gamePane.getChildren().add(sleepBar);
	}

	/**
	 * Update progress bars.
	 */
	// Add this method to update all progress bars
	private void updateProgressBars() {
		if (chosenPet != null) {
			// Debug print
			// System.out.println("Updating progress bars...");

			healthBar.setProgress((double) chosenPet.getHealth() / 100);
			hungerbar.setProgress((double) chosenPet.getHunger() / 100);
			happinessbar.setProgress((double) chosenPet.getHappiness() / 100);
			sleepBar.setProgress((double) chosenPet.getSleep() / 100);
		}
	}

	/**
	 * Start status update timer.
	 */
	// Modify startStatusUpdateTimer to run more frequently
	private void startStatusUpdateTimer() {
		if (statsUpdateTimeline != null) {
			statsUpdateTimeline.stop();
		}

		statsUpdateTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
			if (chosenPet != null && !isGameOver) {
				// Update pet status
				chosenPet.updateStatus();
				updateProgressBars();

				// Explicitly check dangerous levels
				checkDangerousLevels();

				// Debug print to verify the method is being called
				/*
				 * System.out.println("Pet status - Health: " + chosenPet.getHealth() +
				 * ", Hunger: " + chosenPet.getHunger() + ", Sleep: " + chosenPet.getSleep() +
				 * ", Happiness: " + chosenPet.getHappiness() + ", State: " +
				 * chosenPet.getCurrentState());
				 */
			}
		}));
		statsUpdateTimeline.setCycleCount(Timeline.INDEFINITE);
		statsUpdateTimeline.play();
	}

	/**
	 * Check dangerous levels.
	 */
	// Modify the checkDangerousLevels method
	private void checkDangerousLevels() {
		if (chosenPet == null || isGameOver) {
			System.out.println("Check skipped: pet is null or game is over");
			return;
		}

		// Debug print
		// System.out.println("Checking dangerous levels...");
		// System.out.println("Current state: " + chosenPet.getCurrentState());
		// System.out.println("Time since last warning: " + (System.currentTimeMillis()
		// - lastWarningTime) + "ms");

		// Death check
		if (chosenPet.isGameOver() || chosenPet.getCurrentState() == PetState.DEAD) {
			// System.out.println("Death condition detected!");
			if (!isGameOver) {
				handleGameOver();
			}
			return;
		}

		// Warning check with cooldown
		if (System.currentTimeMillis() - lastWarningTime >= WARNING_COOLDOWN) {
			boolean needsWarning = false;
			StringBuilder message = new StringBuilder();

			PetState state = chosenPet.getCurrentState();

			// Check each critical condition
			if (chosenPet.getHunger() <= 20) {
				message.append("Your pet is very hungry! Please feed them!\n");
				needsWarning = true;
			}
			if (chosenPet.getSleep() <= 20) {
				message.append("Your pet is very tired! Let them sleep!\n");
				needsWarning = true;
			}
			if (chosenPet.getHappiness() <= 20) {
				message.append("Your pet is very unhappy! Play with them!\n");
				needsWarning = true;
			}
			if (chosenPet.getHealth() <= 20) {
				message.append("Your pet's health is critical! Take care of their needs!\n");
				needsWarning = true;
			}

			if (needsWarning) {
				// System.out.println("Showing warning: " + message.toString());
				lastWarningTime = System.currentTimeMillis();
				final String warningMessage = message.toString();

				Platform.runLater(() -> {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Pet Needs Attention!");
					alert.setHeaderText("Warning!");
					alert.setContentText(warningMessage);
					alert.show();
				});
			}
		}
	}

	/**
	 * Handle game over.
	 */
	private void handleGameOver() {
		isGameOver = true;

		// Stop all timers
		if (gameTimer != null)
			gameTimer.stop();
		if (statsUpdateTimeline != null)
			statsUpdateTimeline.stop();
		if (coinRespawnTimeline != null)
			coinRespawnTimeline.stop();

		Platform.runLater(() -> {
			List<CharacterStats> statList = new ArrayList<>();

			CharacterStats character = new CharacterStats(chosenPet.getName(), (int) Score, chosenPet.getHealth(),
					chosenPet.getHunger(), chosenPet.getHappiness(), chosenPet.getSleep(),
					foodList.get(0).getFood().getAmount(), foodList.get(1).getFood().getAmount(),
					foodList.get(2).getFood().getAmount(), foodList.get(3).getFood().getAmount(),
					foodList.get(4).getFood().getAmount(), 0, 1, 7);

			statList.add(character);

			try {
				CharacterStatsApp.modifyLine(filePath, 7, statList);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("here");
				e.printStackTrace();
			}

			Alert gameOver = new Alert(AlertType.ERROR);
			gameOver.setTitle("Game Over");
			gameOver.setHeaderText("Your pet has died!");
			gameOver.setContentText("Please take better care of your next pet!");
			gameOver.setOnHidden(evt -> {
				gameStage.close();
				menuStage.show();
			});

			gameOver.show();
		});
	}

	/**
	 * Start coin respawn timer.
	 */
	private void startCoinRespawnTimer() {
		if (coinRespawnTimeline != null) {
			coinRespawnTimeline.stop();
		}

		coinRespawnTimeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
			if (!isGameOver) {
				respawnNextCoin();
			}
		}));
		coinRespawnTimeline.setCycleCount(Timeline.INDEFINITE);
		coinRespawnTimeline.play();
	}

	/**
	 * Respawn next coin.
	 */
	private void respawnNextCoin() {
		// Find the next invisible coin
		int coinsChecked = 0;
		while (coinsChecked < COIN_COUNT) {
			if (!coins[nextCoinToRespawn].isVisible()) {
				// Respawn this coin
				setNewElementPosition(coins[nextCoinToRespawn]);
				coins[nextCoinToRespawn].setVisible(true);

				// Move to next coin index
				nextCoinToRespawn = (nextCoinToRespawn + 1) % COIN_COUNT;
				return;
			}
			nextCoinToRespawn = (nextCoinToRespawn + 1) % COIN_COUNT;
			coinsChecked++;
		}
	}

	/**
	 * Updates the score without displaying pop-up messages.
	 *
	 * @param points Points to add (or subtract if negative)
	 * @param reason Message to display (unused, kept for debugging)
	 */
	private void updateScore(int points, String reason) {
		Score += points;
		scoreLabel.setText("Points : " + Score.toString());
	}

	/**
	 * Feed pet.
	 *
	 * @param food the food
	 */
	// Add this to your pet feeding method
	public void feedPet(FoodItem food) {
		if (chosenPet != null && !chosenPet.isGameOver()) {
			chosenPet.feed(food);
			updateScore(SCORE_FEEDING, "Fed pet with " + food.getName() + "!");
		}
	}

	/**
	 * Play with pet.
	 */
	// Add this to your pet playing method
	public void playWithPet() {
		if (chosenPet != null && !chosenPet.isGameOver()) {
			chosenPet.playWithPet(); // Using the correct method from your Pet class
			updateScore(SCORE_PLAYING, "Played with pet!");
		}
	}

	/**
	 * Save score.
	 */
	// Add this to save the score when game ends
	private void saveScore() {
		// You can implement score saving to a file or database here
		try {
			// Example: Save to a file
			String scoreData = "Player Score: " + Score + "\nDate: " + java.time.LocalDateTime.now();
			// Add code to save scoreData to a file
		} catch (Exception e) {
			// System.out.println("Error saving score: " + e.getMessage());
		}
	}

	/**
	 * Convert to food item.
	 *
	 * @param food the food
	 * @return the food item
	 */
	private FoodItem convertToFoodItem(FOODS food) {
		switch (food) {
		case APPLE:
			return new FoodItem("Apple", 0.3); // 30% hunger increase
		case SUSHI1:
		case SUSHI2:
			return new FoodItem("Sushi", 0.4); // 40% hunger increase
		case HK1:
		case HK2:
			return new FoodItem("HK", 0.5); // 50% hunger increase
		default:
			return new FoodItem("Basic Food", 0.2); // 20% hunger increase
		}
	}

	/**
	 * Feed pet with enum.
	 *
	 * @param foodType the food type
	 */
	public void feedPetWithEnum(FOODS foodType) {
		FoodItem food = convertToFoodItem(foodType);
		feedPet(food);
	}

}
