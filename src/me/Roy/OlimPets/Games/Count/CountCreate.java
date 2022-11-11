package me.Roy.OlimPets.Games.Count;

import java.util.HashMap;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Games.Count.CountControl.countLibres;
import static me.Roy.OlimPets.Games.Count.CountControl.countOcupadas;
import static me.Roy.OlimPets.Lang.cmaps;
import static me.Roy.OlimPets.Lang.configureCountCreated;
import static me.Roy.OlimPets.Lang.configureCountSpawn;
import static me.Roy.OlimPets.Lang.configureCountSpawnFinal;
import static me.Roy.OlimPets.Lang.configureCountSpawnPlayer;
import static me.Roy.OlimPets.Lang.maps;
import me.Roy.OlimPets.PlayerControl;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CountCreate {

    public static HashMap<Player, Integer> countCrearMapa = new HashMap<>();
    public static HashMap<Player, Location> countZombieLocation = new HashMap<>();
    public static HashMap<Player, Location> countZombiePlayer = new HashMap<>();

    public static void crearMapa(Player p) {
        if (PlayerControl.hasPerm(p, "olimpet.create.count")) {
            countCrearMapa.put(p, 1);
            p.sendMessage(configureCountSpawn);
        }
    }
    
    public static void nextStep(Player p) {
        int i = countCrearMapa.get(p);
        if (i == 2) {
            countZombieLocation.put(p, p.getLocation());
            p.sendMessage(configureCountSpawnFinal);
        } else if (i == 3) {
            countZombiePlayer.put(p, p.getLocation());
            p.sendMessage(configureCountSpawnPlayer);
        } else if (i == 4) {
            registrar(p);
        }
    }
    
    public static void registrar(Player p) {
        int id = countLibres.size() + countOcupadas.size() + 1;
        
        countLibres.add(new CountMap(p.getLocation(), countZombiePlayer.get(p), countZombieLocation.get(p), id));
        cmaps.set("count." + id + ".id", id);

        cmaps.set("count." + id + ".spawnplayer.w", p.getWorld().getName());
        cmaps.set("count." + id + ".spawnplayer.x", p.getLocation().getX());
        cmaps.set("count." + id + ".spawnplayer.y", p.getLocation().getY());
        cmaps.set("count." + id + ".spawnplayer.z", p.getLocation().getZ());
        cmaps.set("count." + id + ".spawnplayer.yaw", p.getLocation().getYaw());
        cmaps.set("count." + id + ".spawnplayer.pitch", p.getLocation().getPitch());

        cmaps.set("count." + id + ".spawnfinal.w", countZombiePlayer.get(p).getWorld().getName());
        cmaps.set("count." + id + ".spawnfinal.x", countZombiePlayer.get(p).getX());
        cmaps.set("count." + id + ".spawnfinal.y", countZombiePlayer.get(p).getY());
        cmaps.set("count." + id + ".spawnfinal.z", countZombiePlayer.get(p).getZ());
        cmaps.set("count." + id + ".spawnfinal.yaw", countZombiePlayer.get(p).getYaw());
        cmaps.set("count." + id + ".spawnfinal.pitch", countZombiePlayer.get(p).getPitch());
        
        cmaps.set("count." + id + ".spawn.w", countZombieLocation.get(p).getWorld().getName());
        cmaps.set("count." + id + ".spawn.x", countZombieLocation.get(p).getX());
        cmaps.set("count." + id + ".spawn.y", countZombieLocation.get(p).getY());
        cmaps.set("count." + id + ".spawn.z", countZombieLocation.get(p).getZ());
        cmaps.set("count." + id + ".spawn.yaw", countZombieLocation.get(p).getYaw());
        cmaps.set("count." + id + ".spawn.pitch", countZombieLocation.get(p).getPitch());
        countCrearMapa.remove(p);
        countZombieLocation.remove(p);
        Extra.guardar(maps, cmaps);
        p.sendMessage(configureCountCreated.replaceAll("<number>", "" + id));
    }
}
