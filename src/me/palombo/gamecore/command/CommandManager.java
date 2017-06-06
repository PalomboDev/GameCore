package me.palombo.gamecore.command;

import me.palombo.gamecore.module.Module;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class CommandManager extends Module {

    public HashMap<String, CmdExtend> commands;

    public CommandManager(JavaPlugin plugin) {
        super(plugin);

        commands = new HashMap<>();
    }

    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) return;

        Player player = event.getPlayer();

        String cmdName = event.getMessage().substring(event.getMessage().startsWith(" ") ? 2 : 1);
        String[] newArgs = new String[]{};

        if (cmdName.contains(" ")){
            cmdName = cmdName.split(" ")[0];
            newArgs = event.getMessage().substring(event.getMessage().indexOf(' ') + 1).split(" ");
        }

        CmdExtend cmd = commands.get(cmdName.toLowerCase());
        if (cmd == null) return;
        event.setCancelled(true);

        if (!(player.hasPermission(cmd.getPermission()))){
            player.sendMessage(ChatColor.RED + "No permission.");
            return;
        }

        PlayerCommandEvent commandEvent = new PlayerCommandEvent(player, cmd, newArgs);
        Bukkit.getPluginManager().callEvent(commandEvent);
        if(commandEvent.isCancelled()) return;

        cmd.execute(player, newArgs);
    }

    public void addCommand(CmdExtend cmd){
        for (String cur : cmd.getAliases())
            commands.put(cur, cmd);
    }

}
