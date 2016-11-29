package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.message.FailedMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author sky_lock
 */

class InfoCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;

    InfoCommand(@NotNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (args.length < 2) {
            player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
            return;
        }
        List<Parkour> parkours = handler.getParkours();
        String inputId = args[1];

        for (Parkour parkour : parkours) {
            if (!parkour.getId().equalsIgnoreCase(inputId)) {
                continue;
            }

            Location startLoc = parkour.getStartPoint();
            Location endLoc = parkour.getEndPoint();
            Location preLoc = parkour.getPresetPoint();

            player.sendMessage(ChatColor.GREEN + "Id: " + ChatColor.WHITE + parkour.getId());
            player.sendMessage(ChatColor.GREEN + "Name: " + ChatColor.WHITE + parkour.getName());
            player.sendMessage(ChatColor.GREEN + "Start Point: " + ChatColor.WHITE + locationToString(startLoc));
            player.sendMessage(ChatColor.GREEN + "End Point: " + ChatColor.WHITE + locationToString(endLoc));
            player.sendMessage(ChatColor.GREEN + "Pre Point: " + ChatColor.WHITE + locationToString(preLoc));
            player.sendMessage(ChatColor.GREEN + "Active: " + ChatColor.WHITE + boolString(parkour.isActive()));
            return;
        }
        player.sendMessage(FailedMessage.INFO.getText());

    }

    private String locationToString(Location location) {
        if (location == null) {
            return null;
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
