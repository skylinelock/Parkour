package mc.sky_lock.parkour;

import lombok.Getter;
import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.command.CommandManager;
import mc.sky_lock.parkour.json.ParkourFile;
import mc.sky_lock.parkour.listener.EntityListener;
import mc.sky_lock.parkour.listener.PlayerListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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
    private PluginLogger pluginLogger;
    @Getter
    private ParkourLoader parkourLoader;

    @Override
    public void onLoad() {
        instance = this;
        parkourManager = new ParkourManager();
    }

    @Override
    public void onEnable() {
        CommandManager commandManager = new CommandManager();
        commandManager.register();

        pluginManager = getServer().getPluginManager();
        pluginLogger = new PluginLogger();

        this.reloadResources();

        parkourLoader = new ParkourLoader(new ParkourFile(getDataFolder()), parkourManager);
        parkourLoader.load();

        registerListeners();

        Metrics metrics = new Metrics(this);
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
            pluginLogger.out(ChatColor.GRAY + "プラグインのデータフォルダを作成しました");
        }
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        reloadConfig();
    }

}
