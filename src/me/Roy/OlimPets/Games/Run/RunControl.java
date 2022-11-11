package me.Roy.OlimPets.Games.Run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.petsZombie;
import static me.Roy.OlimPets.Games.Run.RunControlRanked.runRankedJugando;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.NOTE_BASS;
import static me.Roy.OlimPets.Lang.noMapsAvailable;
import static me.Roy.OlimPets.Lang.runZombieStep;
import static me.Roy.OlimPets.Lang.startingPreGame;
import static me.Roy.OlimPets.Lang.unrankedMaxPlayers;
import static me.Roy.OlimPets.Lang.unrankedMinPlayers;
import static me.Roy.OlimPets.Lang.unrankedPreTime;
import static me.Roy.OlimPets.Lang.waitingGame;
import static me.Roy.OlimPets.Main.plugin;
import static me.Roy.OlimPets.Lang.NOTE_STICKS;
import me.Roy.OlimPets.LangItemsUpdater;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class RunControl {

    public static List<RunMap> runLibres = new ArrayList<>();
    public static List<RunMap> runOcupadas = new ArrayList<>();
    public static List<RunMap> runStarting = new ArrayList<>();

    public static HashMap<Player, Boolean> runClickeando = new HashMap<>();

    public static List<Player> runEsperando = new ArrayList<>();
    public static HashMap<Player, RunMap> runJugando = new HashMap<>();
    public static HashMap<Player, RunMap> runPartyJugando = new HashMap<>();
    public static int task = 0;

    public static void ponerEspera(Player p) {
        p.closeInventory();
        if (!runEsperando.contains(p)) {
            runEsperando.add(p);
        }
        LangItemsUpdater.updateLoreRunUnrankedMenu();
        for (Player o : runEsperando) {
            o.sendMessage(waitingGame.replaceAll("<game>", "Run").replaceAll("<number>", "" + runEsperando.size()).replaceAll("<max>", "" + unrankedMaxPlayers));
            Extra.sonido(p, NOTE_BASS);
        }
        Hotbar.ponerSalirWaiting(p);
        //if (runEsperando.size() >= unrankedMinPlayers) {
        if (runEsperando.size() >= 1) {
            Bukkit.getScheduler().cancelTask(task);
            for (Player o : runEsperando) {
                o.sendMessage(startingPreGame.replaceAll("<seconds>", String.valueOf(unrankedPreTime / 20)));
            }
            task = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    Bukkit.getScheduler().cancelTask(task);
                    empezarPreGame(runEsperando);
                }
            }, unrankedPreTime);
        }
    }

    public static void salirEspera(Player p) {
        if (!runEsperando.isEmpty()) {
            for (Player o : runEsperando) {
                o.sendMessage(waitingGame.replaceAll("<game>", "Run").replaceAll("<number>", "" + runEsperando.size()).replaceAll("<max>", "" + unrankedMaxPlayers));
                Extra.sonido(p, NOTE_BASS);
            }
        }
        if (runEsperando.size() < unrankedMinPlayers) {
            Bukkit.getScheduler().cancelTask(task);
        }
        LangItemsUpdater.updateLoreRunUnrankedMenu();
    }

    public static void empezarPreGame(List<Player> lista) {
        if (runLibres.isEmpty()) {
            for (Player o : lista) {
                o.sendMessage(noMapsAvailable);
                Extra.sonido(o, NOTE_BASS);
                Hotbar.ponerMain(o);
            }
            runJugando.clear();
            runEsperando.clear();
        } else {
//            boolean si = false;
            RunMap map = runLibres.get(0);
//            for (RunMap ma : runLibres) {
//                if (ma.getSlots() >= lista.size()) {
//                    map = ma;
//                    si = true;
//                    break;
//                }
//            }
//            if (si) {
            map.getPlayers().clear();
            for (Player p : lista) {
                map.addPlayer(p);
                runJugando.put(p, map);
            }
            runLibres.remove(map);
            runOcupadas.add(map);
            runStarting.add(map);
            runEsperando.clear();
            map.iniciar();
//            } else {
//                for (Player o : lista) {
//                    o.sendMessage(noMapsSlotsAvailable.replaceAll("<slots>", "" + lista.size()));
//                    Extra.sonido(o, NOTE_BASS);
//                    Hotbar.ponerMain(o);
//                }
//                runJugando.clear();
//                runEsperando.clear();
//            }
        }
        LangItemsUpdater.updateLoreRunUnrankedMenu();
    }

    public static void empezarPartyDirecto(List<Player> lista) {
        if (runLibres.isEmpty()) {
            for (Player o : lista) {
                o.sendMessage(noMapsAvailable);
                Extra.sonido(o, NOTE_BASS);
                Hotbar.ponerHotbarParty(o);
            }
        } else {
//            boolean si = false;
            RunMap map = runLibres.get(0);
//            for (RunMap ma : runLibres) {
//                if (ma.getSlots() >= lista.size()) {
//                    map = ma;
//                    si = true;
//                    break;
//                }
//            }
//            if (si) {
                map.getPlayers().clear();
                for (Player p : lista) {
                    map.addPlayer(p);
                    runPartyJugando.put(p, map);
                }
                runLibres.remove(map);
                runOcupadas.add(map);
                runStarting.add(map);
                map.iniciar();
//            } else {
//                for (Player o : lista) {
//                    o.sendMessage(noMapsSlotsAvailable.replaceAll("<slots>", "" + lista.size()));
//                    Extra.sonido(o, NOTE_BASS);
//                    Hotbar.ponerHotbarParty(o);
//                }
//            }
        }
        LangItemsUpdater.updateLoreRunPartyMenu();
    }

    public static void zombieStep(Player p) {
        Zombie z = petsZombie.get(p);
        double x = z.getLocation().getX() + runZombieStep;
        z.teleport(new Location(z.getWorld(), x, z.getLocation().getY(), z.getLocation().getZ(), z.getLocation().getYaw(), z.getLocation().getPitch()));
        if (runJugando.containsKey(p)) {
            if (x > runJugando.get(p).getX()) {
                runJugando.get(p).addGanador(p, z);
            }
        } else if (runRankedJugando.containsKey(p)) {
            if (x > runRankedJugando.get(p).getX()) {
                runRankedJugando.get(p).addGanador(p, z);
            }
        } else if (runPartyJugando.containsKey(p)) {
            if (x > runPartyJugando.get(p).getX()) {
                runPartyJugando.get(p).addGanador(p, z);
            }
        }

        Extra.sonido(p, NOTE_STICKS);
    }
}
