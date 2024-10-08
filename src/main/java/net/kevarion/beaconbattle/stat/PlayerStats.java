package net.kevarion.beaconbattle.stat;

public class PlayerStats {

    private int kills;
    private int deaths;
    private int wins;

    public PlayerStats() {
        this.kills = 0;
        this.deaths = 0;
        this.wins = 0;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void incrementKills() {
        this.kills++;
    }

    public void incrementDeaths() {
        this.deaths++;
    }

    public void incrementWins() {
        this.wins++;
    }


}
