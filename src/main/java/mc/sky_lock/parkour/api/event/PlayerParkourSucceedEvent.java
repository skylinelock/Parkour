package mc.sky_lock.parkour.api.event;

import mc.sky_lock.parkour.Parkour;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * @author sky_lock
 */

public class PlayerParkourSucceedEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Parkour parkour;
    private final long timeMillis;
    private boolean cancelled = false;

    public PlayerParkourSucceedEvent(Player player, Parkour parkour, long timeMillis) {
        super(player);
        this.parkour = parkour;
        this.timeMillis = timeMillis;
    }

    @SuppressWarnings("unused")
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Parkour getParkour() {
        return parkour;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
