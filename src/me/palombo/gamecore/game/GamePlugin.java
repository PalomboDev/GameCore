package me.palombo.gamecore.game;

import me.palombo.gamecore.GameCore;
import me.palombo.gamecore.GameCorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class GamePlugin extends GameCorePlugin {

    public GamePlugin(JavaPlugin plugin) {
        super(plugin);

        //Game Manager
        GameCore.get().enableGameManager();
        GameCore.get().getGameManager().registerGame(setupGame());

        //Map
        GameCore.get().getMapManager().clearOldMaps();

        Bukkit.getScheduler().runTaskLater(getPlugin(), () -> GameCore.get().getGameManager().getGame().setServerMap(GameCore.get().getMapManager().selectRandomSetup()), 15 * 20L);
    }

    public abstract Game setupGame();
}
