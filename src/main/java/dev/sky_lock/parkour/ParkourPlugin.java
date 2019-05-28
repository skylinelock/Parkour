package dev.sky_lock.parkour;

import dev.sky_lock.parkour.api.ParkourManager;
import dev.sky_lock.parkour.command.CommandManager;
import dev.sky_lock.parkour.json.ParkourFile;
import dev.sky_lock.parkour.listener.EntityListener;
import dev.sky_lock.parkour.listener.PlayerListener;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

/**
 * @author sky_lock
 */

public class ParkourPlugin extends JavaPlugin {

    private static ParkourPlugin instance;
    @Getter
    private ParkourManager parkourManager;
    @Getter
    private PluginManager pluginManager;
    @Getter
    private ParkourLoader parkourLoader;

    @Override
    public void onLoad() {
        instance = this;
        parkourManager = new ParkourManager();
    }

    @Override
    public void onEnable() {
        pluginManager = getServer().getPluginManager();
        reloadResources();

        parkourLoader = new ParkourLoader(new ParkourFile(getDataFolder()), parkourManager);
        parkourLoader.load();

        CommandManager commandManager = new CommandManager();
        commandManager.register();
        registerListeners();

        new Metrics(this);
    }
    
    @Override
    public void onDisable() {
        parkourLoader.save();
    }

    private void registerListeners() {
        pluginManager.registerEvents(new PlayerListener(), this);
        pluginManager.registerEvents(new EntityListener(), this);
    }

    public static ParkourPlugin getInstance() {
        return instance;
    }

    public void reloadResources() {
        File dataFolder = getDataFolder();
        if (dataFolder.mkdirs()) {
            this.getLogger().info("プラグインのデータフォルダを作成しました");
        }
        getConfig().options().copyDefaults(true); //Returning default value
        saveDefaultConfig(); //If config.yml does not exist
        reloadConfig();
    }

}
