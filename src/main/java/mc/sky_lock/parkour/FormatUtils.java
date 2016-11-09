package mc.sky_lock.parkour;

import org.bukkit.ChatColor;

import java.text.SimpleDateFormat;

/**
 * @author sky_lock
 */

public class FormatUtils {

    public static String NOT_ENOUGH_MESSAGE = ChatColor.RED + "Not enough arguments";

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

    static String timeFormat(Long time) {
        SimpleDateFormat timeformat = new SimpleDateFormat("mm:ss.SSS");
        return timeformat.format(time);
    }

}

