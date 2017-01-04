package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.SuccessMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author sky_lock
 */

class ActiveCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;

    ActiveCommand(@NotNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("parkour.command.active")) {
            player.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        if (args.length < 2) {
            player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
            return;
        }
        List<Parkour> parkours = handler.getParkours();
        String inputId = args[1];

        for (Parkour parkour : parkours) {
            if (parkour.getId().equals(inputId)) {
                if (parkour.getStartPoint() == null || parkour.getEndPoint() == null || parkour.getPresetPoint() == null || parkour.getName() == null) {
                    break;
                }
                parkour.setActive(true);
                player.sendMessage(SuccessMessage.ACTIVE.getText());
                return;
            }
        }
        player.sendMessage(FailedMessage.ACTIVE.getText());
    }
}
