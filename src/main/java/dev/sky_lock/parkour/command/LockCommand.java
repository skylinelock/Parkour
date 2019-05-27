package dev.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import dev.sky_lock.parkour.message.ParkourMessage;
import dev.sky_lock.parkour.ParkourPlugin;
import dev.sky_lock.parkour.api.ParkourManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
public class LockCommand extends BaseCommand {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("lock")
    @CommandPermission("parkour.command.lock")
    public void onCommand(Player player, String id) {
        ParkourManager parkourManager = plugin.getParkourManager();

        parkourManager.getParkour(id).map(parkour -> {
            parkour.setActive(false);
            player.sendMessage(ChatColor.GREEN + "Parkour " + parkour.getId() + " is locked");
            return Optional.of(parkour);
        }).orElseGet(() -> {
            player.sendMessage(ParkourMessage.NOT_FOUND.getText());
            return Optional.empty();
        });
    }
}