package mc.sky_lock.parkour;

import lombok.Getter;
import mc.sky_lock.parkour.command.Commands;
import mc.sky_lock.parkour.command.tabcomplete.ParkourTabComplete;
import mc.sky_lock.parkour.json.ParkourConfig;
import mc.sky_lock.parkour.listener.EntityListener;
import mc.sky_lock.parkour.listener.PlayerListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 *
 * @author sky_lock
 */
public class ParkourPlugin extends JavaPlugin {

    @Getter
    private ParkourConfig parkourConfig;
    @Getter
    private List<Parkour> parkours;
    
    @Override
    public void onEnable() {
        parkourConfig = new ParkourConfig(this.getDataFolder());
        parkours = parkourConfig.getParkours();
        
        PluginCommand parkourCommand = getCommand("parkour");
        parkourCommand.setExecutor(new Commands(this));
        parkourCommand.setTabCompleter(new ParkourTabComplete(this));

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityListener(), this);

    }
    
    @Override
    public void onDisable() {
        parkourConfig.saveParkours(parkours);
    }
}
