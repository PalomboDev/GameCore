package me.palombo.gamecore.game.rules;

public class RuleSet {

    private boolean canPVP;
    private boolean canPVE;
    private boolean canMobsSpawn;
    private boolean canLoseHunger;
    private boolean canFallDamage;
    private boolean canPlaceBlocks;
    private boolean canBreakBlocks;

    public RuleSet() {
        canPVP = false;
        canPVE = false;
        canMobsSpawn = false;
        canLoseHunger = false;
        canFallDamage = false;
        canPlaceBlocks = false;
        canBreakBlocks = false;
    }

    public boolean isCanPVP() {
        return canPVP;
    }

    public void setCanPVP(boolean canPVP) {
        this.canPVP = canPVP;
    }

    public boolean isCanPVE() {
        return canPVE;
    }

    public void setCanPVE(boolean canPVE) {
        this.canPVE = canPVE;
    }

    public boolean isCanMobsSpawn() {
        return canMobsSpawn;
    }

    public void setCanMobsSpawn(boolean canMobsSpawn) {
        this.canMobsSpawn = canMobsSpawn;
    }

    public boolean isCanLoseHunger() {
        return canLoseHunger;
    }

    public void setCanLoseHunger(boolean canLoseHunger) {
        this.canLoseHunger = canLoseHunger;
    }

    public boolean isCanFallDamage() {
        return canFallDamage;
    }

    public void setCanFallDamage(boolean canFallDamage) {
        this.canFallDamage = canFallDamage;
    }

    public boolean isCanPlaceBlocks() {
        return canPlaceBlocks;
    }

    public void setCanPlaceBlocks(boolean canPlaceBlocks) {
        this.canPlaceBlocks = canPlaceBlocks;
    }

    public boolean isCanBreakBlocks() {
        return canBreakBlocks;
    }

    public void setCanBreakBlocks(boolean canBreakBlocks) {
        this.canBreakBlocks = canBreakBlocks;
    }
}
