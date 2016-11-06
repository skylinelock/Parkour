package mc.sky_lock.parkour;

import org.bukkit.ChatColor;

import javax.xml.stream.Location;

/**
 * @author sky_lock
 */

public class FormatUtils {

    public static String NOT_ENOUGH_MESSAGE = ChatColor.RED + "Not enough arguments";

    public static boolean compareLocation(Location location1, Location location2) {
        return true;
    }

    public static String buildString(int startIndex, String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < args.length; i++) {
            if (i != startIndex) {
                sb.append(" ");
            }
            sb.append(args[i]);
        }
        return sb.toString();
    }

    public static boolean isBoolean(String string) {
        return Boolean.valueOf(string);
    }

}

