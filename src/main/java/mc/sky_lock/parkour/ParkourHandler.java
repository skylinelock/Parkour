package mc.sky_lock.parkour;

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

    public ParkourHandler(@NonNull ParkourPlugin plugin) {
        this.plugin = plugin;
        this.pluginManager = plugin.getServer().getPluginManager();
        this.parkourCommand = plugin.getCommand("parkour");
    }

    public void onEnable() {
        configFile = new ConfigFile(plugin);

        parkourFile = new ParkourFile(plugin.getDataFolder());
        parkours = parkourFile.loadParkours();

        registerListeners();
        registerParkourCommands();
        registerParkourTabCompleter();
    }

    public void onDisable() {
        parkourFile.saveParkours(parkours);
    }

    public void registerParkourCommands() {
        parkourCommand.setExecutor(new CommandHandler(this));
        parkourCommand.setTabCompleter(new ParkourTabComplete(this));
    }

    public void registerParkourTabCompleter() {
        parkourCommand.setTabCompleter(new ParkourTabComplete(this));
    }

    public void registerListeners() {
        pluginManager.registerEvents(new PlayerListener(this), plugin);
        pluginManager.registerEvents(new EntityListener(), plugin);
    }

}
