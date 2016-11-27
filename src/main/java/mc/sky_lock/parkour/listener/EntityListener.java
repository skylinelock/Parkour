package mc.sky_lock.parkour.listener;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.ParkourPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

/**
 * @author sky_lock
 */

@SuppressWarnings("unused")
public class EntityListener implements Listener {
    private final ParkourHandler handler;

    public EntityListener(ParkourHandler handler) {
        this.handler = handler;
    }


    @EventHandler
    public void entityDamage(EntityDamageEvent event) {
        EntityDamageEvent.DamageCause damageCause = event.getCause();
        Entity entity = event.getEntity();
        if (damageCause == EntityDamageEvent.DamageCause.VOID) {
            if (!(entity instanceof Player)) {
                return;
            }
            Player player = (Player) entity;
            for (ParkourPlayer parkourPlayer : handler.getParkourPlayers()) {
                if (parkourPlayer.getPlayer().equals(player)) {
                    event.setCancelled(true);
                }
            }
        } else if (damageCause == DamageCause.FALL) {
            event.setCancelled(true);
        }
    }
}
