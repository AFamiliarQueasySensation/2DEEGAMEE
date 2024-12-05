
package data;

/**
 * Represents a general item in the inventory.
 * Items have a name and a value that describe their effect.
 *
 * @author mahip
 * @author Keito
 */

public abstract class Item {

    protected String name; // The name of the item
    protected double value;   // The value of the item (e.g., effect magnitude)

    /**
     * Creates a new item with the specified name and value.
     *
     * @param name  the name of the item
     * @param foodValue the value of the item
     */
    public Item(String name, double foodValue) {
        this.name = name;
        this.value = foodValue;
    }

    /**
     * Retrieves the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the value of the item.
     *
     * @return the value of the item
     */
    public double getValue() {
        return value;
    }

    /**
     * Abstract method to define the item's effect when used.
     * @return the effect of the item
     */
    public abstract double useItem();
}
