package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourManager;
import mc.sky_lock.parkour.ParkourPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author sky_lock
 */
public class CommandHandler implements CommandExecutor {

    private final ParkourPlugin plugin;
    private final ParkourManager manager;

    public CommandHandler(ParkourPlugin plugin) {
        this.plugin = plugin;
        this.manager = plugin.getParkourManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Not enough arguments!");
            return true;
        }
        if (!(sender instanceof Player)) {
            if (!args[0].equals("reload")) {
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
                return false;
            }
            //reload
        }
        Player player = (Player) sender;
        String id;

        switch (args[0].toLowerCase()) {
            case "add":
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Not enough arguments!");
                    return false;
                }
                id = args[1];
                manager.addParkour(id);
                player.sendMessage(ChatColor.GREEN + "Add parkour successful!");
                break;
            case "setname":
                if (args.length < 3) {
                    player.sendMessage(ChatColor.RED + "Not enough arguments!");
                    return false;
                }
                id = args[1];
                StringBuilder sb = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    if (i != 2) {
                        sb.append(" ");
                    }
                    sb.append(args[i]);
                }
                String name = sb.toString();
                for (Parkour parkour : manager.getParkours()) {
                    if (parkour.getId().equals(id)) {
                        parkour.setName(name);
                        player.sendMessage(ChatColor.GREEN + "Setname successful!");
                        return true;
                    }
                }
                player.sendMessage(ChatColor.RED + "Setname failed!");
                return false;
            case "setstart":
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Not enough arguments!");
                    return false;
                }
                id = args[1];
                for (Parkour parkour : manager.getParkours()) {
                    if (parkour.getId().equals(id)) {
                        parkour.setStartLocation(player.getLocation());
                        player.sendMessage(ChatColor.GREEN + "Setstart successful!");
                        return true;
                    }
                }
                player.sendMessage(ChatColor.GREEN + "Setstart failed!");
                return false;
            case "setend":
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Not enough arguments!");
                    return false;
                }
                id = args[1];
                for (Parkour parkour : manager.getParkours()) {
                    if (parkour.getId().equals(id)) {
                        parkour.setEndLocation(player.getLocation());
                        player.sendMessage(ChatColor.GREEN + "Setend successful!");
                        return true;
                    }
                }
                player.sendMessage(ChatColor.RED + "Setend failed!");
                return false;
            case "setpre":
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Not enough arguments!");
                    return false;
                }
                id = args[1];
                for (Parkour parkour : manager.getParkours()) {
                    if (parkour.getId().equals(id)) {
                        parkour.setRespawnLocation(player.getLocation());
                        player.sendMessage(ChatColor.GREEN + "Setpre successful!");
                        return true;
                    }
                }
                player.sendMessage(ChatColor.GREEN + "Setpre failed!");
                return false;
            case "remove":
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Not enough arguments!");
                    return false;
                }
                id = args[1];
                if (manager.removeParkour(id)) {
                    player.sendMessage(ChatColor.GREEN + "Remove successful!");
                    return true;
                }
                player.sendMessage(ChatColor.GREEN + "Remove failed!");
                return false;
            case "list":
                player.sendMessage(ChatColor.GREEN + "------  [Parkour List]  ------");
                for (Parkour parkour : manager.getParkours()) {
                    player.sendMessage(ChatColor.GREEN + "Id : " + parkour.getId() + " Name : " + parkour.getName());
                }
                break;
            case "active":
                if (args.length < 3) {
                    player.sendMessage(ChatColor.RED + "Not enough arguments!");
                    return false;
                }
                id = args[1];
                for (Parkour parkour : manager.getParkours()) {
                    if (parkour.getId().equals(id)) {
                        boolean flag = Boolean.parseBoolean(args[2]);
                        parkour.setActive(flag);
                        player.sendMessage(ChatColor.GREEN + "Active successful! " + flag);
                        return true;
                    }
                }
                player.sendMessage(ChatColor.GREEN + "Active failed!");
                break;
            case "info":
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Not enough arguments!");
                    return false;
                }
                id = args[1];
                Parkour parkour = checkID(id);
                if (parkour != null) {
                    Location startLoc = parkour.getStartLocation();
                    Location endLoc = parkour.getEndLocation();
                    Location preLoc = parkour.getRespawnLocation();
                    player.sendMessage(ChatColor.GREEN + "Id: " + ChatColor.RESET + parkour.getId());
                    player.sendMessage(ChatColor.GREEN + "Name: " + ChatColor.RESET + parkour.getName());
                    player.sendMessage(ChatColor.GREEN + "Start Point: " + ChatColor.RESET + startLoc.getBlockX() + " " + startLoc.getBlockY() + " " + startLoc.getBlockZ());
                    player.sendMessage(ChatColor.GREEN + "End Point: " + ChatColor.RESET + endLoc.getBlockX() + " " + endLoc.getBlockY() + " " + endLoc.getBlockZ());
                    player.sendMessage(ChatColor.GREEN + "Pre Point: " + ChatColor.RESET + preLoc.getBlockX() + " " + preLoc.getBlockY() + " " + preLoc.getBlockZ());
                    player.sendMessage(ChatColor.GREEN + "Active: " + ChatColor.RESET + Boolean.toString(parkour.getActive()));
                    return false;
                }
                player.sendMessage(ChatColor.RED + "Info failed!");
                break;
        }
        return true;
    }

    private Parkour checkID(String id) {
        for (Parkour parkour : manager.getParkours()) {
            if (parkour.getId().equals(id)) {
                return parkour;
            }
        }
        return null;
    }

}
