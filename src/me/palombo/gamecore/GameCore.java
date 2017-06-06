package me.palombo.gamecore;

import me.palombo.gamecore.command.CmdExtend;
import me.palombo.gamecore.command.CommandManager;
import me.palombo.gamecore.game.GameManager;
import me.palombo.gamecore.map.MapManager;
import me.palombo.gamecore.update.Ticker;
import org.bukkit.plugin.java.JavaPlugin;

public class GameCore extends JavaPlugin {

    public static GameCore pabloCore;

    private CommandManager commandManager;
    private MapManager mapManager;
    private GameManager gameManager;

    public void onEnable() {
        pabloCore = this;

        commandManager = new CommandManager(this);
        mapManager = new MapManager(this);

        new Ticker(this);

        for (CmdExtend command : getCommandManager().commands.values()) {
            getCommandManager().addCommand(command);
        }

    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void enableGameManager() {
        gameManager = new GameManager(get());
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public static GameCore get() {
        return pabloCore;
    }
}
