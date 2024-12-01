/**
 * ParentalControls entity used for managing parental control features such as setting a playtime limit, 
 * tracking total playtime, and monitoring the number of times the game has been opened.
 * <br><br>
 * Provides methods to interact with the controls, such as verifying login {@link #verifyLogin(String)}, 
 * resetting statistics {@link #resetStats()}, and updating playtime {@link #updatePlayTimeLimit(int)}.<br><br>
 *
 * @version 0
 * @author Grant Von Hagen
 */

package data;


public class ParentalControls {
    private String password;
    private int playTimeLimit;
    private int totalPlayTime;
    private int averagePlayTime;
    private int openCount;
    
    /**
     * Default Values are 
     * PlaytimeLimit =  -1 (unlimited limit) 
     * totalPlayTime = 0 (default)
     * averagePlayTime = (totaPlayTime / amount of time logged in?)
     */
    public ParentalControls(String password, int playTimeLimit, int totalPlayTime, int openCount) {
    	this.password = password;
    	this.playTimeLimit = playTimeLimit;
    	this.totalPlayTime = totalPlayTime;
    	this.openCount = openCount;
    	this.averagePlayTime = (int) (totalPlayTime / openCount);
    }
    
    /**
     * This method compares the password with the stored password
     * to check if they are identical. Returns True if match false if not match
     * @param password input string from user
     * @return {@code true} if the password matches {@code false} if password doesnt match
     */
    public boolean verifyLogin(String password) {
        
    	if (this.password.equals(password)) {
    		return true;
    	}
        return false;
    }

    /**
     * Resets all the statistics
     * not sure why we are doing this but sure
     * 
     */
    public void resetStats() {
        this.playTimeLimit = -1;
        this.totalPlayTime = 0;
        this.averagePlayTime = 0;
        this.openCount = 0;
    }

    /**
     * Returns true if successfully revived pet (as in it wasnt dead)
     * returns false if the pet is already alive
     * @param pet to revive.
     * @return True is revive successful, False if revive not successful
     */
    public boolean revive(Pet pet) {
        if (pet.isGameOver()) {
        	pet.setGameOver(false);
        	return true;
        }
        return false;
        
    }
    /**
     * Sets the play time limit.
     * 
     * @param limit The time limit to set, in minutes.
     */
    public void setPlayTimeLimit(int limit) {
        this.playTimeLimit = limit;
    }

    /**
     * Updates the total play time by adding the specified time.
     * 
     * @param time The amount of time to add to the total play time, in minutes.
     */
    public void updatePlayTimeLimit(int time) {
        this.totalPlayTime += time;
    }

    /**
     * Gets the total play time.
     * 
     * @return The total play time, in minutes.
     */
    public int getTotalPlayTime() {
        return this.totalPlayTime;
    }

    /**
     * Gets the average play time.
     * 
     * @return The average play time, in minutes.
     */
    public int getAveragePlayTime() {
        return this.averagePlayTime;
    }

    /**
     * Gets the number of times the game has been opened.
     * 
     * @return The number of times the game has been opened.
     */
    public int getOpenCount() {
        return this.openCount;
    }

    /**
     * Gets the current play time limit.
     * 
     * @return The play time limit, in minutes.
     */
    public int getPlayTimeLimit() {
        return this.playTimeLimit;
    }
}
