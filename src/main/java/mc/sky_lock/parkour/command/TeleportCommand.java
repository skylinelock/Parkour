package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.ParkourMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author sky_lock
 */

public class TeleportCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;

    TeleportCommand(ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("parkour.command.teleport")) {
            player.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        ParkourManager parkourManager = handler.getParkourManager();
        String inputId = args[1];

        if (!parkourManager.getParkour(inputId).flatMap(parkour -> {
            Location prePoint = parkour.getPresetPoint();
            if (prePoint == null) {
                player.sendMessage(ParkourMessage.PRESET_NOT_FOUND.getText());
            } else {
                player.teleport(prePoint);
                player.sendMessage(ChatColor.GREEN + "Teleported to Parkour " + parkour.getId());
            }
            return Optional.of(parkour);
        }).isPresent()) {
            player.sendMessage(ParkourMessage.NOT_FOUND.getText());
        }
    }
}
