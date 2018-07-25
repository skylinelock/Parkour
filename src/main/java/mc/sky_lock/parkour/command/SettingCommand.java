package mc.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;
import mc.sky_lock.parkour.ParkourPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

@CommandAlias("parkour|pk")
public class SettingCommand extends BaseCommand {
    private final FileConfiguration config = ParkourPlugin.getInstance().getConfig();

    @Subcommand("setting")
    public void onCommand(Player player, String key, @Optional boolean bool) {
        
    }
}
