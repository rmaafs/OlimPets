package me.Roy.OlimPets.Games.Run;

import java.util.HashMap;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Games.Run.RunControl.runLibres;
import static me.Roy.OlimPets.Games.Run.RunControl.runOcupadas;
import static me.Roy.OlimPets.Lang.cmaps;
import static me.Roy.OlimPets.Lang.configureRunCreated;
import static me.Roy.OlimPets.Lang.configureRunFinal;
import static me.Roy.OlimPets.Lang.configureRunSpawn;
import static me.Roy.OlimPets.Lang.configureRunStep;
import static me.Roy.OlimPets.Lang.maps;
import me.Roy.OlimPets.PlayerControl;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RunCreate {

    public static HashMap<Player, Integer> runCrearMapa = new HashMap<>();
    public static HashMap<Player, Location> runCrearMapaLoc = new HashMap<>();
    public static int runFinalXLocation;

    public static void crearMapa(Player p) {
        if (PlayerControl.hasPerm(p, "olimpet.create.run")) {
            runCrearMapa.put(p, 1);
            nextStep(p);
        }
    }

    public static void nextStep(Player p) {
        int i = runCrearMapa.get(p);
        if (i == 1) {
            p.sendMessage(configureRunStep);
        } else if (i == 2) {
            runCrearMapaLoc.put(p, p.getLocation());
            p.sendMessage(configureRunFinal);
        } else if (i == 3) {
            runFinalXLocation = p.getLocation().getBlockX();
            p.sendMessage(configureRunSpawn);
        } else if (i == 4) {
            registrar(p);
        }
    }

    public static void registrar(Player p) {
        int id = runLibres.size() + runOcupadas.size() + 1;
        runLibres.add(new RunMap(p.getLocation(), runCrearMapaLoc.get(p), runFinalXLocation, id - 1));

        cmaps.set("run." + id + ".id", id);
        cmaps.set("run." + id + ".final", runFinalXLocation);

        cmaps.set("run." + id + ".spawnplayer.w", p.getWorld().getName());
        cmaps.set("run." + id + ".spawnplayer.x", p.getLocation().getX());
        cmaps.set("run." + id + ".spawnplayer.y", p.getLocation().getY());
        cmaps.set("run." + id + ".spawnplayer.z", p.getLocation().getZ());
        cmaps.set("run." + id + ".spawnplayer.yaw", p.getLocation().getYaw());
        cmaps.set("run." + id + ".spawnplayer.pitch", p.getLocation().getPitch());

        cmaps.set("run." + id + ".spawnzombie.w", runCrearMapaLoc.get(p).getWorld().getName());
        cmaps.set("run." + id + ".spawnzombie.x", runCrearMapaLoc.get(p).getX());
        cmaps.set("run." + id + ".spawnzombie.y", runCrearMapaLoc.get(p).getY());
        cmaps.set("run." + id + ".spawnzombie.z", runCrearMapaLoc.get(p).getZ());
        cmaps.set("run." + id + ".spawnzombie.yaw", runCrearMapaLoc.get(p).getYaw());
        cmaps.set("run." + id + ".spawnzombie.pitch", runCrearMapaLoc.get(p).getPitch());
        runCrearMapa.remove(p);
        runCrearMapaLoc.remove(p);
        Extra.guardar(maps, cmaps);
        p.sendMessage(configureRunCreated.replaceAll("<number>", "" + id));
    }
}
