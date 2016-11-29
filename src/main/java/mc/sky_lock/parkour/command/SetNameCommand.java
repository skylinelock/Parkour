package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.SuccessMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * @author sky_lock
 */

class SetNameCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;

   SetNameCommand(@NotNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
       Player player = (Player)sender;
        if (args.length < 3) {
            player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
            return;
        }
        String[] nameValues = Arrays.copyOfRange(args, 2, args.length);

        String inputId = args[1];
        List<Parkour> parkours = handler.getParkours();

        for (Parkour parkour : parkours) {
            if (!parkour.getId().equals(inputId)) {
                continue;
            }
            String name = String.join(" ", nameValues);
            parkour.setName(name);
            player.sendMessage(SuccessMessage.SET_NAME.getText());
            return;
        }
        player.sendMessage(FailedMessage.SET_NAME.getText());
    }


}
