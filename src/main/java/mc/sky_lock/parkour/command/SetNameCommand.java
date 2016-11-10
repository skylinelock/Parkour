package mc.sky_lock.parkour.command;

import lombok.NonNull;
import mc.sky_lock.parkour.FormatUtils;
import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author sky_lock
 */

class SetNameCommand implements ICommand {

    private final ParkourPlugin plugin;

    SetNameCommand(@NonNull ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player, Command command, String label, String[] args) {
        if (args.length < 3) {
            player.sendMessage(FormatUtils.NOT_ENOUGH_MESSAGE);
            return;
        }
        String inputId = args[1];
        List<Parkour> parkours = plugin.getParkours();

        for (Parkour parkour : parkours) {
            if (!parkour.getId().equals(inputId)) {
                continue;
            }
            String name = FormatUtils.buildString(2, args);
            parkour.setName(name);
            player.sendMessage(ChatColor.GREEN + "Parkour name updated");
            return;
        }
        player.sendMessage(ChatColor.RED + "Parkour name couldn't updated");
    }


}
