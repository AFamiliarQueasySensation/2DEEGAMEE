package data;

	import java.util.ArrayList;
	import java.util.List;

	/**
	 * Manages the inventory of items, including food and gift items.
	 * This class allows adding, removing, and retrieving items from the inventory.
	 *
	 * @author mahip
	 * @author keito
	 */
	public class Inventory {
	    private List<FoodItem> foodItems; // List of food items in the inventory
	    private List<GiftItem> giftItems; // List of gift items in the inventory

	    /**
	     * Initializes an empty inventory for food and gift items.
	     */
	    public Inventory() {
	        this.foodItems = new ArrayList<>();
	        this.giftItems = new ArrayList<>();
	    }

	    /**
	     * Adds a food item to the inventory.
	     *
	     * @param item the food item to add
	     */
	    public void addFoodItem(FoodItem item) {
	        foodItems.add(item);
	    }

	    /**
	     * Adds a gift item to the inventory.
	     *
	     * @param item the gift item to add
	     */
	    public void addGiftItem(GiftItem item) {
	        giftItems.add(item);
	    }

	    /**
	     * Uses (removes) a specified food item from the inventory.
	     * If the item is not present, no changes are made.
	     *
	     * @param item the food item to use
	     */
	    public void useFoodItem(FoodItem item) {
	        if (foodItems.contains(item)) {
	            foodItems.remove(item);
	        }
	    }

	    /**
	     * Uses (removes) a specified gift item from the inventory.
	     * If the item is not present, no changes are made.
	     *
	     * @param item the gift item to use
	     */
	    public void useGiftItem(GiftItem item) {
	        if (giftItems.contains(item)) {
	            giftItems.remove(item);
	        }
	    }

	    /**
	     * Retrieves a copy of the list of food items in the inventory.
	     *
	     * @return a new list containing all food items
	     */
	    public List<FoodItem> getFoodItems() {
	        return this.foodItems;
	    }

	    /**
	     * Retrieves a copy of the list of gift items in the inventory.
	     *
	     * @return a new list containing all gift items
	     */
	    public List<GiftItem> getGiftItems() {
	        return this.giftItems;
	    }

}
