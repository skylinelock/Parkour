package mc.sky_lock.parkour.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * @author sky_lock
 */

@SuppressWarnings("unused")
public class EntityListener implements Listener {

    @EventHandler
    public void playerDamage(EntityDamageEvent event) {
        EntityDamageEvent.DamageCause damageCause = event.getCause();
        if (damageCause != EntityDamageEvent.DamageCause.VOID && damageCause != EntityDamageEvent.DamageCause.FALL) {
            return;
        }
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void foodLevelChange(FoodLevelChangeEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        player.setFoodLevel(20);
        event.setCancelled(true);
    }
}
