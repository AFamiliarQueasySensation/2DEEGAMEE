
package view;

import java.io.File;
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



/**
 * The GameViewManager class is responsible for managing the core game mechanics and the game view.
 * This includes loading game assets, handling animations, updating player interactions,
 * and managing the game state.
 *
 * <p>Main responsibilities include:</p>
 * <ul>
 *   <li>Loading game backgrounds, objects, and player assets.</li>
 *   <li>Managing player and object interactions such as feeding, playing, and scoring.</li>
 *   <li>Handling progress bars for health, hunger, happiness, and sleep.</li>
 *   <li>Creating and displaying the game loop.</li>
 *   <li>Handling game pause/resume and saving game states.</li>
 * </ul>
 *
 * @author Grant Von Hogen, Shivansh Kushwaha, Mahip Varlani, Maya Al Hourani, Keito Hyde
 * @version 2.0
 * @since 2024
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
	private File  filePath;

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
	
	private long playCooldownEndTime = 0; // Tracks when the cooldown ends for "Play"
	private long vetCooldownEndTime = 0;  // Tracks when the cooldown ends for "Visit Vet"


	/**
	 * Constructs a new {@link GameViewManager} instance, initializing the core components required
	 * for the game environment and setting up the necessary configurations for gameplay.
	 *
	 * <p>The constructor performs the following operations:
	 * <ul>
	 *     <li>Initializes the game pane ({@link AnchorPane}) to serve as the root container for all game elements.</li>
	 *     <li>Sets up the game scene with predefined width and height, associating it with the game pane.</li>
	 *     <li>Creates and configures the game stage, disabling resizing and setting the game scene as its content.</li>
	 *     <li>Initializes the random position generator for dynamic placement of game objects.</li>
	 *     <li>Sets up a key listener for handling player input using WASD keys for movement.</li>
	 * </ul>
	 *
	 * <p>By default:
	 * <ul>
	 *     <li>The game starts in a non-fullscreen mode.</li>
	 *     <li>The game stage is configured to a fixed size to ensure a consistent user experience.</li>
	 * </ul>
	 *
	 * @see AnchorPane
	 * @see Scene
	 * @see Stage
	 */
	public GameViewManager() {
		
		
		String dir = System.getProperty("user.dir");

		File csvFile = new File(dir, "resources/stats.csv");

        filePath = csvFile;
		
        
		
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
	 * Creating a method for Loading newly started game.
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
	 * creating method for Loading game. it will load previous score and load music.
	 *
	 * @param score , which is the score for user. 
	 * @throws Exception e when music file could not be loaded
	 */
	public void loadGame(Integer score) {

		music = new MusicPlayer();
		try {
			music.start(gameStage);
			music.setVolume(music_volume);
			
			
			// Just print a warning and continue without music
						// System.out.println("Warning: Could not load music file. Continuing without
						// music.");
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		loadInterface(score);

	}

	/**
	 * Creating an in game button for Feed/Items.
	 * will be used for using in game items collected by pet to restore health and happiness
	 *
	 * @return the red button
	 */
	private RedButton feedButton() {
		RedButton button = new RedButton("Items");
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
	 * Creating a Play button for the pet. User will be able to use the play button to play with pet
	 * to boost the happiness of the pet , while reducing rest.
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
	            long currentTime = System.currentTimeMillis();
	            if (currentTime < playCooldownEndTime) {
	                // Show cooldown message
	                Alert alert = new Alert(AlertType.INFORMATION);
	                alert.setTitle("Cooldown Active");
	                alert.setHeaderText(null);
	                alert.setContentText("Wait for cooldown before you can play with your pet again!");
	                alert.showAndWait();
	            } else {
	                // Perform the action and set the cooldown
	                playWithPet();
	                updateProgressBars();
	                playCooldownEndTime = currentTime + 20000; // 20 seconds
	            }
	        }
	    });

	    return button;
	}


	/**
	 * Creating the Sleep button for the pet.
	 * User will be able to use the sleep button to make the pet have some rest.
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
	 * Creating Visit Vet button for the pet.
	 * User will be able to take the pet to the vet with this feature, which will increase the health of the pet.
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
	            long currentTime = System.currentTimeMillis();
	            if (currentTime < vetCooldownEndTime) {
	                // Show cooldown message
	                Alert alert = new Alert(AlertType.INFORMATION);
	                alert.setTitle("Cooldown Active");
	                alert.setHeaderText(null);
	                alert.setContentText("Wait for cooldown before you can visit the vet again!");
	                alert.showAndWait();
	            } else {
	                // Perform the action and set the cooldown
	                if (chosenPet != null) {
	                    chosenPet.setHealth(100);
	                    chosenPet.setHappiness(Math.max(0, chosenPet.getHappiness() - 20));
	                    updateProgressBars();

	                    // Feedback message
	                    Alert alert = new Alert(AlertType.INFORMATION);
	                    alert.setTitle("Vet Visit");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Your pet has been healed to full health, but they didn't enjoy the vet visit!");
	                    alert.showAndWait();
	                }
	                vetCooldownEndTime = currentTime + 20000; // 20 seconds
	            }
	        }
	    });

	    return button;
	}


	/**
	 * Creating the Inventory button for pet. 
	 * User can use this button to manage food and gift items for the pet!
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
	 * Creates an HBox containing food options that the player can select from.
	 * Each food option is represented as an image that highlights on mouse hover and can be selected via click.
	 *
	 * @return an {@link HBox} containing food picker elements for the inventory.
	 */
	private HBox createFoodToChoose() {
		HBox box = new HBox(); // Set spacing between food items in the HBox for better UI layout.
		box.setSpacing(20);
		foodList = new ArrayList<>();
		for (FOODS food : FOODS.values()) { // Loop through all available food types and create corresponding foodPicker elements.
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
			foodToPick.setOnMouseClicked(new EventHandler<MouseEvent>() { // Set up a click event to select the clicked food and deselect all others.

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

		box.setLayoutX(20); // Set the layout position of the HBox on the game pane.
		box.setLayoutY(20); 

		return box; // Return the HBox containing all food pickers.
	}

	/**
	 * Adds a button to the escape menu subscene. Buttons are dynamically positioned
	 * based on the number of existing buttons in the menu.
	 *
	 * @param button the {@link RedButton} to be added to the escape menu.
	 */
	private void addEscapeMenuButton(RedButton button) {
		button.setLayoutX(200);
		button.setLayoutY(25 + escapeMenuButtons.size() * 100);
		escapeMenuButtons.add(button);
		escapeSubScene.getPane().getChildren().add(button);
	}

	/**
	 * Initializes the escape menu subscene and its associated buttons, such as Resume, Save, and Exit Game.
	 * This menu allows the player to manage game settings and navigate away from the game screen.
	 */
	private void createEscapeMenu() {
		escapeSubScene = new BlueSubSceneBox();
		escapeMenuButtons = new ArrayList<>();
		createResumeButton();
		createSaveButton();
		createExitGameButton();
	}

	/**
	 * Creates and adds the "Exit Game" button to the escape menu. When clicked, this button
	 * stops the game timer, saves game progress, and returns to the main menu.
	 */

	private void createExitGameButton() {

		RedButton button = new RedButton("Exit Game");
		addEscapeMenuButton(button);
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				gameStage.close(); // Close the game window.
				gameTimer.stop(); // Stop the game loop timer
				music.stopMusic();
				menuStage.show(); // Return to the main menu stage.



			}

		});

	}

	/**
	 * Creates and adds the "Save" button to the escape menu. When clicked, this button opens
	 * the save subscene, allowing the player to save the current game state.
	 */

	private void createSaveButton() {
		// TODO Auto-generated method stub
		RedButton button = new RedButton("Save");
		addEscapeMenuButton(button);
		button.setOnAction(new EventHandler<ActionEvent>() { // Define the action to perform when the button is clicked.

			@Override
			public void handle(ActionEvent arg0) {
				showSubSceneGame(saveSubScrene); // Display the save subscene for saving game progress.

			}

		});
	}

	/**
	 * Creates the save sub screen.
	 * here we create it so that user can save the game into the slots provided.
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
	 * User can access the saved games from slots they have saved in
	 *
	 * @return box
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
	 * Creating method to choose which pet to load into.
	 * user has 4 options to load into whichever pet they want.
	 * @return startButton
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
					//System.out.println("here");
					e.printStackTrace();
				}

			}

		});

		return startButton;
	}

	/**
	 * Creates a method to just shake input boxes when clicked
	 * User can see this when they click on a button ingame.
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
	 * Creating the settings button for the game.
	 * User can use this settings button to configure settings of the game
	 */
	private void createSettingsButton() {
		
		RedButton button = new RedButton("Settings"); 
		addEscapeMenuButton(button);
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				

			}

		});
	}

	/**
	 * Creating the resume button for the game.
	 * User can use this resume button to resume the game
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
	 * Creating a method to display the escape menu.
	 * It is displayed when a user hits esc from the keyboard.
	 */
	private void displayEscapeMenu() {
		showSubSceneGame(escapeSubScene);
	}

	/**
	 * Initializes key listeners to define keybinds for the game.
	 * Users can use WASD for movement and shortcut commands for various game features.
	 * Shortcut commands:
	 * - V: Visit Vet
	 * - P: Play with Pet
	 * - Y: Put Pet to Sleep
	 * - I: Open Inventory
	 * - ESC: Open Escape Menu
	 */
	private void createKeyListener() {
	    gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            KeyCode keyCode = event.getCode();
	            if (keyCode == KeyCode.W) {
	                isWPressed = true;
	            } else if (keyCode == KeyCode.A) {
	                isAPressed = true;
	            } else if (keyCode == KeyCode.S) {
	                isSPressed = true;
	            } else if (keyCode == KeyCode.D) {
	                isDPressed = true;
	            } else if (keyCode == KeyCode.ESCAPE) {
	                displayEscapeMenu();
	            } else if (keyCode == KeyCode.V) {
	                handleVisitVetShortcut();
	            } else if (keyCode == KeyCode.P) {
	                handlePlayShortcut();
	            } else if (keyCode == KeyCode.Y) {
	                handleSleepShortcut();
	            } else if (keyCode == KeyCode.I) {
	                handleInventoryShortcut();
	            }
	        }
	    });

	    gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            KeyCode keyCode = event.getCode();
	            if (keyCode == KeyCode.W) {
	                isWPressed = false;
	            } else if (keyCode == KeyCode.A) {
	                isAPressed = false;
	            } else if (keyCode == KeyCode.S) {
	                isSPressed = false;
	            } else if (keyCode == KeyCode.D) {
	                isDPressed = false;
	            }
	        }
	    });
	}

	/**
	 * Handles the shortcut for visiting the vet.
	 * If the vet action is not on cooldown, executes the "Visit Vet" logic.
	 * Otherwise, displays a cooldown alert.
	 */
	private void handleVisitVetShortcut() {
	    if (chosenPet != null) {
	        long currentTime = System.currentTimeMillis();
	        if (currentTime >= vetCooldownEndTime) {
	            visitVetButton(); // Calls the visit vet logic
	            startVetCooldown(); // Start cooldown timer
	        } else {
	            showCooldownAlert("Visit Vet");
	        }
	    }
	}



	/**
	 * Handles the shortcut for playing with the pet.
	 * If the play action is not on cooldown, executes the "Play with Pet" logic.
	 * Otherwise, displays a cooldown alert.
	 */
	private void handlePlayShortcut() {
	    if (chosenPet != null) {
	        long currentTime = System.currentTimeMillis();
	        if (currentTime >= playCooldownEndTime) {
	            playWithPet(); // Calls the play logic
	            updateProgressBars(); // Updates progress bars after playing
	            startPlayCooldown(); // Start the cooldown timer for play
	        } else {
	            showCooldownAlert("Play"); // Notify the user about the cooldown
	        }
	    }
	}

	/**
	 * Handles the shortcut for putting the pet to sleep.
	 * Executes the "Sleep" logic and updates the pet's progress bars.
	 */
	private void handleSleepShortcut() {
	    if (chosenPet != null) {
	        chosenPet.sleep(); // Puts the pet to sleep
	        updateProgressBars(); // Updates progress bars after sleeping
	    }
	}

	/**
	 * Handles the shortcut for opening the inventory.
	 * Displays the inventory subscene.
	 */
	private void handleInventoryShortcut() {
	    showSubSceneGame(inventoryButton.getSubScene()); // Opens the inventory subscene
	}

	/**
	 * Starts the cooldown for the play action.
	 * Sets a cooldown period of 20 seconds.
	 */
	private void startPlayCooldown() {
	    playCooldownEndTime = System.currentTimeMillis() + 5000; // 20 seconds cooldown
	}



	/**
	 * Starts the cooldown for the "Visit Vet" action.
	 * Sets a cooldown period of 20 seconds during which the action cannot be executed again.
	 */
	private void startVetCooldown() {
	    vetCooldownEndTime = System.currentTimeMillis() + 5000; // 20 seconds cooldown
	}

	/**
	 * Displays an alert to the user when an action is on cooldown.
	 *
	 * @param action the name of the action that is currently on cooldown.
	 *               This will be displayed in the alert message.
	 */
	private void showCooldownAlert(String action) {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Cooldown Alert"); // Sets the title of the alert
	    alert.setHeaderText(null); // No header for the alert
	    alert.setContentText("Wait for the cooldown before you can use \"" + action + "\" again!"); // Custom cooldown message
	    alert.showAndWait(); // Displays the alert and waits for the user's acknowledgment
	}


	
	




	/**
	 * Spawn the pet sprite into the world.
	 * Here user can choose what animal to choose as the character.
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
	 * Initializes and starts the game loop.
	 * The game loop continuously updates the game by checking for collisions and moving the animal sprite.
	 * It runs on an {@link AnimationTimer} to ensure smooth gameplay.
	 */
	private void createGameLoop() {
	    gameTimer = new AnimationTimer() {

	        @Override
	        public void handle(long arg0) {
	            checkIfElementsCollide(); // Checks for collisions between game elements
	            moveAnimal(); // Moves the animal based on user input
	        }
	    };
	    gameTimer.start(); // Starts the game loop
	}

	/**
	 * Sets up the background for the game.
	 * Creates a {@link GridPane} and adds the background image to it.
	 * The background image is duplicated and adjusted to fit the game window.
	 */
	private void gameBackGround() {
	    gridpane1 = new GridPane(); // Creates a new grid pane

	    // Creates and configures the background image
	    ImageView backgroundimage1 = new ImageView(BACK_GROUND);
	    backgroundimage1.setFitHeight(GAME_HEIGHT); // Sets the height of the background
	    backgroundimage1.setPreserveRatio(true); // Maintains the aspect ratio of the background image
	    GridPane.setConstraints(backgroundimage1, 3, 3); // Positions the image in the grid pane
	    gridpane1.getChildren().add(backgroundimage1); // Adds the image to the grid pane

	    gridpane1.setLayoutY(0); // Aligns the grid pane to the top of the screen

	    gamePane.getChildren().add(gridpane1); // Adds the grid pane to the game pane
	}


	/**
	 * Displays the specified subscene within the game.
	 * Toggles the visibility of a subscene when called. If the subscene is already visible,
	 * it is moved out of view; otherwise, the current subscene (if any) is hidden, and the specified subscene is displayed.
	 *
	 * @param subScene the {@link BlueSubSceneBox} to be shown.
	 */
	public void showSubSceneGame(BlueSubSceneBox subScene) {
	    if (subScene == currentSubScene) {
	        currentSubScene.moveSubScene(); // Moves the current subscene out of view
	        currentSubScene = null; // Resets the reference to the current subscene
	        return;
	    } else if (currentSubScene != null) {
	        currentSubScene.moveSubScene(); // Moves the currently displayed subscene out of view
	    }
	    subScene.moveSubScene(); // Displays the specified subscene
	    currentSubScene = subScene; // Updates the current subscene reference
	}

	/**
	 * Handles the movement of the animal sprite based on key inputs.
	 * Updates the position of the animal sprite and ensures it wraps around the game boundaries.
	 * Uses WASD keys for movement.
	 */
	private void moveAnimal() {
	    // Wraps the animal's position when it moves out of bounds
	    animalImage.setLayoutY(animalImage.getLayoutY() % GAME_HEIGHT);
	    animalImage.setLayoutX(animalImage.getLayoutX() % GAME_WIDTH);

	    // Corrects negative positions for wrapping
	    if (animalImage.getLayoutY() < 0) {
	        animalImage.setLayoutY(GAME_HEIGHT - 1); // Repositions at the bottom
	    }
	    if (animalImage.getLayoutX() < 0) {
	        animalImage.setLayoutX(GAME_HEIGHT - 1); // Repositions at the right
	    }

	    // Moves the animal up if W is pressed
	    if (isWPressed && !isSPressed) {
	        animalImage.setLayoutY(animalImage.getLayoutY() - 1);
	    }
	    // Moves the animal down if S is pressed
	    if (isSPressed && !isWPressed) {
	        animalImage.setLayoutY(animalImage.getLayoutY() + 1);
	    }
	    // Moves the animal left if A is pressed
	    if (isAPressed && !isDPressed) {
	        animalImage.setLayoutX(animalImage.getLayoutX() - 1);
	    }
	    // Moves the animal right if D is pressed
	    if (isDPressed && !isAPressed) {
	        animalImage.setLayoutX(animalImage.getLayoutX() + 1);
	    }
	}


	/**
	 * Creates and animates a coin instance for the game.
	 * The coin undergoes a continuous animation cycle, switching between predefined
	 * images to simulate a spinning or shimmering effect. It can be positioned and reused
	 * multiple times in the game environment.
	 *
	 * @return an {@link ImageView} instance representing a coin.
	 */
	private ImageView CoinCreator() {
	    // StackPane to hold the coin instance
	    coinsStackPane = new StackPane();

	    // Load the images for the coin animation
	    Image coin1 = new Image(COIN1); // First coin frame
	    Image coin2 = new Image(COIN2); // Second coin frame
	    Image coin3 = new Image(COIN3); // Third coin frame
	    Image coin4 = new Image(COIN4); // Fourth coin frame
	    Image coin5 = new Image(COIN5); // Fifth coin frame

	    // Create an ImageView for the coin using the first image
	    ImageView coinInstance = new ImageView(coin1);

	    // Add the coin instance to the stack pane
	    coinsStackPane.getChildren().add(coinInstance);

	    // Set a new random position for the coin
	    setNewElementPosition(coinInstance);

	    // Define the timeline for the coin animation
	    Timeline animateCoin = new Timeline(
	        // Set the coin to the first image and adjust its position
	        new KeyFrame(Duration.seconds(0.2), e -> {
	            coinInstance.setImage(coin1);
	            coinInstance.setLayoutX(coinInstance.getLayoutX() + 1);
	            coinInstance.setLayoutY(coinInstance.getLayoutY() + 1);
	        }),
	        // Set the coin to the second image and adjust its position
	        new KeyFrame(Duration.seconds(0.4), e -> {
	            coinInstance.setImage(coin2);
	            coinInstance.setLayoutX(coinInstance.getLayoutX() - 1);
	            coinInstance.setLayoutY(coinInstance.getLayoutY() - 1);
	        }),
	        // Set the coin to the third image and adjust its position
	        new KeyFrame(Duration.seconds(0.6), e -> {
	            coinInstance.setImage(coin3);
	            coinInstance.setLayoutX(coinInstance.getLayoutX() + 1);
	            coinInstance.setLayoutY(coinInstance.getLayoutY() + 1);
	        }),
	        // Set the coin to the fourth image and adjust its position
	        new KeyFrame(Duration.seconds(0.8), e -> {
	            coinInstance.setImage(coin4);
	            coinInstance.setLayoutX(coinInstance.getLayoutX() - 1);
	            coinInstance.setLayoutY(coinInstance.getLayoutY() - 1);
	        }),
	        // Set the coin to the fifth image and adjust its position
	        new KeyFrame(Duration.seconds(1), e -> {
	            coinInstance.setImage(coin5);
	            coinInstance.setLayoutX(coinInstance.getLayoutX() + 1);
	            coinInstance.setLayoutY(coinInstance.getLayoutY() + 1);
	        }),
	        // Reset the coin back to the first image
	        new KeyFrame(Duration.seconds(1.2), e -> {
	            coinInstance.setImage(coin1);
	            coinInstance.setLayoutX(coinInstance.getLayoutX() - 1);
	            coinInstance.setLayoutY(coinInstance.getLayoutY() - 1);
	        })
	    );

	    // Set the animation to run indefinitely
	    animateCoin.setCycleCount(Timeline.INDEFINITE);

	    // Start the coin animation
	    animateCoin.play();

	    // Return the created coin instance
	    return coinInstance;
	}


	/**
	 * Initializes all the collectible items in the game.
	 * This includes food items and coins, which are set up with specific attributes and behavior.
	 * The food items are initialized with predefined images and random positions.
	 * Coins are created with click-based scoring functionality and added to the game pane.
	 */
	private void createItems() {
	    // Initialize the foods array with 5 food types
	    foods = new ImageView[5]; // Assuming you have 5 predefined food types
	    foods[0] = new ImageView(APPLE); // Apple food image
	    foods[1] = new ImageView(HK1);   // HK1 food image
	    foods[2] = new ImageView(HK2);   // HK2 food image
	    foods[3] = new ImageView(SUSHI1); // Sushi1 food image
	    foods[4] = new ImageView(SUSHI2); // Sushi2 food image

	    // Set dimensions and randomize positions for all food items
	    for (ImageView food : foods) {
	        food.setFitWidth(25); // Set width of food item
	        food.setFitHeight(25); // Set height of food item
	        setNewElementPosition(food); // Randomize the position of the food item
	    }

	    // Initialize coins array
	    coins = new ImageView[COIN_COUNT];
	    Score = 0; // Initialize the player's score to 0

	    // Create and configure each coin
	    for (int i = 0; i < COIN_COUNT; i++) {
	        ImageView temp = CoinCreator(); // Create a new coin instance
	        coins[i] = temp; // Add the coin to the coins array

	        // Set click behavior for the coin
	        temp.setOnMouseClicked(event -> {
	            if (temp.isVisible()) {
	                updateScore(SCORE_COIN_COLLECT, "Coin collected!"); // Update score when coin is collected
	                temp.setVisible(false); // Hide the coin after collection
	            }
	        });

	        // Add the coin to the game pane
	        gamePane.getChildren().add(temp);
	    }

	    // Start a timeline to respawn coins periodically
	    startCoinRespawnTimer();

	    // Add all food items to the game pane
	    for (ImageView food : foods) {
	        gamePane.getChildren().add(food);
	    }
	}

	/**
	 * Randomizes the position of an item within the game boundaries.
	 * Ensures that items like food and coins are placed at random positions on the map.
	 *
	 * @param image The item whose position needs to be randomized.
	 */
	private void setNewElementPosition(ImageView image) {
	    // Generate a random X-coordinate within the game width
	    image.setLayoutX(randomPositionGenerator.nextInt(0, GAME_WIDTH));

	    // Generate a random Y-coordinate within the game height
	    image.setLayoutY(randomPositionGenerator.nextInt(0, GAME_HEIGHT));
	}


	// COLLISION LOGIC

	/**
	 * Calculates the Euclidean distance between two points in a 2D space.
	 * This is used to determine whether a collision has occurred between objects.
	 *
	 * @param x1 The x-coordinate of the first point.
	 * @param x2 The x-coordinate of the second point.
	 * @param y1 The y-coordinate of the first point.
	 * @param y2 The y-coordinate of the second point.
	 * @return The calculated distance between the two points.
	 */
	private double calculateDistance(double x1, double x2, double y1, double y2) {
	    // Use the distance formula: sqrt((x2 - x1)^2 + (y2 - y1)^2)
	    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	/**
	 * Removes a life from the player's life count when a collision or health loss occurs.
	 * Handles game over logic if the player has no remaining lives.
	 */
	private void removeLife() {
	    // Remove the last life icon from the game pane
	    gamePane.getChildren().remove(playerLife[playerLife.length - 1]);

	    // Decrement the player's health count
	    Health--;

	    // Check if health has reached 0 (game over)
	    if (Health <= 0) {
	        // Placeholder: Save the game state if needed
	        // =========================
	        // Implement way to save the game here
	        // =========================

	        // Close the game stage
	        gameStage.close();

	        // Stop the game timer
	        gameTimer.stop();

	        // Stop any background music
	        music.stopMusic();

	        // Show the main menu stage
	        menuStage.show();
	    }
	}


	/**
	 * Checks for collisions between the player and game elements (food and coins).
	 * Uses the radius of sprites to calculate whether they overlap and handles
	 * the effects of the collision, such as updating scores, repositioning elements,
	 * and modifying the pet's health.
	 */
	private void checkIfElementsCollide() {

	    // Player Food Collision
	    for (int i = 0; i < foods.length; i++) {
	        // Check if the distance between the player and the food is less than the sum of their radii
	        if (PLAYER_RADIUS + FOOD_RADIUS > calculateDistance(
	                animalImage.getLayoutX() + 20, foods[i].getLayoutX() + 20,
	                animalImage.getLayoutY() + 20, foods[i].getLayoutY() + 20)) {

	            // Increment the amount of this type of food in the inventory
	            foodList.get(i).getFood().incrementAmount();

	            // Update the displayed amount of this food type
	            foodList.get(i).updateAmount();

	            // Move the food item to a new random position
	            setNewElementPosition(foods[i]);

	            // Increase the player's score
	            Score++;
	            scoreLabel.setText("Points : " + Score.toString()); // Update the score label
	        }
	    }

	    // Player Coin Collision
	    for (int i = 0; i < coins.length; i++) {
	        // Check if the distance between the player and the coin is less than the sum of their radii
	        if (PLAYER_RADIUS + COIN_RADIUS > calculateDistance(
	                animalImage.getLayoutX() + 20, coins[i].getLayoutX() + 20,
	                animalImage.getLayoutY() + 20, coins[i].getLayoutY() + 20)) {

	            // Move the coin to a new random position
	            setNewElementPosition(coins[i]);

	            // Increment the player's score for collecting the coin
	            Score += 10;

	            // Decrease the pet's health when a coin is collected
	            chosenPet.setHealth(Math.max(0, chosenPet.getHealth() - 10));

	            // Update the score label to reflect the new score
	            scoreLabel.setText("Points : " + Score.toString());
	        }
	    }
	}


	/**
	 * Builds the user interface for the game and loads all necessary graphics, including
	 * progress bars for health, hunger, happiness, and sleep, along with corresponding icons.
	 *
	 * @param score The current score of the player.
	 */
	private void loadInterface(Integer score) {
	    // Initialize the score and set up the score label
	    Score = score;
	    scoreLabel = new ScoreLabel("Points : " + score.toString());
	    scoreLabel.setLayoutX(0); // Set score label X position
	    scoreLabel.setLayoutY(0); // Set score label Y position
	    gamePane.getChildren().add(scoreLabel); // Add score label to the game pane

	    // Initialize progress bars for pet stats
	    healthBar = new ProgressBar();
	    hungerbar = new ProgressBar();
	    happinessbar = new ProgressBar();
	    sleepBar = new ProgressBar();

	    // Set positions for progress bars
	    healthBar.setLayoutX(20);
	    healthBar.setLayoutY(50);
	    hungerbar.setLayoutX(20);
	    hungerbar.setLayoutY(70);
	    happinessbar.setLayoutX(20);
	    happinessbar.setLayoutY(90);
	    sleepBar.setLayoutX(20);
	    sleepBar.setLayoutY(110);

	    // Set progress values for progress bars
	    healthBar.setProgress((double) chosenPet.getHealth() / 100); // Scale health to 0-1
	    hungerbar.setProgress((double) chosenPet.getHunger() / 100); // Scale hunger to 0-1
	    happinessbar.setProgress((double) chosenPet.getHappiness() / 100); // Scale happiness to 0-1
	    sleepBar.setProgress((double) chosenPet.getSleep() / 100); // Scale sleep to 0-1

	    // Style the progress bars with custom colors
	    healthBar.setStyle("-fx-accent: #4CAF50;"); // Green for health
	    hungerbar.setStyle("-fx-accent: #FF9800;"); // Orange for hunger
	    happinessbar.setStyle("-fx-accent: #FFEB3B;"); // Yellow for happiness
	    sleepBar.setStyle("-fx-accent: #2196F3;"); // Blue for sleep

	    // Create icons for progress bars
	    ImageView healthIcon = new ImageView(HEART); // Icon for health
	    ImageView hungerIcon = new ImageView(MEAT); // Icon for hunger
	    ImageView happinessIcon = new ImageView(SMILEY_FACE); // Icon for happiness
	    ImageView sleepIcon = new ImageView(BED); // Icon for sleep

	    // Position icons next to their corresponding progress bars
	    healthIcon.setLayoutX(healthBar.getLayoutX() - 20);
	    hungerIcon.setLayoutX(hungerbar.getLayoutX() - 20);
	    happinessIcon.setLayoutX(happinessbar.getLayoutX() - 20);
	    sleepIcon.setLayoutX(sleepBar.getLayoutX() - 20);

	    healthIcon.setLayoutY(healthBar.getLayoutY());
	    hungerIcon.setLayoutY(hungerbar.getLayoutY());
	    happinessIcon.setLayoutY(happinessbar.getLayoutY());
	    sleepIcon.setLayoutY(sleepBar.getLayoutY());

	    // Add icons to the game pane
	    gamePane.getChildren().add(healthIcon);
	    gamePane.getChildren().add(hungerIcon);
	    gamePane.getChildren().add(happinessIcon);
	    gamePane.getChildren().add(sleepIcon);

	    // Add progress bars to the game pane
	    gamePane.getChildren().add(healthBar);
	    gamePane.getChildren().add(hungerbar);
	    gamePane.getChildren().add(happinessbar);
	    gamePane.getChildren().add(sleepBar);
	}


	/**
	 * Updates the progress bars on the game interface to reflect the current status of the pet.
	 * Progress bars include health, hunger, happiness, and sleep levels.
	 */
	private void updateProgressBars() {
	    if (chosenPet != null) {
	        // Update health bar based on pet's health
	        healthBar.setProgress((double) chosenPet.getHealth() / 100);
	        // Update hunger bar based on pet's hunger
	        hungerbar.setProgress((double) chosenPet.getHunger() / 100);
	        // Update happiness bar based on pet's happiness
	        happinessbar.setProgress((double) chosenPet.getHappiness() / 100);
	        // Update sleep bar based on pet's sleep level
	        sleepBar.setProgress((double) chosenPet.getSleep() / 100);
	    }
	}

	/**
	 * Starts a timer to frequently update the pet's status and progress bars,
	 * as well as to check for critical levels that might trigger alerts or state changes.
	 */
	private void startStatusUpdateTimer() {
	    // Stop any existing timer before starting a new one
	    if (statsUpdateTimeline != null) {
	        statsUpdateTimeline.stop();
	    }

	    // Create a new timeline to update status every second
	    statsUpdateTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
	        if (chosenPet != null && !isGameOver) {
	            // Update the pet's internal status
	            chosenPet.updateStatus();
	            // Refresh the progress bars to reflect the new status
	            updateProgressBars();
	            // Check if any critical levels require action or warnings
	            checkDangerousLevels();

	            // Debugging output for tracking pet status
	            /*
	             * System.out.println("Pet status - Health: " + chosenPet.getHealth() +
	             * ", Hunger: " + chosenPet.getHunger() + ", Sleep: " + chosenPet.getSleep() +
	             * ", Happiness: " + chosenPet.getHappiness() + ", State: " +
	             * chosenPet.getCurrentState());
	             */
	        }
	    }));

	    // Set the timer to run indefinitely
	    statsUpdateTimeline.setCycleCount(Timeline.INDEFINITE);
	    // Start the timer
	    statsUpdateTimeline.play();
	}


	/**
	 * Checks the pet's critical levels for hunger, sleep, happiness, and health.
	 * Displays warnings if any of these levels fall below a dangerous threshold
	 * and triggers game-over logic if necessary.
	 */
	private void checkDangerousLevels() {
	    // Skip check if no pet is chosen or the game is already over
	    if (chosenPet == null || isGameOver) {
	        System.out.println("Check skipped: pet is null or game is over");
	        return;
	    }

	    // Debugging output to log the current state of the check
	    // System.out.println("Checking dangerous levels...");
	    // System.out.println("Current state: " + chosenPet.getCurrentState());
	    // System.out.println("Time since last warning: " + (System.currentTimeMillis() - lastWarningTime) + "ms");

	    // Handle game-over conditions if the pet is dead
	    if (chosenPet.isGameOver() || chosenPet.getCurrentState() == PetState.DEAD) {
	        // System.out.println("Death condition detected!");
	        if (!isGameOver) {
	            handleGameOver(); // Trigger game-over logic
	        }
	        return;
	    }

	    // Check if enough time has passed since the last warning
	    if (System.currentTimeMillis() - lastWarningTime >= WARNING_COOLDOWN) {
	        boolean needsWarning = false; // Tracks if any warning needs to be displayed
	        StringBuilder message = new StringBuilder(); // Collects warning messages

	        PetState state = chosenPet.getCurrentState(); // Get the current state of the pet

	        // Check if hunger level is critical
	        if (chosenPet.getHunger() <= 20) {
	            message.append("Your pet is very hungry! Please feed them!\n");
	            needsWarning = true;
	        }
	        // Check if sleep level is critical
	        if (chosenPet.getSleep() <= 20) {
	            message.append("Your pet is very tired! Let them sleep!\n");
	            needsWarning = true;
	        }
	        // Check if happiness level is critical
	        if (chosenPet.getHappiness() <= 20) {
	            message.append("Your pet is very unhappy! Play with them!\n");
	            needsWarning = true;
	        }
	        // Check if health level is critical
	        if (chosenPet.getHealth() <= 20) {
	            message.append("Your pet's health is critical! Take care of their needs!\n");
	            needsWarning = true;
	        }

	        // Display a warning alert if any critical level is detected
	        if (needsWarning) {
	            // System.out.println("Showing warning: " + message.toString());
	            lastWarningTime = System.currentTimeMillis(); // Update the last warning time
	            final String warningMessage = message.toString(); // Prepare the warning message

	            Platform.runLater(() -> {
	                Alert alert = new Alert(AlertType.WARNING); // Create a warning alert
	                alert.setTitle("Pet Needs Attention!");
	                alert.setHeaderText("Warning!");
	                alert.setContentText(warningMessage); // Set the alert content
	                alert.show(); // Display the alert
	            });
	        }
	    }
	}

	/**
	 * Handles the game-over logic when the pet dies or reaches an unplayable state.
	 * Stops all game timers, saves the current game state, and displays a game-over alert.
	 * 
	 * @throws IOexception e
	 */
	private void handleGameOver() {
	    isGameOver = true; // Set the game-over flag

	    // Stop all active timers
	    if (gameTimer != null)
	        gameTimer.stop();
	    if (statsUpdateTimeline != null)
	        statsUpdateTimeline.stop();
	    if (coinRespawnTimeline != null)
	        coinRespawnTimeline.stop();

	    Platform.runLater(() -> {
	        List<CharacterStats> statList = new ArrayList<>(); // List to store character stats

	        // Create a CharacterStats object with the current state of the pet
	        CharacterStats character = new CharacterStats(
	            chosenPet.getName(), // Pet name
	            (int) Score,         // Player score
	            chosenPet.getHealth(), // Pet health
	            chosenPet.getHunger(), // Pet hunger
	            chosenPet.getHappiness(), // Pet happiness
	            chosenPet.getSleep(), // Pet sleep
	            foodList.get(0).getFood().getAmount(), // Food item 1 quantity
	            foodList.get(1).getFood().getAmount(), // Food item 2 quantity
	            foodList.get(2).getFood().getAmount(), // Food item 3 quantity
	            foodList.get(3).getFood().getAmount(), // Food item 4 quantity
	            foodList.get(4).getFood().getAmount(), // Food item 5 quantity
	            0, // Placeholder for additional value
	            1, // Flag indicating game over
	            7  // Line identifier for saving
	        );

	        statList.add(character); // Add the stats to the list

	        // Attempt to save the updated stats to a file
	        try {
	            CharacterStatsApp.modifyLine(filePath, 7, statList); // Update the stats in the file

	        } catch (IOException e) {
	            System.out.println("Error saving character stats."); // Debug message
	            e.printStackTrace();
	        }

	        // Stop the music
	        music.stopMusic();

	        // Display a game-over alert to the user
	        Alert gameOver = new Alert(AlertType.ERROR);
	        gameOver.setTitle("Game Over");
	        gameOver.setHeaderText("Your pet has died!");
	        gameOver.setContentText("Please take better care of your next pet!");
	        gameOver.setOnHidden(evt -> {
	            gameStage.close(); // Close the game stage
	            menuStage.show();  // Show the main menu stage
	        });

	        gameOver.show(); // Display the alert
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