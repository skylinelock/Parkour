package mc.sky_lock.parkour.command.tabcomplete;

import lombok.NonNull;
import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sky_lock
 */

public class ParkourTabComplete implements TabCompleter {

    private final ParkourPlugin plugin;

    public ParkourTabComplete(@NonNull ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> displayStrs = new ArrayList<>();
        if (!(sender instanceof Player)) {
            displayStrs.add("reload");
            return displayStrs;
        }
        if (args.length == 1) {
            displayStrs.add("teleport");
            displayStrs.add("list");
            displayStrs.add("info");
            displayStrs.add("add");
            displayStrs.add("setname");
            displayStrs.add("setstart");
            displayStrs.add("setend");
            displayStrs.add("setpre");
            displayStrs.add("active");
            displayStrs.add("remove");
            displayStrs.add("reload");
            return displayStrs;
        }

        switch (args[0].toLowerCase()) {
            case "active":
            case "info":
            case "setname":
            case "sn":
            case "setstart":
            case "ss":
            case "setend":
            case "se":
            case "setpre":
            case "sp":
            case "remove":
            case "teleport":
            case "tp":
                for (Parkour parkour : plugin.getParkours()) {
                    displayStrs.add(parkour.getId());
                }
                break;
        }
        return displayStrs;
    }
}
