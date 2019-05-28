package dev.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import dev.sky_lock.parkour.Optionals;
import dev.sky_lock.parkour.message.ParkourMessage;
import dev.sky_lock.parkour.ParkourPlugin;
import dev.sky_lock.parkour.api.ParkourManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
class TeleportCommand extends BaseCommand {
    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("teleport")
    @CommandPermission("parkour.command.teleport")
    public void onCommand(Player player, String id) {
        ParkourManager parkourManager = plugin.getParkourManager();

        Optionals.ifPresentOrElse(parkourManager.getParkour(id), parkour -> {
            Location prePoint = parkour.getPresetPoint();
            if (prePoint == null) {
                player.sendMessage(ParkourMessage.PRESET_NOT_FOUND.getText());
            } else {
                player.teleport(prePoint);
                player.sendMessage(ChatColor.GREEN + "Teleported to " + parkour.getId() + " parkour");
            }
        }, () -> player.sendMessage(ParkourMessage.NOT_FOUND.getText()));
    }
}
