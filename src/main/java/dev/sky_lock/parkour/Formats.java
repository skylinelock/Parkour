package dev.sky_lock.parkour;

import org.bukkit.Location;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sky_lock
 */

public class Formats {

    public static String durationFormat(long timeMillis) {
        Date date = new Date(timeMillis);
        SimpleDateFormat format = new SimpleDateFormat("mm:ss.SSS");
        return format.format(date);
    }

    public static String roundDownCoordinateSet(Location location) {
        if (location == null) {
            return "Null";
        }
        BigDecimal x = new BigDecimal(location.getX());
        BigDecimal y = new BigDecimal(location.getY());
        BigDecimal z = new BigDecimal(location.getZ());

        String scaledX = x.setScale(1, BigDecimal.ROUND_DOWN).toString();
        String scaledY = y.setScale(1, BigDecimal.ROUND_DOWN).toString();
        String scaledZ = z.setScale(1, BigDecimal.ROUND_DOWN).toString();
        return scaledX + " " + scaledY + " " + scaledZ;
    }
}
