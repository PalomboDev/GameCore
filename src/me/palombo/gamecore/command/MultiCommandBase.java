package me.palombo.gamecore.command;

import me.palombo.gamecore.module.Module;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public abstract class MultiCommandBase<T extends Module> extends CommandBase<T> {

    private HashMap<String, CmdExtend> subCommands;

    public MultiCommandBase(T module, String permission, String... aliases) {
        super(module, permission, aliases);

        subCommands = new HashMap<>();
    }

    public void addCommand(CmdExtend cmd) {
        for (String s : cmd.getAliases())
            subCommands.put(s, cmd);
    }

    @Override
    public void execute(Player player, String[] args) {
        String commandName = null;
        String[] newArgs = new String[]{};

        if (args != null && args.length > 0){
            commandName = args[0];

            if (args.length > 1){
                newArgs = new String[args.length - 1];
                for (int i = 1; i < args.length; i++)
                    newArgs[i-1] = args[i];
            }
        }

        CmdExtend cmd = subCommands.get(commandName);

        if (cmd != null){
            if (player.hasPermission(cmd.getPermission())) {
                cmd.execute(player, newArgs);
            } else {
                player.sendMessage(ChatColor.RED + "No permission.");
            }
        } else {
            showHelp(player);
        }
    }

    public abstract void showHelp(Player p);
}
