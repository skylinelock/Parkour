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

public class SetNameCommand implements ICommand {

    private final ParkourPlugin plugin;

    public SetNameCommand(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player, Command command, String label, String[] args) {
        if (args.length < 3) {
            player.sendMessage(Util.NOT_ENOUGH_MESSAGE);
            return;
        }
        String enterId = args[1];
        List<Parkour> parkours = plugin.getParkours();

        for (Parkour parkour : parkours) {
            if (!parkour.getId().equals(enterId)) {
                continue;
            }
            String name = Util.buildString(2, args);
            parkour.setName(name);
            player.sendMessage(ChatColor.GREEN + "Parkour name updated");
            return;
        }
        player.sendMessage(ChatColor.RED + "Parkour name couldn't updated");
    }


}
