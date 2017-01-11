package mc.sky_lock.parkour.command.tabcomplete;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.Parkour;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
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
        //かなりアバウトな実装。CommandMapクラス要検討。
        List<String> displayStrs = new ArrayList<>();
        if (!(sender instanceof Player)) {
            displayStrs.add("reload");
            return displayStrs;
        }
        List<String> allCommand = Arrays.asList(
                "info", "list", "teleport", "reload",
                "add", "setstart", "setend", "setpre", "setname", "active", "lock", "save", "delete",
                "ss", "se", "sp", "sn", "tp"
        );
        String firstArg = args[0];

        if (args.length == 1) {
            if (firstArg.equals("")) {
                return allCommand;
            }
            allCommand.stream()
                    .filter(commandName -> commandName.toLowerCase().startsWith(firstArg))
                    .findAny()
                    .ifPresent(displayStrs::add);
        } else if (args.length == 2) {
            String secondArg = args[1];
            if (firstArg.equalsIgnoreCase("add") || firstArg.equalsIgnoreCase("list")) {
                return Collections.emptyList();
            }
            List<Parkour> parkours = handler.getParkourManager().getParkours();
            if (secondArg.equals("")) {
                parkours.forEach(parkour -> displayStrs.add(parkour.getId()));
                return displayStrs;
            }
            parkours.stream()
                    .filter(parkour -> parkour.getId().startsWith(secondArg))
                    .forEach(parkour -> displayStrs.add(parkour.getId()));
        }
        return displayStrs;
    }
}
