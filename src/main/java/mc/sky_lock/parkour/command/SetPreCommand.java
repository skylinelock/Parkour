package mc.sky_lock.parkour.command;

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

public class SetPreCommand implements ICommand {

    private final ParkourPlugin plugin;

    public SetPreCommand(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player, Command command, String label, String[] args) {
        if (args.length < 2) {
            player.sendMessage(FormatUtils.NOT_ENOUGH_MESSAGE);
            return;
        }
        String enterId = args[1];
        List<Parkour> parkours = plugin.getParkours();

        for (Parkour parkour : parkours) {
            if (parkour.getId().equals(enterId)) {
                parkour.setRespawnPoint(player.getLocation());
                player.sendMessage(ChatColor.GREEN + "Set pre successful");
                return;
            }
        }
        player.sendMessage(ChatColor.RED + "Set pre failed");
    }
}
