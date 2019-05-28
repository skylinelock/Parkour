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
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
class SetNameCommand extends BaseCommand {
    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("setname")
    @CommandPermission("parkour.command.rename")
    public void onCommand(Player player, String id, String renameTo) {
        ParkourManager parkourManager = plugin.getParkourManager();
        Optionals.ifPresentOrElse(parkourManager.getParkour(id), parkour -> {
            parkour.setName(renameTo);
            player.sendMessage(ChatColor.GREEN + "Set Parkour " + parkour.getId() + "'s name to " + parkour.getName());
        }, () -> player.sendMessage(ParkourMessage.NOT_FOUND.getText()));
    }
}