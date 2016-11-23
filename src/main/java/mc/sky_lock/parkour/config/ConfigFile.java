package mc.sky_lock.parkour.config;

import lombok.NonNull;
import mc.sky_lock.parkour.ParkourPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author sky_lock
 */

public class ConfigFile {
    private final ParkourPlugin plugin;
    private final FileConfiguration config;

    public ConfigFile(@NonNull ParkourPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        config.options().copyDefaults(true);

        File dataFolder = plugin.getDataFolder();
        dataFolder.mkdirs();
        File configFile = new File(dataFolder, "config.yml");

        try {
            config.save(configFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void save(@NonNull ConfigElement element, @NonNull String value) {
        config.set(element.getElement(), value);
    }

    public Object load(@NonNull ConfigElement element) {
        String path = element.getElement();
        if (element == ConfigElement.TELEPORT_HEIGHT) {
            if (config.isInt(path)) {
                return config.getInt(path);
            }
        }
        return config.get(path);
    }






}
