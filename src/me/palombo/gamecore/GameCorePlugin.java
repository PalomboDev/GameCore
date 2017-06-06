package me.palombo.gamecore;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class GameCorePlugin {

    private JavaPlugin plugin;

    public GameCorePlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public abstract void onEnable();
    public abstract void onDisable();
}

