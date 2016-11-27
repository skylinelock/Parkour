package mc.sky_lock.parkour.command.tabcomplete;

import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sky_lock
 */

public class ParkourTabCompleter implements TabCompleter {

    private final ParkourHandler handler;

    public ParkourTabCompleter(@NotNull ParkourHandler handler) {
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
        List<String> allCommand = Arrays.asList("active", "add", "info", "list", "remove", "setend", "setname", "setpre", "setstart", "teleport", "sn", "ss", "se", "sp", "tp");
        String firstArg = args[0];
        if (args.length == 1) {
            if (firstArg.equals("")) {
                return allCommand;
            }
            for (String commandName : allCommand) {
                if (commandName.toLowerCase().startsWith(firstArg)) {
                    displayStrs.add(commandName);
                }
            }
        } else if (args.length == 2) {
            String secondArg = args[1];
            if (firstArg.equalsIgnoreCase("add") || firstArg.equalsIgnoreCase("list")) {
                return null;
            }
            for (Parkour parkour : handler.getParkours()) {
                if (secondArg.equals("")) {
                    displayStrs.add(parkour.getId());
                    continue;
                }
                if (parkour.getId().startsWith(args[0])) {
                    displayStrs.add(parkour.getId());
                }
            }
        }
        return displayStrs;
    }
}
