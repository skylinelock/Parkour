package mc.sky_lock.parkour.command;

import lombok.NonNull;
import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.message.FailedMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author sky_lock
 */

class InfoCommand implements ICommand {

    private final ParkourHandler handler;

    InfoCommand(@NonNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(Player player, Command command, String label, String[] args) {
        if (args.length < 2) {
            if (args.length < 2) {
                player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
                return;
            }
            String inputId = args[1];
            List<Parkour> parkours = handler.getParkours();

            for (Parkour parkour : parkours) {
                if (!parkour.getId().equals(inputId)) {
                    continue;
                }

                Location startLoc = parkour.getStartPoint();
                Location endLoc = parkour.getEndPoint();
                Location preLoc = parkour.getPresetPoint();

                player.sendMessage(ChatColor.GREEN + "Id: " + ChatColor.RESET + parkour.getId());
                player.sendMessage(ChatColor.GREEN + "Name: " + ChatColor.RESET + parkour.getName());
                player.sendMessage(ChatColor.GREEN + "Start Point: " + ChatColor.RESET + locationToString(startLoc));
                player.sendMessage(ChatColor.GREEN + "End Point: " + ChatColor.RESET + locationToString(endLoc));
                player.sendMessage(ChatColor.GREEN + "Pre Point: " + ChatColor.RESET + locationToString(preLoc));
                player.sendMessage(ChatColor.GREEN + "Active: " + ChatColor.RESET + boolString(parkour.isActive()));
                return;
            }
            player.sendMessage(FailedMessage.INFO.getText());
        }
    }

    private String locationToString(Location location) {
        if (location == null) {
            return "null";
        }
        BigDecimal x = new BigDecimal(location.getX());
        BigDecimal y = new BigDecimal(location.getY());
        BigDecimal z = new BigDecimal(location.getZ());

        String scaledX = x.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
        String scaledY = y.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
        String scaledZ = z.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
        return scaledX + " " + scaledY + " " + scaledZ;
    }

    private String boolString(boolean bool) {
        if (bool) {
            return "Yes";
        }
        return "No";
    }
}
