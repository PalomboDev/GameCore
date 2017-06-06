package me.palombo.gamecore.map;

import me.palombo.gamecore.module.Module;
import me.palombo.gamecore.util.UtilZip;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class MapManager extends Module {

    public MapManager(JavaPlugin plugin) {
        super(plugin);
    }

    public void clearOldMaps(){
        Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), () -> {
            for (File f : Bukkit.getWorldContainer().listFiles()) {
                if (!f.isDirectory())
                    continue;

                if (!f.getName().contains("MAP_"))
                    continue;

                String name = f.getName();

                for (File del : f.listFiles()) {
                    del.delete();
                }

                if (f.exists())
                    f.delete();

                System.out.println("-------------------------");
                System.out.println("Removed Map: " + name);
                System.out.println("-------------------------");
            }
        });
    }

    public void setupMap(Map map){
        if (map == null)
            return;

        final String folderName = "MAP_" + map.getZip();
        new File(Bukkit.getWorldContainer().getPath() + File.separator + folderName).mkdir();

        UtilZip.unzipToFile(Bukkit.getWorldContainer().getPath() + File.separator + ".." + File.separator + ".." + File.separator + "maps" + File.separator + map.getZip() + ".zip",
                Bukkit.getWorldContainer().getPath() + File.separator + folderName);

        Bukkit.createWorld(new WorldCreator(folderName));

        Bukkit.getWorld(folderName).getEntities().stream().filter(e -> e instanceof LivingEntity).forEach(Entity::remove);

        Bukkit.getWorld(folderName).setWeatherDuration(1); // No weather in worlds.
    }

    public Map selectRandomSetup(){
        if (getMaps().isEmpty()){
            getPlugin().getLogger().info("No maps found.");
            return null;
        }

        final Map map = getMaps().get(new Random().nextInt(getMaps().size()));

        setupMap(map);

        return map;
    }

    public ArrayList<Map> getMaps() {
        ArrayList<Map> maps = new ArrayList<>();

        File directory = new File(".." + File.separator + ".." + File.separator + "maps");

        if (!directory.exists())
            directory.mkdir();

        for (File mapFile : directory.listFiles()){
            if (!mapFile.getName().contains(".yml"))
                continue;

            FileConfiguration map = YamlConfiguration.loadConfiguration(mapFile);
            Map km = new Map(
                    map.getString("name"),
                    map.getString("author"),
                    map.getString("zip"),
                    map.getStringList("data")
            );

            maps.add(km);
        }

        return maps;
    }

    @EventHandler
    public void weather(WeatherChangeEvent e){
        e.setCancelled(true);
    }
}
