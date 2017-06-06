package me.palombo.gamecore.game;

import me.palombo.gamecore.GameCore;
import me.palombo.gamecore.game.rules.RuleSet;
import me.palombo.gamecore.game.team.GameTeam;
import me.palombo.gamecore.map.Map;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String name;
    private List<String> description;
    private RuleSet ruleSet;
    private List<GameTeam> teams;
    private GameExecutor executor;

    private GameState state;
    private int ticks;
    private Map serverMap;

    private int minPlayers;
    private int maxPlayers;

    public Game(String name, List<String> description, GameExecutor executor) {
        this.name = name;
        this.description = description;

        this.ruleSet = new RuleSet();
        this.teams = new ArrayList<>();
        this.executor = executor;

        this.state = GameState.WAITING;
        this.ticks = 45;

        this.minPlayers = 2;
        this.maxPlayers = 16;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Map getServerMap() {
        return serverMap;
    }

    public void setServerMap(Map serverMap) {
        this.serverMap = serverMap;
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public List<GameTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<GameTeam> teams) {
        {
            //Add specs default
            GameTeam spec = new GameTeam("Spectators", "§7");
            spec.setVisible(false);
            teams.add(spec);
        }

        this.teams = teams;

        for (GameTeam team : this.teams){
            GameCore.get().getGameManager().getTeamManager().registerTeam(team);
        }
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public void setRuleSet(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return description;
    }

    public GameExecutor getExecutor() {
        return executor;
    }
}
