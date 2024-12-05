package model;

import data.FoodItem;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/** 
 * This class handles food selection within the inventory. It manages
 * the display of the food item, the amount, and whether the food is selected.
 * 
 * @author Grant Von Hagen
 * @version 1.0
 */
public class foodPicker extends VBox {

    private ImageView foodImage;
    private FOODS food;
    private FoodItem foodItem;
    private inventoryLabel amount;
    private boolean foodSelected; // Indicates if the food item is currently selected

    /**
     * Constructs a food picker for displaying food items in the inventory.
     * 
     * @param food the food item associated with this picker
     */
    public foodPicker(FOODS food) {
        foodImage = new ImageView(food.getURL());
        amount = new inventoryLabel(Integer.toString(food.getAmount()));

        this.food = food;
        foodSelected = false;
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(foodImage);
        this.getChildren().add(amount);
    }

    /**
     * Updates the displayed amount of the food item in the inventory.
     */
    public void updateAmount() {
        amount.setText(Integer.toString(food.getAmount()));
    }

    /**
     * Gets the associated food item.
     * 
     * @return the food item
     */
    public FOODS getFood() {
        return food;
    }

    /**
     * Checks if the food item is selected.
     * 
     * @return true if the food item is selected, false otherwise
     */
    public boolean getFoodSelected() {
        return foodSelected;
    }

    /**
     * Gets the ImageView representing the food item.
     * 
     * @return the ImageView of the food item
     */
    public ImageView getFoodImage() {
        return foodImage;
    }

    /**
     * Sets the selected state of the food item, adding or removing a drop shadow effect
     * to indicate selection.
     * 
     * @param foodSelected true if the food item is selected, false otherwise
     */
    public void setFoodSelected(boolean foodSelected) {
        this.foodSelected = foodSelected;
        if (this.foodSelected) {
            DropShadow dropshadow = new DropShadow();
            dropshadow.setColor(Color.WHITESMOKE);
            dropshadow.setRadius(20);
            dropshadow.setSpread(0.5);
            foodImage.setEffect(dropshadow);
        } else {
            foodImage.setEffect(null);
        }
    }
}
