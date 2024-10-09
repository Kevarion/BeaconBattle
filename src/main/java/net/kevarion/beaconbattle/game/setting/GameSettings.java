package net.kevarion.beaconbattle.game.setting;

public class GameSettings {

    private final int maxTeams;
    private final int maxPlayersPerTeam;
    private final int gameDuration;
    private final int respawnTime;
    private final int invisibilityDuration;

    public GameSettings(int maxTeams, int maxPlayersPerTeam, int gameDuration, int respawnTime, int invisibilityDuration) {
        this.maxTeams = maxTeams;
        this.maxPlayersPerTeam = maxPlayersPerTeam;
        this.gameDuration = gameDuration;
        this.respawnTime = respawnTime;
        this.invisibilityDuration = invisibilityDuration;
    }

    public int getMaxTeams() {
        return maxTeams;
    }

    public int getMaxPlayersPerTeam() {
        return maxPlayersPerTeam;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public int getInvisibilityDuration() {
        return invisibilityDuration;
    }

}
