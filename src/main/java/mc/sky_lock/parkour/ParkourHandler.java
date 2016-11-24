package mc.sky_lock.parkour;

import lombok.Getter;
import lombok.NonNull;
import mc.sky_lock.parkour.command.CommandHandler;
import mc.sky_lock.parkour.command.tabcomplete.ParkourTabCompleter;
import mc.sky_lock.parkour.config.ConfigFile;
import mc.sky_lock.parkour.json.ParkourFile;
import mc.sky_lock.parkour.listener.EntityListener;
import mc.sky_lock.parkour.listener.PlayerListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.craftbukkit.v1_11_R1.CraftServer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

    void onEnable() {
        configFile = new ConfigFile(plugin);

        parkourFile = new ParkourFile(plugin.getDataFolder());
        parkours = parkourFile.loadParkours();

        registerListeners();
        registerParkourCommand();
    }

    void onDisable() {
        parkourFile.saveParkours(parkours);
    }

    private void registerListeners() {
        pluginManager.registerEvents(new PlayerListener(this), plugin);
        pluginManager.registerEvents(new EntityListener(), plugin);
    }

    private void registerParkourCommand() {
        Constructor<?> constructor;
        try {
            constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
        } catch (NoSuchMethodException ex) {
            return;
        }
        constructor.setAccessible(true);
        PluginCommand parkourCmd;
        try {
            parkourCmd = (PluginCommand)constructor.newInstance("parkour", plugin);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            return;
        }
        parkourCmd.setExecutor(new CommandHandler(this));
        parkourCmd.setTabCompleter(new ParkourTabCompleter(this));
        ((CraftServer)getPlugin().getServer()).getCommandMap().register("parkour", parkourCmd);
    }

}
