package me.Roy.OlimPets.Games.Jump;

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

public class JumpControl {

    public static int task = 0;
    
    public static List<JumpMap> jumpLibres = new ArrayList<>();
    public static List<JumpMap> jumpOcupadas = new ArrayList<>();
    public static List<JumpMap> jumpStarting = new ArrayList<>();
    
    public static List<Player> jumpEsperando = new ArrayList<>();
    public static HashMap<Player, JumpMap> jumpJugando = new HashMap<>();
    public static HashMap<Player, JumpMap> jumpPartyJugando = new HashMap<>();
    
    public static void ponerEspera(Player p) {
        p.closeInventory();
        if (!jumpEsperando.contains(p)) {
            jumpEsperando.add(p);
        }
        LangItemsUpdater.updateLoreJumpUnrankedMenu();
        for (Player o : jumpEsperando) {
            o.sendMessage(waitingGame.replaceAll("<game>", "Jumper").replaceAll("<number>", "" + jumpEsperando.size()).replaceAll("<max>", "" + unrankedMaxPlayers));
            Extra.sonido(p, NOTE_BASS);
        }
        Hotbar.ponerSalirWaiting(p);
        //if (runEsperando.size() >= unrankedMinPlayers) {
        if (jumpEsperando.size() >= 1) {
            Bukkit.getScheduler().cancelTask(task);
            for (Player o : jumpEsperando) {
                o.sendMessage(startingPreGame.replaceAll("<seconds>", String.valueOf(unrankedPreTime / 20)));
            }
            task = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    Bukkit.getScheduler().cancelTask(task);
                    empezarPreGame(jumpEsperando);
                }
            }, unrankedPreTime);
        }
    }

    public static void salirEspera(Player p) {
        if (!jumpEsperando.isEmpty()) {
            for (Player o : jumpEsperando) {
                o.sendMessage(waitingGame.replaceAll("<game>", "Jumper").replaceAll("<number>", "" + jumpEsperando.size()).replaceAll("<max>", "" + unrankedMaxPlayers));
                Extra.sonido(p, NOTE_BASS);
            }
        }
        if (jumpEsperando.size() < unrankedMinPlayers) {
            Bukkit.getScheduler().cancelTask(task);
        }
        LangItemsUpdater.updateLoreRunUnrankedMenu();
    }

    public static void empezarPreGame(List<Player> lista) {
        if (jumpLibres.isEmpty()) {
            for (Player o : lista) {
                o.sendMessage(noMapsAvailable);
                Extra.sonido(o, NOTE_BASS);
                Hotbar.ponerMain(o);
            }
            jumpJugando.clear();
            jumpEsperando.clear();
        } else {
//            boolean si = false;
            JumpMap map = jumpLibres.get(0);
//            for (JumpMap ma : jumpLibres) {
//                if (ma.getSlots() >= lista.size()) {
//                    map = ma;
//                    si = true;
//                    break;
//                }
//            }
//            if (si) {
                map.getPlayers().clear();
                for (Player p : lista) {
                    map.getPlayers().add(p);
                    jumpJugando.put(p, map);
                }
                jumpLibres.remove(map);
                jumpOcupadas.add(map);
                jumpStarting.add(map);
                jumpEsperando.clear();
                map.iniciar();
//            } else {
//                for (Player o : lista) {
//                    o.sendMessage(noMapsSlotsAvailable.replaceAll("<slots>", "" + lista.size()));
//                    Extra.sonido(o, NOTE_BASS);
//                    Hotbar.ponerMain(o);
//                }
//                jumpJugando.clear();
//                jumpEsperando.clear();
//            }
        }
        LangItemsUpdater.updateLoreRunUnrankedMenu();
    }
    
    public static void empezarPartyDirecto(List<Player> lista){
        if (jumpLibres.isEmpty()) {
            for (Player o : lista) {
                o.sendMessage(noMapsAvailable);
                Extra.sonido(o, NOTE_BASS);
                Hotbar.ponerHotbarParty(o);
            }
        } else {
//            boolean si = false;
            JumpMap map = jumpLibres.get(0);
//            for (JumpMap ma : jumpLibres) {
//                if (ma.getSlots() >= lista.size()) {
//                    map = ma;
//                    si = true;
//                    break;
//                }
//            }
//            if (si) {
                map.getPlayers().clear();
                for (Player p : lista) {
                    map.getPlayers().add(p);
                    jumpPartyJugando.put(p, map);
                }
                jumpLibres.remove(map);
                jumpOcupadas.add(map);
                jumpStarting.add(map);
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
}
