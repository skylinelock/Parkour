package mc.sky_lock.parkour.command;

import lombok.NonNull;
import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

public class TeleportCommand implements ICommand {

    private final ParkourPlugin plugin;

    public TeleportCommand(@NonNull ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player, Command command, String label, String[] args) {
        String inputId = args[1];
        for (Parkour parkour : plugin.getParkours()) {
            if (parkour.getId().equals(inputId)) {
                player.teleport(parkour.getRespawnPoint());
                player.sendMessage(ChatColor.GREEN + "Teleporterd");
                return;
            }
        }
        player.sendMessage(ChatColor.RED + "Teleported failed");
    }
}
