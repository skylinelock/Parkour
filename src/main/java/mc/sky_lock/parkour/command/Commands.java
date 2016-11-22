package mc.sky_lock.parkour.command;

import lombok.NonNull;
import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.message.CommandUsage;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.SuccessMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author sky_lock
 */
public class Commands implements CommandExecutor {

    private final ParkourHandler handler;

    public Commands(@NonNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length < 1) {
                sender.sendMessage(CommandUsage.RELOAD.getText());
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                saveParkours();
                sender.sendMessage(SuccessMessage.RELOAD.getText());
                return true;
            }
            sender.sendMessage(FailedMessage.NOT_PLAYER.getText());
            return false;
        }
        Player player = (Player) sender;
        ICommand cmd = new UsageCommand();

        if (args.length < 1) {
            cmd.execute(player, command, label, args);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "add":
                cmd = new AddCommand(handler);
                break;
            case "setname":
            case "sn":
                cmd = new SetNameCommand(handler);
                break;
            case "setstart":
            case "ss":
                cmd = new SetStartCommand(handler);
                break;
            case "setend":
            case "se":
                cmd = new SetEndCommand(handler);
                break;
            case "setpre":
            case "sp":
                cmd = new SetPreCommand(handler);
                break;
            case "remove":
                cmd = new RemoveCommand(handler);
                break;
            case "active":
                cmd = new ActiveCommand(handler);
                break;
            case "info":
                cmd = new InfoCommand(handler);
                break;
            case "list":
                cmd = new ListCommand(handler);
                break;
            case "teleport":
            case "tp":
                cmd = new TeleportCommand(handler);
                break;
            case "reload":
                saveParkours();
                player.sendMessage(SuccessMessage.RELOAD.getText());
                return true;
            default:
                break;
        }

        cmd.execute(player, command, label, args);
        return true;
    }

    private void saveParkours() {
        handler.getParkourFile().saveParkours(handler.getParkours());
    }

}
