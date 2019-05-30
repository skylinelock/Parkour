package dev.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import dev.sky_lock.parkour.Formats;
import dev.sky_lock.parkour.Optionals;
import dev.sky_lock.parkour.ParkourPlugin;
import dev.sky_lock.parkour.api.ParkourManager;
import dev.sky_lock.parkour.message.ParkourMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
class InfoCommand extends BaseCommand {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("info")
    @CommandPermission("parkour.command.info")
    public void onCommand(Player player, String id) {
        ParkourManager parkourManager = plugin.getParkourManager();

        Optionals.ifPresentOrElse(parkourManager.getParkour(id), parkour -> {
            Location startLoc = parkour.getStartPoint();
            Location endLoc = parkour.getEndPoint();
            Location preLoc = parkour.getPresetPoint();
            player.sendMessage(ChatColor.GREEN + "Id: " + ChatColor.WHITE + parkour.getId());
            player.sendMessage(ChatColor.GREEN + "Name: " + ChatColor.WHITE + parkour.getName());
            player.sendMessage(ChatColor.GREEN + "Start Point: " + ChatColor.WHITE + Formats.roundDownCoordinateSet(startLoc));
            player.sendMessage(ChatColor.GREEN + "End Point: " + ChatColor.WHITE + Formats.roundDownCoordinateSet(endLoc));
            player.sendMessage(ChatColor.GREEN + "Pre Point: " + ChatColor.WHITE + Formats.roundDownCoordinateSet(preLoc));
            player.sendMessage(ChatColor.GREEN + "Save: " + ChatColor.WHITE + Formats.toCamelCase(parkour.doSave()));
            player.sendMessage(ChatColor.GREEN + "Locked: " + ChatColor.WHITE + Formats.toCamelCase(parkour.isLocked()));
            player.sendMessage(ChatColor.GREEN + "Active: " + ChatColor.WHITE + Formats.toCamelCase(parkour.isActive()));
        }, () -> player.sendMessage(ParkourMessage.NOT_FOUND.getText()));
    }
}