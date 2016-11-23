package mc.sky_lock.parkour.command;

import lombok.NonNull;
import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.message.FailedMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author sky_lock
 */

class InfoCommand implements ICommand {

    private final ParkourHandler handler;

    public InfoCommand(@NonNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(Player player, Command command, String label, String[] args) {
        if (args.length < 2) {
            player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
            return;
        }
        String inputId = args[1];
        List<Parkour> parkours = handler.getParkours();

        if (parkours == null || parkours.isEmpty()) {
            player.sendMessage(FailedMessage.INFO.getText());
            return;
        }

        for (Parkour parkour : parkours) {
            if (!parkour.getId().equals(inputId)) {
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
        player.sendMessage(FailedMessage.INFO.getText());
    }

    private String locationToString(@NonNull Location location) {
        return location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ();
    }
}
