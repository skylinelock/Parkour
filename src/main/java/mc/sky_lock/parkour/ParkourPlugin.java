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
import java.io.IOException;

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
    private ParkourFile parkourFile;
    @Getter
    private PluginLogger pluginLogger;
    private CommandManager commandManager;

    @Override
    public void onLoad() {
        instance = this;
        parkourManager = new ParkourManager();
    }

    @Override
    public void onEnable() {
        commandManager = new CommandManager();
        commandManager.register();

        pluginManager = getServer().getPluginManager();
        pluginLogger = new PluginLogger();

        this.reloadConfiguration();

        parkourFile = new ParkourFile(getDataFolder());
        try {
            parkourFile.loadParkours().forEach(parkourManager::addParkour);
        } catch (IOException ex) {
            pluginLogger.out(ChatColor.RED + "parkours.jsonからのParkourの読み込みに失敗しました");
        }

        registerListeners();
    }
    
    @Override
    public void onDisable() {
        try {
            parkourFile.saveParkours(parkourManager.getParkours());
        } catch (IOException ex) {
            pluginLogger.out(ChatColor.RED + "Parkour情報のparkours.jsonへの保存に失敗しました");
        }
    }

    private void registerListeners() {
        pluginManager.registerEvents(new PlayerListener(), this);
        pluginManager.registerEvents(new EntityListener(), this);
    }

    public static ParkourPlugin getInstance() {
        return instance;
    }

    public void reloadConfiguration() {
        File dataFolder = getDataFolder();
        if (dataFolder.mkdirs()) {
            pluginLogger.out(ChatColor.GRAY + "プラグインのデータフォルダを作成しました");
        }
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        reloadConfig();
    }

}
