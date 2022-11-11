package me.Roy.OlimPets.Objetos;

import static me.Roy.OlimPets.Extra.petsZombieDueño;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverJugando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverPartyJugando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControlRanked.antiloverRankedJugando;
import static me.Roy.OlimPets.Lang.antiloverLoverStep;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class AntiloverZombie {

    Location locInitial;
    int zFinal;
    Zombie zombie;
    boolean sum = false;

    public AntiloverZombie(Zombie z, Location l1, int l2) {
        zombie = z;
        locInitial = l1;
        zFinal = l2;
        sum = true;
    }

    public boolean mover() {
        if (zombie.getLocation().getZ() < zFinal) {
            sum = false;
            matar();
        } else {
            Location loc = zombie.getLocation().clone();
            loc.setZ(loc.getZ() - antiloverLoverStep);
            zombie.teleport(loc);
            for (Entity current : zombie.getNearbyEntities(0.15, 0.15, 0.15)) {
                if (current instanceof Zombie) {
                    if (petsZombieDueño.containsKey((Zombie) current)) {
                        Player p = petsZombieDueño.get((Zombie) current);
                        Location locH = zombie.getLocation();
                            Location flameloc = locH;
                            flameloc.setZ(flameloc.getZ());
                            flameloc.setX(flameloc.getX());
                            locH.getWorld().playEffect(flameloc, Effect.HEART, 51);
                            flameloc.setX(flameloc.getX() - 0.2);
                            locH.getWorld().playEffect(flameloc, Effect.HEART, 51);
                            flameloc.setX(flameloc.getX() + 0.4);
                            locH.getWorld().playEffect(flameloc, Effect.HEART, 51);
                        if (antiloverJugando.containsKey(p)) {
                            antiloverJugando.get(p).caido(p);
                        } else if (antiloverPartyJugando.containsKey(p)) {
                            antiloverPartyJugando.get(p).caido(p);
                        } else if (antiloverRankedJugando.containsKey(p)) {
                            antiloverRankedJugando.get(p).caido(p);
                        }
                    }
                }
            }
        }
        return sum;
    }

    public void matar() {
        zombie.remove();
    }
}
