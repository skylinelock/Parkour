package dev.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import dev.sky_lock.parkour.Optionals;
import dev.sky_lock.parkour.ParkourPlugin;
import dev.sky_lock.parkour.api.Parkour;
import dev.sky_lock.parkour.api.ParkourManager;
import dev.sky_lock.parkour.message.ParkourMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
class AddCommand extends BaseCommand {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("add")
    @CommandPermission("parkour.command.add")
    public void onCommand(Player player, String id) {
        ParkourManager parkourManager = plugin.getParkourManager();

        Optionals.ifPresentOrElse(parkourManager.getParkour(id), parkour -> {
            player.sendMessage(ParkourMessage.ALREADY_EXISTS.getText());
        }, () -> {
            Parkour newParkour = new Parkour(id);
            parkourManager.add(newParkour);
            player.sendMessage(ChatColor.GREEN + "Parkour " + newParkour.getId() + " added");
        });
    }
}