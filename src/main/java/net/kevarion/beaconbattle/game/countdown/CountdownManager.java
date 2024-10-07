package net.kevarion.beaconbattle.game.countdown;

import net.kevarion.beaconbattle.BeaconBattle;
import net.kevarion.beaconbattle.game.GameManager;
import net.kevarion.beaconbattle.game.state.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownManager {

    private final GameManager gameManager;
    private int countdownTime;
    private BukkitRunnable countdownTask;

    public CountdownManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Starts the countdown from the specified time.
     *
     * @param startTime The time in seconds to start the countdown.
     */
    public void startCountdown(int startTime) {
        this.countdownTime = startTime;

        countdownTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (countdownTime <= 0) {
                    gameManager.getGameStateManager().setGameState(GameState.ACTIVE);
                    notifyPlayers("&aThe game has begun!");
                    cancel();
                } else {
                    if (countdownTime == 10 || countdownTime <= 5) {
                        notifyPlayers("&eCountdown: &b" + countdownTime);
                    }
                }
                countdownTime--;
            }
        };

        countdownTask.runTaskTimer(BeaconBattle.getInstance(), 0, 20);

    }

    /**
     * Notifies all players in the game with a message.
     *
     * @param message The message to send to players.
     */
    private void notifyPlayers(String message) {
        for (Player player : gameManager.getPlayers()) {
            player.sendMessage(message.replace("&", "§"));
        }
    }

    /**
     * Stops the countdown if it's currently running.
     */
    public void stopCountdown() {
        if (countdownTask != null) {
            countdownTask.cancel();
        }
    }

}
