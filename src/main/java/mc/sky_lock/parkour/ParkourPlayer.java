package mc.sky_lock.parkour;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

public class ParkourPlayer {

    @Getter
    private final Player player;
    @Getter
    private final Parkour parkour;
    private final long startTime;

    public ParkourPlayer(@NonNull Player player, @NonNull Parkour parkour) {
        this.player = player;
        this.parkour = parkour;

        this.startTime = System.currentTimeMillis();
    }

    public long getCurrentTime_ms() {
        long currentTime = System.currentTimeMillis();
        return currentTime - startTime;
    }

}
