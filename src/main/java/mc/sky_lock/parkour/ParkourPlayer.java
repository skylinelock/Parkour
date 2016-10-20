package mc.sky_lock.parkour;

import org.bukkit.entity.Player;

/**
 *
 * @author sky_lock
 */
public class ParkourPlayer {
    private final Player player;
    private final ParkourObj parkour;
    private final long startTime;
    
    public ParkourPlayer(Player player, ParkourObj parkour) {
        this.player = player;
        this.parkour = parkour;
        startTime = System.currentTimeMillis();
    }
    
    public long getTime() {
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    
    public ParkourObj getParkour() {
        return parkour;
    }
    
    public Player getPlayer() {
        return player;
    }
}
