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
public class SaveCommand extends BaseCommand {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("save")
    @CommandPermission("parkour.command.save")
    public void onCommand(Player player, String id) {
        ParkourManager parkourManager = plugin.getParkourManager();

        Optionals.ifPresentOrElse(parkourManager.getParkour(id), parkour -> {
            if (parkour.canSave()) {
                parkour.setSave(false);
            } else {
                parkour.setSave(true);
            }
            player.sendMessage(ChatColor.GREEN + "Turned save successful");
        }, () -> player.sendMessage(ParkourMessage.NOT_FOUND.getText()));
    }
}