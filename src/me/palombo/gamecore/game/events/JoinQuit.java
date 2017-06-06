package me.palombo.gamecore.game.events;

import me.palombo.gamecore.GameCore;
import me.palombo.gamecore.game.Game;
import me.palombo.gamecore.game.GameState;
import me.palombo.gamecore.util.M;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuit implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Game game = GameCore.get().getGameManager().getGame();

        if (!game.getState().isJoinable()){
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cThe game cannot be joined right now.");
            return;
        }

        if (Bukkit.getOnlinePlayers().size() >= game.getMaxPlayers()) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cThe game is full.");
            return;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);

        Bukkit.broadcastMessage("§a" + M.ARROW_CHARACTER + " §7" + player.getName());

        if (GameCore.get().getGameManager().getGame().getState().equals(GameState.WAITING)){
            if (Bukkit.getOnlinePlayers().size() == GameCore.get().getGameManager().getGame().getMinPlayers()){
                GameCore.get().getGameManager().setCountdownState();
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Bukkit.broadcastMessage("§c" + M.ARROW_CHARACTER + " §7" + player.getName());
    }

}
