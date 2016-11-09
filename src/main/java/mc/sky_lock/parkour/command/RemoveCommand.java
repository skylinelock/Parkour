package mc.sky_lock.parkour.command;

import lombok.NonNull;
import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.FormatUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author sky_lock
 */

class RemoveCommand implements ICommand {

    private final ParkourPlugin plugin;

    RemoveCommand(@NonNull ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player, Command command, String label, String[] args) {
        if (args.length < 2) {
            player.sendMessage(FormatUtils.NOT_ENOUGH_MESSAGE);
            return;
        }

        List<Parkour> parkours = plugin.getParkours();
        String enterId = args[1];

        for (Parkour parkour : parkours) {
            if (parkour.getId().equals(enterId)) {
                parkours.remove(parkour);
                player.sendMessage(ChatColor.GREEN + "Remove successful");
                return;
            }
        }

        player.sendMessage(ChatColor.RED + "Remove failed");
    }
}
