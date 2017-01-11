package mc.sky_lock.parkour;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sky_lock
 */

public class Util {

    public static String formatTime(long timeMillis) {
        Date date = new Date(timeMillis);
        SimpleDateFormat format = new SimpleDateFormat("mm:ss.SSS");
        return format.format(date);
    }

    public static String convertBool(boolean bool) {
        return bool ? "Yes" : "No";
    }
}
