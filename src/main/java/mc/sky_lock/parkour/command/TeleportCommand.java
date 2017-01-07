package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.ParkourMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        String inputId = args[1];
        Parkour parkour = handler.getParkourManager().getParkour(inputId);

        if (parkour == null) {
            player.sendMessage(ParkourMessage.NOT_FOUND.getText());
            return;
        }

        player.teleport(parkour.getPresetPoint());
        player.sendMessage(ChatColor.GREEN + "Teleported to Parkour " + parkour.getId());
    }
}
