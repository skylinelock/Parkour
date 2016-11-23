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

class SetStartCommand implements ICommand {

    private final ParkourHandler handler;

    public SetStartCommand(@NonNull ParkourHandler handler) {
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
            player.sendMessage(FailedMessage.SET_START.getText());
            return;
        }

        for (Parkour parkour : parkours) {
            if (parkour.getId().equals(inputId)) {
                parkour.setStartPoint(player.getLocation());
                player.sendMessage(SuccessMessage.SET_START.getText());
                return;
            }
        }
        player.sendMessage(FailedMessage.SET_START.getText());
    }
}
