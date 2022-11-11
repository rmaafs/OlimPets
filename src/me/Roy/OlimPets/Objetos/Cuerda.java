package me.Roy.OlimPets.Objetos;

import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.petsZombieDueño;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpJugando;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpPartyJugando;
import static me.Roy.OlimPets.Games.Jump.JumpControlRanked.jumpRankedJugando;
import static me.Roy.OlimPets.Lang.CHICKEN_EGG_POP;
import static me.Roy.OlimPets.Lang.jumpFenceVelocity;
import static me.Roy.OlimPets.Main.version;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class Cuerda {

    Location loc;
    ArmorStand bat1;
    double xMax = 0;

    public Cuerda(Location l, double i) {
        xMax = i;
        l.setY(l.getY() - 1);
        loc = l.clone();
        bat1 = ((ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND));
        version.noAI(bat1, 1);
    }

    public boolean moverCuerda() {
        loc.setX(loc.getX() + jumpFenceVelocity);
        if (loc.getX() >= xMax) {
            return true;
        } else {
            bat1.teleport(loc);
            for (Entity current : bat1.getNearbyEntities(0.1, 0.3, 0.1)) {
                if (current instanceof Zombie) {
                    Player p = petsZombieDueño.get((Zombie) current);
                    Extra.sonido(p, CHICKEN_EGG_POP);
                    if (jumpJugando.containsKey(p)) {
                        //jumpJugando.get(p).caido(p);
                        jumpJugando.get(p).getCaidos().add(p);
                    }
                    if (jumpRankedJugando.containsKey(p)) {
                        //jumpRankedJugando.get(p).caido(p);
                        jumpRankedJugando.get(p).getCaidos().add(p);
                    }
                    if (jumpPartyJugando.containsKey(p)) {
                        //jumpPartyJugando.get(p).caido(p);
                        jumpPartyJugando.get(p).getCaidos().add(p);
                    }
                    break;
                }
            }
        }
        return false;
    }

    public void removerCuerda() {
        bat1.remove();
    }
}
