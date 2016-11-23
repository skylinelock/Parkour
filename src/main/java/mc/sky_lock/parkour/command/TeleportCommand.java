package mc.sky_lock.parkour.command;

import lombok.NonNull;
import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.SuccessMessage;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author sky_lock
 */

public class TeleportCommand implements ICommand {

    private final ParkourHandler handler;

    public TeleportCommand(@NonNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(Player player, Command command, String label, String[] args) {
        String inputId = args[1];
        List<Parkour> parkours = handler.getParkours();

        for (Parkour parkour : parkours) {
            if (parkour.getId().equals(inputId)) {
                player.teleport(parkour.getRespawnPoint());
                player.sendMessage(SuccessMessage.TELEPORT.getText());
                return;
            }
        }
        player.sendMessage(FailedMessage.TELEPORT.getText());
    }
}
