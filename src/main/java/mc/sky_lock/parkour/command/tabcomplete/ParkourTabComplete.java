package mc.sky_lock.parkour.command.tabcomplete;

import lombok.NonNull;
import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sky_lock
 */

public class ParkourTabComplete implements TabCompleter {

    private final ParkourHandler handler;

    public ParkourTabComplete(@NonNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //かなりアバウトな実装。CommandMapクラス要検討。
        List<String> displayStrs = new ArrayList<>();
        if (!(sender instanceof Player)) {
            displayStrs.add("reload");
            return displayStrs;
        }
        List<String> commandList = Arrays.asList("active", "add", "info", "list", "remove", "setend", "setname", "setpre", "setstart", "teleport", "sn", "ss", "se", "sp", "tp");
        if (args.length == 1) {
            if (args[0].equals("")) {
                return commandList;
            }
            for (String commandName : commandList) {
                if (commandName.toLowerCase().startsWith(args[0])) {
                    displayStrs.add(commandName);
                }
            }
            return displayStrs;
        }

        if (args.length == 2) {
            if (args[1].equals("")) {
                for (Parkour parkour : handler.getParkours()) {
                    displayStrs.add(parkour.getId());
                }
                return displayStrs;
            }
            for (Parkour parkour : handler.getParkours()) {
                if (parkour.getId().startsWith(args[1])) {
                    displayStrs.add(parkour.getId());
                }
            }
        }
        return displayStrs;
    }
}
