package me.Roy.OlimPets.Objetos;

import java.util.Random;
import static me.Roy.OlimPets.Lang.countZombieVelocity;
import org.bukkit.Location;
import org.bukkit.entity.Zombie;

public class CountZombie {

    Location loc1, loc2;
    Zombie zombie;
    boolean aumentando = false, sum = false;
    double vel = 1.0;

    public CountZombie(Zombie z, boolean au, Location l1, Location l2) {
        Random r = new Random();
        loc1 = l1;
        loc2 = l2;
        zombie = z;
        aumentando = au;
        vel = countZombieVelocity.get(r.nextInt(countZombieVelocity.size()));
        sum = true;
    }

    public boolean moverZombie() {
        if (aumentando) {
            Location loc = zombie.getLocation();
            if (loc.getBlockX() < loc2.getBlockX()){
            //if (loc.distance(loc2) <= 1) {
                eliminar();
            } else {
                loc.setX(loc.getX() - vel);
                zombie.teleport(loc);
            }
        } else {
            Location loc = zombie.getLocation();
            if (loc.getBlockX() > loc1.getBlockX()){
            //if (loc.distance(loc1) <= 1) {
                eliminar();
            } else {
                loc.setX(loc.getX() + vel);
                zombie.teleport(loc);
            }
        }
        return sum;
    }

    public void eliminar() {
        zombie.remove();
        sum = false;
    }
}
