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
class SetNameCommand extends BaseCommand {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("setname")
    @CommandPermission("parkour.command.rename")
    public void onCommand(Player player, String id, String renameTo) {
        ParkourManager parkourManager = plugin.getParkourManager();
        parkourManager.getParkour(id).map(parkour -> {
            parkour.setName(renameTo);

            player.sendMessage(ChatColor.GREEN + "Set Parkour " + parkour.getId() + "'s name to " + parkour.getName());
            return Optional.of(parkour);
        }).orElseGet(() -> {
            player.sendMessage(ParkourMessage.NOT_FOUND.getText());
            return null;
        });
    }
}