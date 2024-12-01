package model;

import data.FoodItem;

// TODO: Auto-generated Javadoc
/** 
 * This enum represents various food items that can be used in the game. Each food item is associated
 * with a specific image URL, a `FoodItem` object, and an amount representing how many of that food exist.
 * 
 * @author Grant Von Hagen
 * @version 1.0
 */
public enum FOODS {
    
    /** The apple. */
    APPLE("view/resources/Food/apple.png", new FoodItem("Apple", 0.1), 0),
    
    /** The hk1. */
    HK1("view/resources/Food/hk1.png", new FoodItem("HK", 0.2), 0),
    
    /** The hk2. */
    HK2("view/resources/Food/hk2.png", new FoodItem("HK2", 0.2), 0),
    
    /** The sushi1. */
    SUSHI1("view/resources/Food/sushi1.png", new FoodItem("SUSHI1", 0.2), 0),
    
    /** The sushi2. */
    SUSHI2("view/resources/Food/sushi2.png", new FoodItem("SUSHI2", 0.2), 0);
    
    /** The url food. */
    private String urlFood;
    
    /** The food item. */
    private FoodItem foodItem;
    
    /** The amount. */
    private int amount;

    /**
     * Constructor for each food item, assigning its image URL, associated `FoodItem`, and initial amount.
     * 
     * @param urlFood the URL path to the food item's image
     * @param foodItem the `FoodItem` object associated with this food
     * @param amount the initial quantity of this food item
     */
    private FOODS(String urlFood, FoodItem foodItem, int amount) {
        this.urlFood = urlFood;
        this.foodItem = foodItem;
        this.amount = amount;
    }

    /**
     * Gets the URL path for the food item's image.
     * 
     * @return the image URL for the food
     */
    public String getURL() {
        return this.urlFood;
    }

    /**
     * Gets the associated `FoodItem` object for this food type.
     * 
     * @return the `FoodItem` associated with this food
     */
    public FoodItem getFoodItem() {
        return foodItem;
    }

    /**
     * Gets the current amount of this food item.
     * 
     * @return the current quantity of the food item
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount of this food item to a specified value.
     * 
     * @param amount the new amount for this food item
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Increments the amount of this food item by 1.
     */
    public void incrementAmount() {
        amount += 1;
    }

    /**
     * Decrements the amount of this food item by 1, ensuring it does not go below zero.
     */
    public void decrementAmount() {
        if (amount > 0) {
            amount -= 1;
        }
    }
}
