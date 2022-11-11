package me.Roy.OlimPets.Games.Antilover;

import java.util.HashMap;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverLibres;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverOcupadas;
import static me.Roy.OlimPets.Lang.cmaps;
import static me.Roy.OlimPets.Lang.configureAntiloverCreated;
import static me.Roy.OlimPets.Lang.configureAntiloverFirstBorder;
import static me.Roy.OlimPets.Lang.configureAntiloverLoverSpawn;
import static me.Roy.OlimPets.Lang.configureAntiloverPlayerSpawn;
import static me.Roy.OlimPets.Lang.configureAntiloverSecondBorder;
import static me.Roy.OlimPets.Lang.configureAntiloverZombieSpawn;
import static me.Roy.OlimPets.Lang.maps;
import me.Roy.OlimPets.PlayerControl;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AntiloverCreate {

    public static HashMap<Player, Integer> antiloverCrearMapa = new HashMap<>();
    public static HashMap<Player, Location> antiloverZombie = new HashMap<>();
    public static HashMap<Player, Location> antiloverPlayer = new HashMap<>();
    public static HashMap<Player, Location> antiloverLover = new HashMap<>();
    public static HashMap<Player, Integer> antiloverFirst = new HashMap<>();

    public static void crearMapa(Player p) {
        if (PlayerControl.hasPerm(p, "olimpet.create.antilover")) {
            antiloverCrearMapa.put(p, 1);
            p.sendMessage(configureAntiloverZombieSpawn);
        }
    }

    public static void nextStep(Player p) {
        int i = antiloverCrearMapa.get(p);
        if (i == 2) {
            antiloverZombie.put(p, p.getLocation());
            p.sendMessage(configureAntiloverPlayerSpawn);
        } else if (i == 3) {
            antiloverPlayer.put(p, p.getLocation());
            p.sendMessage(configureAntiloverLoverSpawn);
        } else if (i == 4) {
            antiloverLover.put(p, p.getLocation());
            p.sendMessage(configureAntiloverFirstBorder);
        } else if (i == 5) {
            antiloverFirst.put(p, p.getLocation().getBlockX());
            p.sendMessage(configureAntiloverSecondBorder);
        } else if (i == 6) {
            registrar(p);
        }
    }
    
    public static void sacar(Player p){
        if (antiloverCrearMapa.containsKey(p)){
            antiloverCrearMapa.remove(p);
        }
        if (antiloverZombie.containsKey(p)){
            antiloverZombie.remove(p);
        }
        if (antiloverPlayer.containsKey(p)){
            antiloverPlayer.remove(p);
        }
        if (antiloverLover.containsKey(p)){
            antiloverLover.remove(p);
        }
        if (antiloverFirst.containsKey(p)){
            antiloverFirst.remove(p);
        }
    }

    public static void registrar(Player p) {
        int id = antiloverLibres.size() + antiloverOcupadas.size() + 1;

        antiloverLibres.add(new AntiloverMap(antiloverPlayer.get(p),
                antiloverZombie.get(p),
                antiloverLover.get(p),
                antiloverFirst.get(p),
                p.getLocation().getBlockX(),
                id));
        cmaps.set("antilover." + id + ".id", id);

        cmaps.set("antilover." + id + ".spawnplayer.w", antiloverPlayer.get(p).getWorld().getName());
        cmaps.set("antilover." + id + ".spawnplayer.x", antiloverPlayer.get(p).getX());
        cmaps.set("antilover." + id + ".spawnplayer.y", antiloverPlayer.get(p).getY());
        cmaps.set("antilover." + id + ".spawnplayer.z", antiloverPlayer.get(p).getZ());
        cmaps.set("antilover." + id + ".spawnplayer.yaw", antiloverPlayer.get(p).getYaw());
        cmaps.set("antilover." + id + ".spawnplayer.pitch", antiloverPlayer.get(p).getPitch());
        
        cmaps.set("antilover." + id + ".spawnzombie.w", antiloverZombie.get(p).getWorld().getName());
        cmaps.set("antilover." + id + ".spawnzombie.x", antiloverZombie.get(p).getX());
        cmaps.set("antilover." + id + ".spawnzombie.y", antiloverZombie.get(p).getY());
        cmaps.set("antilover." + id + ".spawnzombie.z", antiloverZombie.get(p).getZ());
        cmaps.set("antilover." + id + ".spawnzombie.yaw", antiloverZombie.get(p).getYaw());
        cmaps.set("antilover." + id + ".spawnzombie.pitch", antiloverZombie.get(p).getPitch());
        
        cmaps.set("antilover." + id + ".spawnlovers.w", antiloverLover.get(p).getWorld().getName());
        cmaps.set("antilover." + id + ".spawnlovers.x", antiloverLover.get(p).getX());
        cmaps.set("antilover." + id + ".spawnlovers.y", antiloverLover.get(p).getY());
        cmaps.set("antilover." + id + ".spawnlovers.z", antiloverLover.get(p).getZ());
        cmaps.set("antilover." + id + ".spawnlovers.yaw", antiloverLover.get(p).getYaw());
        cmaps.set("antilover." + id + ".spawnlovers.pitch", antiloverLover.get(p).getPitch());
        
        cmaps.set("antilover." + id + ".firstborder", antiloverFirst.get(p));
        cmaps.set("antilover." + id + ".secondborder", p.getLocation().getBlockX());

        sacar(p);
        Extra.guardar(maps, cmaps);
        p.sendMessage(configureAntiloverCreated.replaceAll("<number>", "" + id));
    }
}
