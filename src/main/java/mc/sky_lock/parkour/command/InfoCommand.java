package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.Util;
import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.ParkourMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author sky_lock
 */

class InfoCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;

    InfoCommand(ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("parkour.command.info")) {
            player.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        if (args.length < 2) {
            player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
            return;
        }
        ParkourManager parkourManager = handler.getParkourManager();
        String inputId = args[1];

        if (!parkourManager.getParkour(inputId).flatMap(parkour -> {
            Location startLoc = parkour.getStartPoint();
            Location endLoc = parkour.getEndPoint();
            Location preLoc = parkour.getPresetPoint();

            player.sendMessage(ChatColor.GREEN + "Id: " + ChatColor.WHITE + parkour.getId());
            player.sendMessage(ChatColor.GREEN + "Name: " + ChatColor.WHITE + parkour.getName());
            player.sendMessage(ChatColor.GREEN + "Start Point: " + ChatColor.WHITE + locationToString(startLoc));
            player.sendMessage(ChatColor.GREEN + "End Point: " + ChatColor.WHITE + locationToString(endLoc));
            player.sendMessage(ChatColor.GREEN + "Pre Point: " + ChatColor.WHITE + locationToString(preLoc));
            player.sendMessage(ChatColor.GREEN + "Save: " + ChatColor.WHITE + convertBool(parkour.canSave()));
            player.sendMessage(ChatColor.GREEN + "Locked: " + ChatColor.WHITE + convertBool(parkour.isLocked()));
            player.sendMessage(ChatColor.GREEN + "Active: " + ChatColor.WHITE + convertBool(parkour.isActive()));
            return Optional.of(parkour);
        }).isPresent()) {
            player.sendMessage(ParkourMessage.NOT_FOUND.getText());
        }
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

    private String convertBool(boolean bool) {
        return Util.convertBool(bool);
    }
}