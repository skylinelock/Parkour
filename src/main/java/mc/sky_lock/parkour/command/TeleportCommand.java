package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.SuccessMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author sky_lock
 */

public class TeleportCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;

    TeleportCommand(@NotNull ParkourHandler handler) {
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
        Parkour parkour = handler.getParkour(inputId);

        if (parkour == null) {
            player.sendMessage(FailedMessage.TELEPORT.getText());
            return;
        }

        player.teleport(parkour.getPresetPoint());
        player.sendMessage(SuccessMessage.TELEPORT.getText());
    }
}
