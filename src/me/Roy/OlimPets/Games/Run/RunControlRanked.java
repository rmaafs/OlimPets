package me.Roy.OlimPets.Games.Run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.pets;
import static me.Roy.OlimPets.Games.Run.RunControl.runLibres;
import static me.Roy.OlimPets.Games.Run.RunControl.runOcupadas;
import static me.Roy.OlimPets.Games.Run.RunControl.runStarting;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.NOTE_BASS;
import static me.Roy.OlimPets.Lang.noMapsAvailable;
import static me.Roy.OlimPets.Lang.playerRankedEloStart;
import static me.Roy.OlimPets.Lang.unrankedMinPlayers;
import static me.Roy.OlimPets.Lang.waitingGame;
import me.Roy.OlimPets.LangItemsUpdater;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RunControlRanked {
    
    public static List<Player> runRankedEsperando = new ArrayList<>();
    public static HashMap<Player, RunMap> runRankedJugando = new HashMap<>();
    public static int task = 0;

    public static void ponerEspera(Player p) {
        p.closeInventory();
        if (!runRankedEsperando.contains(p)) {
            runRankedEsperando.add(p);
        }
        LangItemsUpdater.updateLoreRunRankedMenu();
        for (Player o : runRankedEsperando) {
            o.sendMessage(waitingGame.replaceAll("<game>", "Run").replaceAll("<number>", "" + runRankedEsperando.size()).replaceAll("<max>", "2"));
            Extra.sonido(p, NOTE_BASS);
        }
        Hotbar.ponerSalirWaiting(p);
        //if (runEsperando.size() >= unrankedMinPlayers) {
        if (runRankedEsperando.size() >= 2) {
            //Bukkit.getScheduler().cancelTask(task);
            //for (Player o : runRankedEsperando) {
            //    o.sendMessage(startingPreGame.replaceAll("<seconds>", String.valueOf(unrankedPreTime / 20)));
            //}
            //task = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                //public void run() {
                    //Bukkit.getScheduler().cancelTask(task);
                    empezarPreGame(runRankedEsperando);
                //}
            //}, unrankedPreTime);
        }
    }

    public static void salirEspera(Player p) {
        if (!runRankedEsperando.isEmpty()) {
            for (Player o : runRankedEsperando) {
                o.sendMessage(waitingGame.replaceAll("<game>", "Run").replaceAll("<number>", "" + runRankedEsperando.size()).replaceAll("<max>", "2"));
                Extra.sonido(p, NOTE_BASS);
            }
        }
        if (runRankedEsperando.size() < unrankedMinPlayers) {
            Bukkit.getScheduler().cancelTask(task);
        }
        LangItemsUpdater.updateLoreRunRankedMenu();
    }

    public static void empezarPreGame(List<Player> lista) {
        if (runLibres.isEmpty()) {
            for (Player o : lista) {
                o.sendMessage(noMapsAvailable);
                Extra.sonido(o, NOTE_BASS);
                Hotbar.ponerMain(o);
            }
            runRankedJugando.clear();
            runRankedEsperando.clear();
        } else {
            Player p1 = lista.get(0);
            Player p2 = lista.get(1);
            RunMap map = runLibres.get(0);
            map.getPlayers().clear();
            for (Player p : lista) {
                map.addPlayer(p);
                runRankedJugando.put(p, map);
            }
            runLibres.remove(map);
            runOcupadas.add(map);
            runStarting.add(map);
            runRankedEsperando.clear();
            map.iniciar();
            p1.sendMessage(playerRankedEloStart.replaceAll("<player1>", p1.getName()).replaceAll("<player2>", p2.getName()).replaceAll("<kit>", "Run").replaceAll("<elo1>", "" + pets.get(p1).getElo("run")).replaceAll("<elo2>", "" + pets.get(p2).getElo("run")));
            p2.sendMessage(playerRankedEloStart.replaceAll("<player1>", p1.getName()).replaceAll("<player2>", p2.getName()).replaceAll("<kit>", "Run").replaceAll("<elo1>", "" + pets.get(p1).getElo("run")).replaceAll("<elo2>", "" + pets.get(p2).getElo("run")));
        }
        LangItemsUpdater.updateLoreRunRankedMenu();
    }
}
