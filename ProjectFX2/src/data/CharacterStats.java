/**
 * The CharacterStats class holds values related to a character's statistics, which are used for 
 * saving and loading data from a CSV file. This class contains various integer values representing 
 * different character attributes, along with getter and setter methods for each value.
 * 
 * @author Grant Von Hagen
 * @version 1.0
 */
package data;


public class CharacterStats {
    
    /** The value 1. */
    // Instance variables for character statistics
    private String value1;
    
    /** The value 2. */
    private int value2;
    
    /** The value 3. */
    private int value3;
    
    /** The value 4. */
    private int value4;
    
    /** The value 5. */
    private int value5;
    
    /** The value 6. */
    private int value6;
    
    /** The value 7. */
    private int value7;
    
    /** The value 8. */
    private int value8;
    
    /** The value 9. */
    private int value9;
    
    /** The value 10. */
    private int value10;
    
    /** The value 11. */
    private int value11;
    
    /** The value 12. */
    private int value12;
    
    /** The value 13. */
    private int value13;
    
    /** The value 14. */
    private int value14;

    /**
     * Constructs a CharacterStats object with the specified values.
     *
     * @param value1 a String representing the first attribute of the character
     * @param value2 an integer representing the second attribute of the character
     * @param value3 an integer representing the third attribute of the character
     * @param value4 an integer representing the fourth attribute of the character
     * @param value5 an integer representing the fifth attribute of the character
     * @param value6 an integer representing the sixth attribute of the character
     * @param value7 an integer representing the seventh attribute of the character
     * @param value8 an integer representing the eighth attribute of the character
     * @param value9 an integer representing the ninth attribute of the character
     * @param value10 an integer representing the tenth attribute of the character
     * @param value11 an integer representing the eleventh attribute of the character
     * @param value12 an integer representing the twelfth attribute of the character
     * @param value13 an integer representing the thirteenth attribute of the character
     * @param value14 an integer representing the fourteenth attribute of the character
     */
    public CharacterStats(String value1, int value2, int value3, int value4, int value5, int value6, int value7, int value8, int value9, int value10, int value11, int value12, int value13, int value14) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.value6 = value6;
        this.value7 = value7;
        this.value8 = value8;
        this.value9 = value9;
        this.value10 = value10;
        this.value11 = value11;
        this.value12 = value12;
        this.value13 = value13;
        this.value14 = value14;
    }

    // Getters
    /**
     * Retrieves the value of the first attribute.
     *
     * @return the first attribute as a String
     */
    public String getValue1() {
        return value1;
    }

    /**
     * Retrieves the value of the second attribute.
     *
     * @return the second attribute as an integer
     */
    public int getValue2() {
        return value2;
    }

    /**
     * Retrieves the value of the third attribute.
     *
     * @return the third attribute as an integer
     */
    public int getValue3() {
        return value3;
    }

    /**
     * Retrieves the value of the fourth attribute.
     *
     * @return the fourth attribute as an integer
     */
    public int getValue4() {
        return value4;
    }

    /**
     * Retrieves the value of the fifth attribute.
     *
     * @return the fifth attribute as an integer
     */
    public int getValue5() {
        return value5;
    }

    /**
     * Retrieves the value of the sixth attribute.
     *
     * @return the sixth attribute as an integer
     */
    public int getValue6() {
        return value6;
    }

    /**
     * Retrieves the value of the seventh attribute.
     *
     * @return the seventh attribute as an integer
     */
    public int getValue7() {
        return value7;
    }

    /**
     * Retrieves the value of the eighth attribute.
     *
     * @return the eighth attribute as an integer
     */
    public int getValue8() {
        return value8;
    }

    /**
     * Retrieves the value of the ninth attribute.
     *
     * @return the ninth attribute as an integer
     */
    public int getValue9() {
        return value9;
    }

    /**
     * Retrieves the value of the tenth attribute.
     *
     * @return the tenth attribute as an integer
     */
    public int getValue10() {
        return value10;
    }

    /**
     * Retrieves the value of the eleventh attribute.
     *
     * @return the eleventh attribute as an integer
     */
    public int getValue11() {
        return value11;
    }

    /**
     * Retrieves the value of the twelfth attribute.
     *
     * @return the twelfth attribute as an integer
     */
    public int getValue12() {
        return value12;
    }

    /**
     * Retrieves the value of the thirteenth attribute.
     *
     * @return the thirteenth attribute as an integer
     */
    public int getValue13() {
        return value13;
    }

    /**
     * Retrieves the value of the fourteenth attribute.
     *
     * @return the fourteenth attribute as an integer
     */
    public int getValue14() {
        return value14;
    }

    // Setters
    /**
     * Sets the value of the first attribute.
     *
     * @param value1 the value to set for the first attribute
     */
    public void setValue1(String value1) {
        this.value1 = value1;
    }

    /**
     * Sets the value of the second attribute.
     *
     * @param value2 the value to set for the second attribute
     */
    public void setValue2(int value2) {
        this.value2 = value2;
    }

    /**
     * Sets the value of the third attribute.
     *
     * @param value3 the value to set for the third attribute
     */
    public void setValue3(int value3) {
        this.value3 = value3;
    }

    /**
     * Sets the value of the fourth attribute.
     *
     * @param value4 the value to set for the fourth attribute
     */
    public void setValue4(int value4) {
        this.value4 = value4;
    }

    /**
     * Sets the value of the fifth attribute.
     *
     * @param value5 the value to set for the fifth attribute
     */
    public void setValue5(int value5) {
        this.value5 = value5;
    }

    /**
     * Sets the value of the sixth attribute.
     *
     * @param value6 the value to set for the sixth attribute
     */
    public void setValue6(int value6) {
        this.value6 = value6;
    }

    /**
     * Sets the value of the seventh attribute.
     *
     * @param value7 the value to set for the seventh attribute
     */
    public void setValue7(int value7) {
        this.value7 = value7;
    }

    /**
     * Sets the value of the eighth attribute.
     *
     * @param value8 the value to set for the eighth attribute
     */
    public void setValue8(int value8) {
        this.value8 = value8;
    }

    /**
     * Sets the value of the ninth attribute.
     *
     * @param value9 the value to set for the ninth attribute
     */
    public void setValue9(int value9) {
        this.value9 = value9;
    }

    /**
     * Sets the value of the tenth attribute.
     *
     * @param value10 the value to set for the tenth attribute
     */
    public void setValue10(int value10) {
        this.value10 = value10;
    }

    /**
     * Sets the value of the eleventh attribute.
     *
     * @param value11 the value to set for the eleventh attribute
     */
    public void setValue11(int value11) {
        this.value11 = value11;
    }

    /**
     * Sets the value of the twelfth attribute.
     *
     * @param value12 the value to set for the twelfth attribute
     */
    public void setValue12(int value12) {
        this.value12 = value12;
    }

    /**
     * Sets the value of the thirteenth attribute.
     *
     * @param value13 the value to set for the thirteenth attribute
     */
    public void setValue13(int value13) {
        this.value13 = value13;
    }

    /**
     * Sets the value of the fourteenth attribute.
     *
     * @param value14 the value to set for the fourteenth attribute
     */
    public void setValue14(int value14) {
        this.value14 = value14;
    }
}
