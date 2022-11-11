package me.Roy.OlimPets.Events;

import me.Roy.OlimPets.Extra;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.spawnLobby;
import static me.Roy.OlimPets.Main.plugin;
import me.Roy.OlimPets.PetControl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        e.setJoinMessage("");
        final Player p = e.getPlayer();
        p.getInventory().clear();
        Hotbar.ponerMain(p);
        PetControl.comprobarPet(p);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                p.teleport(spawnLobby);
            }
        }, 5L);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage("");
        Player p = e.getPlayer();
        Extra.sacar(p);
    }
}
