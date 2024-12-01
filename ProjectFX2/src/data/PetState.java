/**
 * Enum representing the various states of a pet, such as DEAD, STARVING, EXHAUSTED, DEPRESSED, and HEALTHY.
 * <br><br>
 * Each state corresponds to a pet's emotional or physical condition, and the state can be changed using {@link #changePetState(boolean)}.
 * The pet's state is stored as a boolean value, which can be retrieved with {@link #getPetState()}.
 * 
 * @version 0
 * @author Grant Von Hagen
 */
package data;

public enum PetState {

    DEAD(false), STARVING(false), EXHAUSTED(false), DEPRESSED(false), HEALTHY(false);

    private boolean petEmotion;

    private PetState(boolean petEmotion) {
        this.petEmotion = petEmotion;
    }

    /**
     * Retrieves the pet's current state.
     * 
     * @return {@code true} if the pet is in an emotional state (as indicated by the boolean), {@code false} otherwise.
     */
    public boolean getPetState() {
        return this.petEmotion;
    }

    /**
     * Changes the pet's state to the new state.
     * 
     * @param newState The new state to change the pet to.
     * @return {@code false} if the pet's state did not change (e.g., from DEAD(true) to DEAD(true)), 
     *         {@code true} if the state was successfully changed.
     */
    public boolean changePetState(boolean newState) {
        if (petEmotion == newState) {
            return false;
        } else {
            this.petEmotion = newState;
            return true;
        }
    }

}