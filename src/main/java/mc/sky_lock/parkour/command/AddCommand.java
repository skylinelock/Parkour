package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author sky_lock
 */

public class AddCommand implements ICommand {

    private final ParkourPlugin plugin;

    public AddCommand(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player, Command command, String label, String[] args) {
        if (args.length < 2) {
            player.sendMessage(Util.NOT_ENOUGH_MESSAGE);
            return;
        }
        String enterId = args[1];
        List<Parkour> parkours = plugin.getParkours();

        for (Parkour parkour : parkours) {
            if (parkour.getId().equalsIgnoreCase(enterId)) {
                player.sendMessage(ChatColor.RED + "The ID already exists");
                return;
            }
        }
        Parkour newParkour = new Parkour(enterId);
        plugin.getParkours().add(newParkour);
        player.sendMessage(ChatColor.GREEN + "Add parkour successful");
    }
}
