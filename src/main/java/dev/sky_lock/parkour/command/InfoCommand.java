package dev.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import dev.sky_lock.parkour.message.ParkourMessage;
import dev.sky_lock.parkour.Utils;
import dev.sky_lock.parkour.ParkourPlugin;
import dev.sky_lock.parkour.api.ParkourManager;
import org.apache.commons.lang.BooleanUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;

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

        parkourManager.getParkour(id).map(parkour -> {
            Location startLoc = parkour.getStartPoint();
            Location endLoc = parkour.getEndPoint();
            Location preLoc = parkour.getPresetPoint();

            player.sendMessage(ChatColor.GREEN + "Id: " + ChatColor.WHITE + parkour.getId());
            player.sendMessage(ChatColor.GREEN + "Name: " + ChatColor.WHITE + parkour.getName());
            player.sendMessage(ChatColor.GREEN + "Start Point: " + ChatColor.WHITE + Utils.roundDownCoordinateSet(startLoc));
            player.sendMessage(ChatColor.GREEN + "End Point: " + ChatColor.WHITE + Utils.roundDownCoordinateSet(endLoc));
            player.sendMessage(ChatColor.GREEN + "Pre Point: " + ChatColor.WHITE + Utils.roundDownCoordinateSet(preLoc));
            player.sendMessage(ChatColor.GREEN + "Save: " + ChatColor.WHITE + toYesOrNo(parkour.canSave()));
            player.sendMessage(ChatColor.GREEN + "Locked: " + ChatColor.WHITE + toYesOrNo(parkour.isLocked()));
            player.sendMessage(ChatColor.GREEN + "Active: " + ChatColor.WHITE + toYesOrNo(parkour.isActive()));
            return Optional.of(parkour);
        }).orElseGet(() -> {
            player.sendMessage(ParkourMessage.NOT_FOUND.getText());
            return Optional.empty();
        });
    }

    private String toYesOrNo(boolean bool) {
        return BooleanUtils.toString(bool, "Yes", "No");
    }
}