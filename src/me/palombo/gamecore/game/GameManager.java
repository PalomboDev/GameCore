package me.palombo.gamecore.game;

import me.palombo.gamecore.game.events.JoinQuit;
import me.palombo.gamecore.game.lobby.LobbyManager;
import me.palombo.gamecore.game.team.GameTeam;
import me.palombo.gamecore.game.team.TeamManager;
import me.palombo.gamecore.module.Module;
import me.palombo.gamecore.update.TickEvent;
import me.palombo.gamecore.util.M;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class GameManager extends Module {

    private LobbyManager lobbyManager;
    private TeamManager teamManager;

    private Game game;

    public GameManager(JavaPlugin plugin) {
        super(plugin);

        registerGameListeners();
        lobbyManager = new LobbyManager(plugin);
        teamManager = new TeamManager(plugin);
    }

    public void registerGame(Game game){
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public LobbyManager getLobbyManager() {
        return lobbyManager;
    }

    public void showDesc(int remaining){
        Bukkit.broadcastMessage(M.line("§8"));
        Bukkit.broadcastMessage(" §6§l" + game.getName() + " §6is starting in " + remaining + " seconds!");
        Bukkit.broadcastMessage(" ");

        for (String line : game.getDescription()){
            Bukkit.broadcastMessage(" " + M.arrow("") + "§e" + line);
        }

        Bukkit.broadcastMessage(" ");
        Bukkit.broadcastMessage(" §6You are about to play the map §l" + game.getServerMap().getName() + "§6by " + game.getServerMap().getAuthor() + ".");
        Bukkit.broadcastMessage(M.line("§8"));
    }

    public void showWinner(GameTeam winner){
        Bukkit.broadcastMessage(M.line("§8"));
        Bukkit.broadcastMessage(" §6§l" + game.getName() + " §6has ended!");
        Bukkit.broadcastMessage(" ");

        if (winner != null)
            Bukkit.broadcastMessage(" " + winner.getColoredName() + " §6has won the game.");
        else
            Bukkit.broadcastMessage(" §cUnfortunately, there was no winner this game.");

        Bukkit.broadcastMessage(" ");
        Bukkit.broadcastMessage(" §6You have just played the map §l" + game.getServerMap().getName() + " §6by " + game.getServerMap().getAuthor() + ".");
        Bukkit.broadcastMessage(M.line("§8"));
    }

    public void setCountdownState(){
        if (game == null) return;
        if (game.getState().equals(GameState.WAITING))
            game.setState(GameState.STARTING);
    }

    private void registerGameListeners(){
        getPluginManager().registerEvents(new JoinQuit(), getPlugin());
    }

    @EventHandler
    public void countdownUpdate(TickEvent event){
        if (game.getState().equals(GameState.STARTING) || game.getState().equals(GameState.WARMUP)) {
            if (Bukkit.getOnlinePlayers().size() < game.getMinPlayers()){
                Bukkit.broadcastMessage(M.error("Countdown stopped - not enough players!"));
                game.setState(GameState.WAITING);
                game.setTicks(45);
                return;
            }

            if (game.getTicks() == 5){
                startWarmup();
            }

            if (game.getTicks() == 0){
                startGame();
                Bukkit.broadcastMessage(M.regular("§aThe game has started!"));
                return;
            }

            if (game.getTicks() % 15 == 0){
                Bukkit.broadcastMessage(M.regular("The game starts in §e" + game.getTicks() + "s§7..."));
            }

            game.setTicks(game.getTicks() - 1);
        }
    }

    public void startGame(){
        game.setState(GameState.STARTING);
    }

    public void startWarmup(){
        game.setState(GameState.WARMUP);
        showDesc(game.getTicks());
    }

    public void endGame(){
        game.setState(GameState.ENDING);
    }
}
