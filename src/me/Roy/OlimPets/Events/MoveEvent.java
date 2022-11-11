package me.Roy.OlimPets.Events;

import static me.Roy.OlimPets.Extra.jumpPlayers;
import static me.Roy.OlimPets.Extra.petsZombie;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (jumpPlayers.contains(e.getPlayer())) {
            Location loc = petsZombie.get(e.getPlayer()).getLocation();
            loc.setY(e.getPlayer().getLocation().getY());
            petsZombie.get(e.getPlayer()).teleport(loc);
        }
    }
}
