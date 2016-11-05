package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.Util;
import org.bukkit.ChatColor;
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

    public Commands(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(Util.NOT_ENOUGH_MESSAGE);
            return false;
        }

        if (!(sender instanceof Player)) {
            if (!args[0].equals("reload")) {
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
                return false;
            }
            //reload
        }
        Player player = (Player) sender;
        ICommand cmd = new ListCommand(plugin);

        switch (args[0].toLowerCase()) {
            case "add":
                cmd = new AddCommand(plugin);
                break;

            case "setname":
                cmd = new SetNameCommand(plugin);
                break;

            case "setstart":
                cmd = new SetStartCommand(plugin);
                break;

            case "setend":
                cmd = new SetEndCommand(plugin);
                break;

            case "setpre":
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
        }

        cmd.execute(player, command, label, args);
        return true;
    }

}
