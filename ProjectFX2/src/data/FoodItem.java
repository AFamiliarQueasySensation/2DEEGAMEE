package data;

/**
 * Represents a food item in the inventory.
 * @author mahip
 * @author keito
 */
public class FoodItem extends Item {

    /**
     * Creates a new food item with the specified name and value.
     *
     * @param foodName  the name of the food item
     * @param foodValue the value of the food item
     */
	private double foodValue;
	private String foodName;
	
    public FoodItem(String foodName, double foodValue) {
        super(foodName, foodValue); // Use the Item constructor
        this.foodValue = foodValue;
        this.foodName = foodName;
    }

    /**
     * Defines the effect of the food item when used.
     * @return the value of the food item
     */
    @Override
    public double useItem() {
        return value; // Return the food value as its effect
    }

    /*
	public int getHappinessBoost() {
		// TODO Auto-generated method stub
		return 0;
	}
	*/

	public double getNutritionValue() {
		// TODO Auto-generated method stub
		return value;
	}

    public String getName() {
        return foodName;
    }
}

