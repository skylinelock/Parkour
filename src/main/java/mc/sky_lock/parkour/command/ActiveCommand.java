package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.SuccessMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

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

        Optional<Parkour> oParkour = handler.getParkour(inputId);
        if (oParkour.isPresent()) {
            Parkour parkour = oParkour.get();
            if (!checkParkour(parkour)) {
                return;
            }
            parkour.setActive(true);
            player.sendMessage(SuccessMessage.ACTIVE.getText());
            return;
        }
        player.sendMessage(SuccessMessage.ACTIVE.getText());
    }

    private boolean checkParkour(Parkour parkour) {
        if (parkour.getStartPoint() == null || parkour.getEndPoint() == null || parkour.getPresetPoint() == null || parkour.getName() == null) {
            return false;
        }
        return true;
    }
}
