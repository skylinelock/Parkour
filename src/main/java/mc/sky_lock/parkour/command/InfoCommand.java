package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.FormatUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author sky_lock
 */

class InfoCommand implements ICommand {

    private final ParkourPlugin plugin;

    InfoCommand(ParkourPlugin plugin) {
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
            if (!parkour.getId().equals(enterId)) {
                continue;
            }

            Location startLoc = parkour.getStartPoint();
            Location endLoc = parkour.getEndPoint();
            Location preLoc = parkour.getRespawnPoint();

            player.sendMessage(ChatColor.GREEN + "Id: " + ChatColor.RESET + parkour.getId());
            player.sendMessage(ChatColor.GREEN + "Name: " + ChatColor.RESET + parkour.getName());
            player.sendMessage(ChatColor.GREEN + "Start Point: " + ChatColor.RESET + locationToString(startLoc));
            player.sendMessage(ChatColor.GREEN + "End Point: " + ChatColor.RESET + locationToString(endLoc));
            player.sendMessage(ChatColor.GREEN + "Pre Point: " + ChatColor.RESET + locationToString(preLoc));
            player.sendMessage(ChatColor.GREEN + "Active: " + ChatColor.RESET + Boolean.toString(parkour.isActive()));
            return;
        }
        player.sendMessage(ChatColor.RED + "Info failed!");
    }

    private String locationToString(Location location) {
        if (location == null) {
            return null;
        }
        return location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ();
    }
}
