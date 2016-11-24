package mc.sky_lock.parkour.command;

import lombok.NonNull;
import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author sky_lock
 */

class ListCommand implements ICommand {

    private final ParkourHandler handler;

    ListCommand(@NonNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(Player player, Command command, String label, String[] args) {
        List<Parkour> parkours = handler.getParkours();

        player.sendMessage(ChatColor.GREEN + "------  [Parkour List]  ------");

        for (Parkour parkour : parkours) {
            if (parkour.isActive()) {
                player.sendMessage(ChatColor.GREEN + "Id : " + parkour.getId() + " Name : " + parkour.getName());
                continue;
            }
            player.sendMessage(ChatColor.RED + "Id : " + parkour.getId() + " Name : " + parkour.getName());

        }

        player.sendMessage(ChatColor.GREEN + "--------------------------");
    }
}
