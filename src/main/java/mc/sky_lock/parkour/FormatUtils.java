package mc.sky_lock.parkour;

/**
 * @author sky_lock
 */

public class FormatUtils {

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

}

