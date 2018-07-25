package mc.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.message.ParkourMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author sky_lock
 */

@CommandAlias("parkour|pk")
class SetEndCommand extends BaseCommand {
    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("setend")
    @CommandPermission("parkour.command.setend")
    public void onCommand(Player player, String id) {
        ParkourManager parkourManager = plugin.getParkourManager();
        parkourManager.getParkour(id).map(parkour -> {
            parkour.setEndPoint(player.getLocation());
            player.sendMessage(ChatColor.GREEN + "Set Parkour " + parkour.getId() + "'s endpoint");
            return Optional.of(parkour);
        }).orElseGet(() -> {
            player.sendMessage(ParkourMessage.NOT_FOUND.getText());
            return Optional.empty();
        });
    }
}