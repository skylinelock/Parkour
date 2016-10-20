package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourManager;
import mc.sky_lock.parkour.ParkourObj;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author sky_lock
 */
public class CommandHandler implements CommandExecutor {
    private final Parkour plugin;
    private final ParkourManager manager;
    
    public CommandHandler(Parkour plugin) {
        this.plugin = plugin;
        this.manager = plugin.getParkourManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 1) {
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "start":
                if (manager.getParkours() == null || manager.getParkours().isEmpty()) {
                    sender.sendMessage("hage");
                    return true;
                }
                for (ParkourObj parkour : manager.getParkours()) {
                    sender.sendMessage(parkour.getName());
                }
                break;
            case "end":
                break;
        }
        return true;
    }
    
}
