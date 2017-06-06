package me.palombo.gamecore.module;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Module implements Listener {

    private JavaPlugin plugin;

    public Module(JavaPlugin plugin) {
        this.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public PluginManager getPluginManager(){
        return Bukkit.getPluginManager();
    }

    public JavaPlugin getPlugin(){
        return plugin;
    }
}
