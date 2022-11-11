package me.Roy.OlimPets.Games.Jump;

import java.util.HashMap;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpLibres;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpOcupadas;
import static me.Roy.OlimPets.Lang.cmaps;
import static me.Roy.OlimPets.Lang.configureJumpCreated;
import static me.Roy.OlimPets.Lang.configureJumpPlayer;
import static me.Roy.OlimPets.Lang.configureJumpSpawn;
import static me.Roy.OlimPets.Lang.maps;
import me.Roy.OlimPets.PlayerControl;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class JumpCreate {

    public static HashMap<Player, Integer> jumpCrearMapa = new HashMap<>();
    public static HashMap<Player, Location> jumpZombieLocation = new HashMap<>();
    
    public static void crearMapa(Player p) {
        if (PlayerControl.hasPerm(p, "olimpet.create.jump")) {
            jumpCrearMapa.put(p, 1);
            p.sendMessage(configureJumpSpawn);
        }
    }

    public static void jumpSetPlayerSpawns(Player p) {
        jumpZombieLocation.put(p, p.getLocation());
        jumpCrearMapa.put(p, 2);
        p.sendMessage(configureJumpPlayer);
    }
    
    public static void registrar(Player p) {
        int id = jumpLibres.size() + jumpOcupadas.size() + 1;
        
        jumpLibres.add(new JumpMap(p.getLocation(), jumpZombieLocation.get(p), id));
        cmaps.set("jump." + id + ".id", id);

        cmaps.set("jump." + id + ".spawnplayer.w", p.getWorld().getName());
        cmaps.set("jump." + id + ".spawnplayer.x", p.getLocation().getX());
        cmaps.set("jump." + id + ".spawnplayer.y", p.getLocation().getY());
        cmaps.set("jump." + id + ".spawnplayer.z", p.getLocation().getZ());
        cmaps.set("jump." + id + ".spawnplayer.yaw", p.getLocation().getYaw());
        cmaps.set("jump." + id + ".spawnplayer.pitch", p.getLocation().getPitch());

        cmaps.set("jump." + id + ".spawnzombie.w", jumpZombieLocation.get(p).getWorld().getName());
        cmaps.set("jump." + id + ".spawnzombie.x", jumpZombieLocation.get(p).getX());
        cmaps.set("jump." + id + ".spawnzombie.y", jumpZombieLocation.get(p).getY());
        cmaps.set("jump." + id + ".spawnzombie.z", jumpZombieLocation.get(p).getZ());
        cmaps.set("jump." + id + ".spawnzombie.yaw", jumpZombieLocation.get(p).getYaw());
        cmaps.set("jump." + id + ".spawnzombie.pitch", jumpZombieLocation.get(p).getPitch());
        jumpCrearMapa.remove(p);
        jumpZombieLocation.remove(p);
        Extra.guardar(maps, cmaps);
        p.sendMessage(configureJumpCreated.replaceAll("<number>", "" + id));
    }
}
