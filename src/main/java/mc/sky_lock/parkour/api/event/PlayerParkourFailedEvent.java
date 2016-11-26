package mc.sky_lock.parkour.api.event;

import mc.sky_lock.parkour.Parkour;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * @author sky_lock
 */

public class PlayerParkourFailedEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Parkour parkour;
    private boolean cancelled = false;

    public PlayerParkourFailedEvent(Player player, Parkour parkour) {
        super(player);
        this.parkour = parkour;
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
}
