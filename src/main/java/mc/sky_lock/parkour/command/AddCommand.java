package mc.sky_lock.parkour.command;

import lombok.NonNull;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.message.SuccessMessage;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author sky_lock
 */

class AddCommand implements ICommand {

    private final ParkourPlugin plugin;

    AddCommand(@NonNull ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player, Command command, String label, String[] args) {
        if (args.length < 2) {
            player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
            return;
        }
        String inputId = args[1];
        List<Parkour> parkours = plugin.getParkours();

        for (Parkour parkour : parkours) {
            if (parkour.getId().equalsIgnoreCase(inputId)) {
                player.sendMessage(FailedMessage.ID_EXISTS.getText());
                return;
            }
        }
        Parkour newParkour = new Parkour(inputId);
        plugin.getParkours().add(newParkour);
        player.sendMessage(SuccessMessage.ADD.getText());
    }
}
