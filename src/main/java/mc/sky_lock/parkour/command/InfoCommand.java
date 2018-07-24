package mc.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import mc.sky_lock.parkour.FormatUtils;
import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.message.ParkourMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author sky_lock
 */

@CommandAlias("parkour|pk")
class InfoCommand extends BaseCommand {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("info")
    @CommandPermission("parkour.command.info")
    public void onCommand(Player player, String id) {
        ParkourManager parkourManager = plugin.getParkourManager();

        if (!parkourManager.getParkour(id).flatMap(parkour -> {
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
        return FormatUtils.formatBoolean(bool);
    }
}