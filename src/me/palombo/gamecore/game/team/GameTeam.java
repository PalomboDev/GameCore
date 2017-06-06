package me.palombo.gamecore.game.team;

import org.bukkit.ChatColor;

public class GameTeam {

    private String name;
    private ChatColor color;
    private boolean visible;
    private int maxSize;

    public GameTeam(String name, ChatColor color) {
        this.name = name;
        this.color = color;

        this.visible = true;
        this.maxSize = -1; // Default = No Limit
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getColoredName(){
        return getColor() + "§l" + getName();
    }
}
