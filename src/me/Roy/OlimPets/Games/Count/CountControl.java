package me.Roy.OlimPets.Games.Count;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.Roy.OlimPets.Extra;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.NOTE_BASS;
import static me.Roy.OlimPets.Lang.noMapsAvailable;
import static me.Roy.OlimPets.Lang.startingPreGame;
import static me.Roy.OlimPets.Lang.unrankedMaxPlayers;
import static me.Roy.OlimPets.Lang.unrankedMinPlayers;
import static me.Roy.OlimPets.Lang.unrankedPreTime;
import static me.Roy.OlimPets.Lang.waitingGame;
import me.Roy.OlimPets.LangItemsUpdater;
import static me.Roy.OlimPets.Main.plugin;
import me.Roy.OlimPets.Objetos.CountZombie;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CountControl {

    public static int task = 0;

    public static List<CountMap> countLibres = new ArrayList<>();
    public static List<CountMap> countOcupadas = new ArrayList<>();
    public static List<CountMap> countStarting = new ArrayList<>();

    public static List<Player> countEsperando = new ArrayList<>();
    public static HashMap<Player, CountMap> countJugando = new HashMap<>();
    public static HashMap<Player, CountMap> countPartyJugando = new HashMap<>();

    public static List<Player> countContando = new ArrayList<>();
    public static List<CountZombie> countZombies = new ArrayList<>();
    
    public static void ponerEspera(Player p) {
        p.closeInventory();
        if (!countEsperando.contains(p)) {
            countEsperando.add(p);
        }
        LangItemsUpdater.updateLoreCountUnrankedMenu();
        for (Player o : countEsperando) {
            o.sendMessage(waitingGame.replaceAll("<game>", "Count").replaceAll("<number>", "" + countEsperando.size()).replaceAll("<max>", "" + unrankedMaxPlayers));
            Extra.sonido(p, NOTE_BASS);
        }
        Hotbar.ponerSalirWaiting(p);
        //if (runEsperando.size() >= unrankedMinPlayers) {
        if (countEsperando.size() >= 1) {
            Bukkit.getScheduler().cancelTask(task);
            for (Player o : countEsperando) {
                o.sendMessage(startingPreGame.replaceAll("<seconds>", String.valueOf(unrankedPreTime / 20)));
            }
            task = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    Bukkit.getScheduler().cancelTask(task);
                    empezarPreGame(countEsperando);
                }
            }, unrankedPreTime);
        }
    }

    public static void salirEspera(Player p) {
        if (!countEsperando.isEmpty()) {
            for (Player o : countEsperando) {
                o.sendMessage(waitingGame.replaceAll("<game>", "Count").replaceAll("<number>", "" + countEsperando.size()).replaceAll("<max>", "" + unrankedMaxPlayers));
                Extra.sonido(p, NOTE_BASS);
            }
        }
        if (countEsperando.size() < unrankedMinPlayers) {
            Bukkit.getScheduler().cancelTask(task);
        }
        LangItemsUpdater.updateLoreCountUnrankedMenu();
    }

    public static void empezarPreGame(List<Player> lista) {
        if (countLibres.isEmpty()) {
            for (Player o : lista) {
                o.sendMessage(noMapsAvailable);
                Extra.sonido(o, NOTE_BASS);
                Hotbar.ponerMain(o);
            }
            countJugando.clear();
            countEsperando.clear();
        } else {
            CountMap map = countLibres.get(0);
                map.getPlayers().clear();
                for (Player p : lista) {
                    map.getPlayers().add(p);
                    countJugando.put(p, map);
                }
                countLibres.remove(map);
                countOcupadas.add(map);
                countStarting.add(map);
                countEsperando.clear();
                map.iniciar();
        }
        LangItemsUpdater.updateLoreCountUnrankedMenu();
    }
    
    public static void empezarPartyDirecto(List<Player> lista){
        if (countLibres.isEmpty()) {
            for (Player o : lista) {
                o.sendMessage(noMapsAvailable);
                Extra.sonido(o, NOTE_BASS);
                Hotbar.ponerHotbarParty(o);
            }
        } else {
            CountMap map = countLibres.get(0);
                map.getPlayers().clear();
                for (Player p : lista) {
                    map.getPlayers().add(p);
                    countPartyJugando.put(p, map);
                }
                countLibres.remove(map);
                countOcupadas.add(map);
                countStarting.add(map);
                map.iniciar();
        }
        LangItemsUpdater.updateLoreCountPartyMenu();
    }
}
