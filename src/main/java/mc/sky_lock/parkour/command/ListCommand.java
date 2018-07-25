package mc.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.api.Parkour;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
class ListCommand extends BaseCommand {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("list")
    @CommandPermission("parkour.command.list")
    public void onCommand(Player player) {
        List<Parkour> parkours = plugin.getParkourManager().getParkours();

        player.sendMessage(ChatColor.GREEN + "------  [" + ChatColor.WHITE + "Parkour List" + ChatColor.GREEN + "]  ------");

        parkours.stream()
                .filter(Parkour::isActive)
                .forEach(parkour -> player.sendMessage(
                        ChatColor.GREEN + "Id : " + ChatColor.WHITE + parkour.getId() + ChatColor.GREEN + " Name : " + ChatColor.WHITE + parkour.getName()
                        )
                );
        parkours.stream()
                .filter(parkour -> !parkour.isActive())
                .forEach(parkour -> player.sendMessage(
                        ChatColor.RED + "Id : " + ChatColor.WHITE + parkour.getId() + ChatColor.RED + " Name : " + ChatColor.WHITE + parkour.getName()
                        )
                );

        player.sendMessage(ChatColor.GREEN + "---------------------------");
    }

}