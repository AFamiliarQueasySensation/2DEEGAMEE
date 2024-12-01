package data;

// TODO: Auto-generated Javadoc
/**
 * Represents a gift item in the inventory.
 * @author mahip
 * @author keito
 */
public class GiftItem extends Item {

    /**
     * Creates a new gift item with the specified name and value.
     *
     * @param giftName  the name of the gift item
     * @param giftValue the value of the gift item
     */
    public GiftItem(String giftName, int giftValue) {
        super(giftName, giftValue); // Use the Item constructor
    }

    /**
     * Defines the effect of the gift item when used.
     * @return the value of the gift item
     */
    @Override
    public double useItem() {
        return value; // Return the gift value as its effect
    }

	/**
	 * Gets the happiness boost.
	 *
	 * @return the happiness boost
	 */
	public int getHappinessBoost() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Gets the score boost.
	 *
	 * @return the score boost
	 */
	public int getScoreBoost() {
		// TODO Auto-generated method stub
		return 0;
	}
}
