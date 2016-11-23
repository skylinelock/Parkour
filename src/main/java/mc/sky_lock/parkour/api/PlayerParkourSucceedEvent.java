package mc.sky_lock.parkour.api;

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
    private final long time_ms;
    private boolean cancelled = false;

    public PlayerParkourSucceedEvent(Player player, Parkour parkour, long time_ms) {
        super(player);
        this.parkour = parkour;
        this.time_ms = time_ms;
    }

    public static HandlerList getHandlerList() {
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

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public long getTim_ms() {
        return time_ms;
    }
}
