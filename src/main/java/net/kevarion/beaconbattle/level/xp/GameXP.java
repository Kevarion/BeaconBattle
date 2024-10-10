package net.kevarion.beaconbattle.level.xp;

public class GameXP {

    private int xp;

    public GameXP() {
        this.xp = 0;
    }

    public int getXP() {
        return xp;
    }

    public void addXP(int amount) {
        this.xp += amount;
    }

    public void setXP(int xp) {
        this.xp = xp;
    }

    public void resetXP() {
        this.xp = 0;
    }

}
