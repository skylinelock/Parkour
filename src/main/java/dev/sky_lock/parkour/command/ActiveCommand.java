package dev.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import dev.sky_lock.parkour.message.ParkourMessage;
import dev.sky_lock.parkour.ParkourPlugin;
import dev.sky_lock.parkour.api.Parkour;
import dev.sky_lock.parkour.api.ParkourManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
class ActiveCommand extends BaseCommand {
    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("active")
    @CommandPermission("parkour.command.active")
    public void onCommand(Player player, String id) {
        ParkourManager parkourManager = plugin.getParkourManager();
        parkourManager.getParkour(id).map(parkour -> {
            if (!checkParkour(parkour)) {
                player.sendMessage(ParkourMessage.NOT_ENOUGH_ELEMENTS.getText());
                return Optional.of(parkour);
            }
            parkour.setActive(true);
            player.sendMessage(ChatColor.GREEN + "Parkour " + parkour.getId() + " is activated");
            return Optional.of(parkour);
        }).orElseGet(() -> {
            player.sendMessage(ParkourMessage.NOT_FOUND.getText());
            return Optional.empty();
        });
    }

    private boolean checkParkour(Parkour parkour) {
        return parkour.getStartPoint() != null && parkour.getEndPoint() != null && parkour.getPresetPoint() != null && parkour.getName() != null;
    }
}