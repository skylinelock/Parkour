package mc.sky_lock.parkour.command;

import lombok.NonNull;
import mc.sky_lock.parkour.ParkourPlugin;
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

    private final ParkourPlugin plugin;

    public Commands(@NonNull ParkourPlugin plugin) {
        this.plugin = plugin;
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
                cmd = new AddCommand(plugin);
                break;
            case "setname":
            case "sn":
                cmd = new SetNameCommand(plugin);
                break;
            case "setstart":
            case "ss":
                cmd = new SetStartCommand(plugin);
                break;
            case "setend":
            case "se":
                cmd = new SetEndCommand(plugin);
                break;
            case "setpre":
            case "sp":
                cmd = new SetPreCommand(plugin);
                break;
            case "remove":
                cmd = new RemoveCommand(plugin);
                break;
            case "active":
                cmd = new ActiveCommand(plugin);
                break;
            case "info":
                cmd = new InfoCommand(plugin);
                break;
            case "list":
                cmd = new ListCommand(plugin);
                break;
            case "teleport":
            case "tp":
                cmd = new TeleportCommand(plugin);
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
        plugin.getParkourConfig().saveParkours(plugin.getParkours());
    }

}
