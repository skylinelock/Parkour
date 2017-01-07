package mc.sky_lock.parkour;

import mc.sky_lock.parkour.command.CommandHandler;
import mc.sky_lock.parkour.command.tabcomplete.ParkourTabCompleter;
import mc.sky_lock.parkour.database.MongoDBManager;
import mc.sky_lock.parkour.json.ParkourFile;
import mc.sky_lock.parkour.listener.EntityListener;
import mc.sky_lock.parkour.listener.PlayerListener;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_11_R1.CraftServer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author sky_lock
 */

public class ParkourHandler {

    private final ParkourPlugin plugin;
    private final PluginManager pluginManager;
    private final MongoDBManager dbManager;
    private final ParkourManager parkourManager;
    private ParkourFile parkourFile;

    private PluginLogger logger;

    public ParkourHandler(ParkourPlugin plugin) {
        this.plugin = plugin;
        this.pluginManager = plugin.getServer().getPluginManager();
        this.dbManager = new MongoDBManager(this);
        this.parkourManager = new ParkourManager();
    }

    void onEnable() {
        logger = new PluginLogger();

        dbManager.connect();

        reloadConfig();

        parkourFile = new ParkourFile(plugin.getDataFolder());
        try {
            parkourFile.loadParkours().forEach(parkourManager::addParkour);
        } catch (IOException ex) {
            logger.out(ChatColor.RED + "parkours.jsonからのParkourの読み込みに失敗しました");
        }

        registerListeners();
        registerParkourCommand();
    }

    void onDisable() {
        try {
            parkourFile.saveParkours(parkourManager.getParkours());
        } catch (IOException ex) {
            logger.out(ChatColor.RED + "Parkour情報のparkours.jsonへの保存に失敗しました");
        }
        dbManager.close();
    }

    private void registerListeners() {
        pluginManager.registerEvents(new PlayerListener(this), plugin);
        pluginManager.registerEvents(new EntityListener(this), plugin);
    }

    private void registerParkourCommand() {
        Constructor<?> constructor;
        try {
            constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
        } catch (NoSuchMethodException ex) {
            logger.out(ChatColor.RED + "コマンドの登録に失敗しました");
            return;
        }
        constructor.setAccessible(true);
        PluginCommand parkourCmd;
        try {
            parkourCmd = (PluginCommand) constructor.newInstance("parkour", plugin);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            logger.out(ChatColor.RED + "コマンドの登録に失敗しました");
            return;
        }
        parkourCmd.setExecutor(new CommandHandler(this));
        parkourCmd.setTabCompleter(new ParkourTabCompleter(this));
        ((CraftServer) plugin.getServer()).getCommandMap().register("parkour", parkourCmd);
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    public ParkourFile getParkourFile() {
        return parkourFile;
    }

    public PluginLogger getLogger() {
        return logger;
    }

    public MongoDBManager getMongoDBManager() {
        return dbManager;
    }

    public ParkourManager getParkourManager() {
        return parkourManager;
    }

    public void disablePlugin() {
        pluginManager.disablePlugin(plugin);
    }

    public void reloadConfig() {
        File dataFolder = plugin.getDataFolder();
        if (dataFolder.mkdirs()) {
            logger.out(ChatColor.GRAY + "プラグインのデータフォルダを作成しました");
        }
        getConfig().options().copyDefaults(true);
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
    }

}
