package me.Roy.OlimPets.Events;

import static me.Roy.OlimPets.Extra.petsZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageEvent implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Zombie){
            Zombie z = (Zombie) e.getDamager();
            if (petsZombie.containsValue(z)){
                e.setCancelled(true);
            }
        } else if (e.getDamager() instanceof Player){
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onHit(EntityDamageEvent e){
        if (e.getEntity() instanceof Zombie){
            Zombie z = (Zombie) e.getEntity();
            if (petsZombie.containsValue(z)){
                e.setCancelled(true);
            }
        }
    }
}
