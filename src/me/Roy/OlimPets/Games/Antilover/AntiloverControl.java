package me.Roy.OlimPets.Games.Antilover;

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
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AntiloverControl {
    public static List<AntiloverMap> antiloverLibres = new ArrayList<>();
    public static List<AntiloverMap> antiloverOcupadas = new ArrayList<>();
    public static List<AntiloverMap> antiloverStarting = new ArrayList<>();

    public static List<Player> antiloverEsperando = new ArrayList<>();
    public static HashMap<Player, AntiloverMap> antiloverJugando = new HashMap<>();
    public static HashMap<Player, AntiloverMap> antiloverPartyJugando = new HashMap<>();
    
    public static List<Player> antiloverPlayers = new ArrayList<>();
    public static int task = 0;
    
    public static void ponerEspera(Player p) {
        p.closeInventory();
        if (!antiloverEsperando.contains(p)) {
            antiloverEsperando.add(p);
        }
        LangItemsUpdater.updateLoreAntiloverUnrankedMenu();
        for (Player o : antiloverEsperando) {
            o.sendMessage(waitingGame.replaceAll("<game>", "AntiLover").replaceAll("<number>", "" + antiloverEsperando.size()).replaceAll("<max>", "" + unrankedMaxPlayers));
            Extra.sonido(p, NOTE_BASS);
        }
        Hotbar.ponerSalirWaiting(p);
        //if (runEsperando.size() >= unrankedMinPlayers) {
        if (antiloverEsperando.size() >= 1) {
            Bukkit.getScheduler().cancelTask(task);
            for (Player o : antiloverEsperando) {
                o.sendMessage(startingPreGame.replaceAll("<seconds>", String.valueOf(unrankedPreTime / 20)));
            }
            task = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    Bukkit.getScheduler().cancelTask(task);
                    empezarPreGame(antiloverEsperando);
                }
            }, unrankedPreTime);
        }
    }

    public static void salirEspera(Player p) {
        if (!antiloverEsperando.isEmpty()) {
            for (Player o : antiloverEsperando) {
                o.sendMessage(waitingGame.replaceAll("<game>", "AntiLover").replaceAll("<number>", "" + antiloverEsperando.size()).replaceAll("<max>", "" + unrankedMaxPlayers));
                Extra.sonido(p, NOTE_BASS);
            }
        }
        if (antiloverEsperando.size() < unrankedMinPlayers) {
            Bukkit.getScheduler().cancelTask(task);
        }
        LangItemsUpdater.updateLoreAntiloverUnrankedMenu();
    }

    public static void empezarPreGame(List<Player> lista) {
        if (antiloverLibres.isEmpty()) {
            for (Player o : lista) {
                o.sendMessage(noMapsAvailable);
                Extra.sonido(o, NOTE_BASS);
                Hotbar.ponerMain(o);
            }
            antiloverJugando.clear();
            antiloverEsperando.clear();
        } else {
            AntiloverMap map = antiloverLibres.get(0);
                map.getPlayers().clear();
                for (Player p : lista) {
                    map.getPlayers().add(p);
                    antiloverJugando.put(p, map);
                }
                antiloverLibres.remove(map);
                antiloverOcupadas.add(map);
                antiloverStarting.add(map);
                antiloverEsperando.clear();
                map.iniciar();
        }
        LangItemsUpdater.updateLoreAntiloverUnrankedMenu();
    }
    
    public static void empezarPartyDirecto(List<Player> lista){
        if (antiloverLibres.isEmpty()) {
            for (Player o : lista) {
                o.sendMessage(noMapsAvailable);
                Extra.sonido(o, NOTE_BASS);
                Hotbar.ponerHotbarParty(o);
            }
        } else {
            AntiloverMap map = antiloverLibres.get(0);
                map.getPlayers().clear();
                for (Player p : lista) {
                    map.getPlayers().add(p);
                    antiloverPartyJugando.put(p, map);
                }
                antiloverLibres.remove(map);
                antiloverOcupadas.add(map);
                antiloverStarting.add(map);
                map.iniciar();
        }
        LangItemsUpdater.updateLoreAntiloverPartyMenu();
    }
}
