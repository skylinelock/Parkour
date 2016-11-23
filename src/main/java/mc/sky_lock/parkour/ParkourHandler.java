package mc.sky_lock.parkour;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.NonNull;
import mc.sky_lock.parkour.command.CommandHandler;
import mc.sky_lock.parkour.command.tabcomplete.ParkourTabComplete;
import mc.sky_lock.parkour.config.ConfigFile;
import mc.sky_lock.parkour.json.ParkourFile;
import mc.sky_lock.parkour.listener.EntityListener;
import mc.sky_lock.parkour.listener.PlayerListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;

import java.util.List;

/**
 * @author sky_lock
 */

public class ParkourHandler {

    @Getter
    private final ParkourPlugin plugin;
    @Getter
    private final PluginManager pluginManager;
    private final PluginCommand parkourCommand;
    @Getter
    private ConfigFile configFile;
    @Getter
    private ParkourFile parkourFile;
    @Getter
    private List<Parkour> parkours;
    private MongoClient dbClient;
    @Getter
    private MongoDatabase database;

    public ParkourHandler(@NonNull ParkourPlugin plugin) {
        this.plugin = plugin;
        this.pluginManager = plugin.getServer().getPluginManager();
        this.parkourCommand = plugin.getCommand("parkour");
    }

    void onEnable() {
        configFile = new ConfigFile(plugin);

        parkourFile = new ParkourFile(plugin.getDataFolder());
        parkours = parkourFile.loadParkours();

        registerListeners();
        registerParkourCommands();
        registerParkourTabCompleter();

        connectDB();
    }

    void onDisable() {
        parkourFile.saveParkours(parkours);
        disconnectDB();
    }

    private void registerParkourCommands() {
        parkourCommand.setExecutor(new CommandHandler(this));
        parkourCommand.setTabCompleter(new ParkourTabComplete(this));
    }

    private void registerParkourTabCompleter() {
        parkourCommand.setTabCompleter(new ParkourTabComplete(this));
    }

    private void registerListeners() {
        pluginManager.registerEvents(new PlayerListener(this), plugin);
        pluginManager.registerEvents(new EntityListener(), plugin);
    }

    private void connectDB() {
        dbClient = new MongoClient("localhost", 27017);
        this.database = dbClient.getDatabase("parkourRecords");
    }

    private void disconnectDB() {
        dbClient.close();
    }

}
