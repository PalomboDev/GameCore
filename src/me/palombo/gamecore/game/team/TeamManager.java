package me.palombo.gamecore.game.team;

import me.palombo.gamecore.module.Module;
import me.palombo.gamecore.util.M;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class TeamManager extends Module {

    private ArrayList<GameTeam> allTeams;
    private HashMap<Player, GameTeam> teamMap;

    public TeamManager(JavaPlugin plugin) {
        super(plugin);

        allTeams = new ArrayList<>();
        teamMap = new HashMap<>();

        {
            GameTeam spec = new GameTeam("Spectators", ChatColor.GRAY);
            spec.setVisible(false);
            registerTeam(spec); // Spectators auto added
        }
    }

    public void registerTeam(GameTeam team){
        allTeams.add(team);
        getPlugin().getLogger().info("Registered GameTeam: " + team.getName());
    }

    public GameTeam getTeam(Player player){
        return (hasTeam(player) ? teamMap.get(player) : null);
    }

    public boolean hasTeam(Player player){
        return teamMap.containsKey(player);
    }

    public void leaveTeam(Player player, boolean announce){
        if (!hasTeam(player))
            return;

        if (announce)
            player.sendMessage(M.regular("You left " + getTeam(player).getColoredName() + " §7team."));

        teamMap.remove(player);
    }

    public void joinTeam(Player player, GameTeam team, boolean announce){
        if (hasTeam(player))
            leaveTeam(player, false);

        teamMap.put(player, team);
        player.sendMessage(M.regular("You joined " + team.getColoredName() + " §7team."));
    }

}
