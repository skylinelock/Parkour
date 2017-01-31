package mc.sky_lock.parkour.command.tabcomplete;

import mc.sky_lock.parkour.ParkourHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sky_lock
 */

public class ParkourTabCompleter implements TabCompleter {

    private final ParkourHandler handler;

    public ParkourTabCompleter(ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return Collections.singletonList("reload");
        }
        List<String> commandNames = new ArrayList<>(handler.getSubCommandNames());

        if (args.length < 2) {
            return commandNames;
        }

        if (args.length == 2) {
            List<String> parkourNames = new ArrayList<>();
            handler.getParkourManager().getParkours().forEach(parkour -> parkourNames.add(parkour.getName()));
            return parkourNames;
        }

        return Collections.emptyList();
    }

}
