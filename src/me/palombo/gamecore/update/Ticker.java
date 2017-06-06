package me.palombo.gamecore.update;

import me.palombo.gamecore.module.Module;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Ticker extends Module {

    public Ticker(JavaPlugin plugin) {
        super(plugin);

        startTicks();
    }

    private void startTicks(){
        BukkitRunnable run = new BukkitRunnable() {
            public void run() {
                getPluginManager().callEvent(new TickEvent());
            }
        };

        run.runTaskTimer(getPlugin(), 20L, 20L);
    }

}
