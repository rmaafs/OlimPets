package me.Roy.OlimPets.Events;

import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverJugando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverPartyJugando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverPlayers;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControlRanked.antiloverRankedJugando;
import static me.Roy.OlimPets.Games.Count.CountControl.countContando;
import me.Roy.OlimPets.Games.Run.RunControl;
import static me.Roy.OlimPets.Games.Run.RunControl.runClickeando;
import static me.Roy.OlimPets.Lang.NOTE_STICKS;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (runClickeando.containsKey(p)) {
            if ((e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) && !runClickeando.get(p)) {
                runClickeando.put(p, true);
                RunControl.zombieStep(p);
            } else if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && runClickeando.get(p)) {
                runClickeando.put(p, false);
                RunControl.zombieStep(p);
            }
        } else if (countContando.contains(p)) {
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (p.getLevel() > 0) {
                    p.setLevel(p.getLevel() - 1);
                    Extra.sonido(p, NOTE_STICKS);
                }
            } else if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                p.setLevel(p.getLevel() + 1);
                Extra.sonido(p, NOTE_STICKS);
            }
        } else if (antiloverPlayers.contains(p)) {
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (antiloverJugando.containsKey(p)) {
                    antiloverJugando.get(p).moverZombie(p, true);
                } else if (antiloverPartyJugando.containsKey(p)) {
                    antiloverPartyJugando.get(p).moverZombie(p, true);
                } else if (antiloverRankedJugando.containsKey(p)) {
                    antiloverRankedJugando.get(p).moverZombie(p, true);
                }
            } else if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (antiloverJugando.containsKey(p)) {
                    antiloverJugando.get(p).moverZombie(p, false);
                } else if (antiloverPartyJugando.containsKey(p)) {
                    antiloverPartyJugando.get(p).moverZombie(p, false);
                } else if (antiloverRankedJugando.containsKey(p)) {
                    antiloverRankedJugando.get(p).moverZombie(p, false);
                }
            }
        }
    }
}
