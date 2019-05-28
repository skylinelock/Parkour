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
        String scaledX = truncateToOneDecimalPlace(location.getX());
        String scaledY = truncateToOneDecimalPlace(location.getY());
        String scaledZ = truncateToOneDecimalPlace(location.getZ());
        return scaledX + " " + scaledY + " " + scaledZ;
    }

    private static String truncateToOneDecimalPlace(double decimal) {
        return String.format("%.1f", decimal);
    }
}
