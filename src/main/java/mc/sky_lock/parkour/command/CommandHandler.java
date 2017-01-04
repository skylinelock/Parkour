package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.message.FailedMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author sky_lock
 */

public class CommandHandler implements CommandExecutor {

    private final ParkourHandler handler;

    public CommandHandler(@NotNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ICommand cmd = new UsageCommand();
        if (args.length < 1) {
            cmd.execute(sender, command, label, args);
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
            case "lock":
                cmd = new LockCommand(handler);
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
                cmd = new ReloadCommand(handler);
                break;
            case "save":
                cmd = new SaveCommand(handler);
                break;
            default:
                break;
        }
        if (cmd instanceof ConsoleCancellable && !(sender instanceof Player)) {
            sender.sendMessage(FailedMessage.NOT_PLAYER.getText());
            return true;
        }
        cmd.execute(sender, command, label, args);
        return true;
    }

}
