package net.kevarion.beaconbattle.game.state;

import org.bukkit.Bukkit;

public class GameStateManager {

    private GameState currentState;

    public GameStateManager() {
        currentState = GameState.PREGAME;
    }

    /**
     * Sets the current game state.
     * @param newState The state to set.
     */
    public void setGameState(GameState newState) {
        if (currentState != newState) {
            currentState = newState;
            onStateChange(newState);
        }
    }

    /**
     * Gets the current game state.
     * @return The current game state.
     */
    public GameState getCurrentState() {
        return currentState;
    }

    /**
     * Checks if the game is currently in the provided state.
     * @param state The state to check
     * @return True if the game is in the given state, false otherwise.
     */
    public boolean isInState(GameState state) {
        return currentState == state;
    }

    /**
     * Called when the game state changes. You can add additional logic here.
     * @param newState The new game state.
     */
    private void onStateChange(GameState newState) {
        switch (newState) {
            case PREGAME:
                System.out.println("Pregame");
            case STARTING:
                System.out.println("Starting");
            case ACTIVE:
                System.out.println("Active");
            case ENDING:
                System.out.println("Ending");
            case RESETTING:
                System.out.println("Resetting");
            default:
                break;
        }
    }

    /**
     * Convenience method to check if the game is active.
     * @return True if the game is ACTIVE, false otherwise.
     */
    public boolean isGameActive() {
        return currentState == GameState.ACTIVE;
    }

    /**
     * Convenience method to check if the game is in the pregame stage.
     * @return True if the game is in PREGAME, false otherwise.
     */
    public boolean isPregame() {
        return currentState == GameState.PREGAME;
    }

}
