package net.kevarion.beaconbattle.level;

public class Level {

    private int level;
    private int prestige;
    private int xp;
    private int xpToNextLevel;

    public Level(int level, int prestige, int xp, int xpToNextLevel) {
        this.level = level;
        this.prestige = prestige;
        this.xp = xp;
        this.xpToNextLevel = xpToNextLevel;
    }

    public Level() {
        this.level = 1;
        this.prestige = 0;
        this.xp = 0;
        this.xpToNextLevel = 3000;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public int getXP() {
        return xp;
    }

    public int getXpToNextLevel() {
        return xpToNextLevel;
    }

    public void setXP(int xp) {
        this.xp = xp;
    }

    public void setXPToNextLevel(int xpToNextLevel) {
        this.xpToNextLevel = xpToNextLevel;
    }

    public void addXP(int amount) {
        xp += amount;
        while (xp >= xpToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        xp -= xpToNextLevel;
        level++;
        xpToNextLevel = calculateXPForNextLevel();

        if (level > 999) {
            level = 1;
            prestige++;
        }
    }

    private int calculateXPForNextLevel() {
        return 3000 + (level * 100);
    }

}
