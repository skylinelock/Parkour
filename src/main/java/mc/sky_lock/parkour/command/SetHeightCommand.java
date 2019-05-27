package mc.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import mc.sky_lock.parkour.ParkourPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
public class SetHeightCommand extends BaseCommand {
    private final FileConfiguration config = ParkourPlugin.getInstance().getConfig();

    @Subcommand("setheight")
    public void onCommand(Player player, Integer height) {
        if (player.getLocation().getBlockY() <= height) {
            player.sendMessage(ChatColor.RED + "Too big argument");
            return;
        }
        config.set("respawn.height", height);
        player.sendMessage(ChatColor.GREEN + "Set height to " + height);
    }
}
