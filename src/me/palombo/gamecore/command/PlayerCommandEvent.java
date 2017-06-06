package me.palombo.gamecore.command;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerCommandEvent extends Event implements Cancellable {

    private boolean cancelled;
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private CmdExtend command;
    private String[] args;

    public PlayerCommandEvent(Player player, CmdExtend command, String[] args) {
        this.player = player;
        this.command = command;
        this.args = args;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled(){
        return cancelled;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public CmdExtend getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }
    
}
