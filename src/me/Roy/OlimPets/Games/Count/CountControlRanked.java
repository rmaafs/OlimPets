package me.Roy.OlimPets.Games.Count;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.pets;
import static me.Roy.OlimPets.Games.Count.CountControl.countLibres;
import static me.Roy.OlimPets.Games.Count.CountControl.countOcupadas;
import static me.Roy.OlimPets.Games.Count.CountControl.countStarting;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.NOTE_BASS;
import static me.Roy.OlimPets.Lang.noMapsAvailable;
import static me.Roy.OlimPets.Lang.playerRankedEloStart;
import static me.Roy.OlimPets.Lang.waitingGame;
import me.Roy.OlimPets.LangItemsUpdater;
import org.bukkit.entity.Player;

public class CountControlRanked {

    public static List<Player> countRankedEsperando = new ArrayList<>();
    public static HashMap<Player, CountMap> countRankedJugando = new HashMap<>();
    
    public static void ponerEspera(Player p) {
        p.closeInventory();
        if (!countRankedEsperando.contains(p)) {
            countRankedEsperando.add(p);
        }
        LangItemsUpdater.updateLoreCountRankedMenu();
        for (Player o : countRankedEsperando) {
            o.sendMessage(waitingGame.replaceAll("<game>", "Count").replaceAll("<number>", "" + countRankedEsperando.size()).replaceAll("<max>", "2"));
            Extra.sonido(p, NOTE_BASS);
        }
        Hotbar.ponerSalirWaiting(p);
        if (countRankedEsperando.size() >= 2) {
            empezarPreGame(countRankedEsperando);
        }
    }

    public static void salirEspera(Player p) {
        if (!countRankedEsperando.isEmpty()) {
            for (Player o : countRankedEsperando) {
                o.sendMessage(waitingGame.replaceAll("<game>", "Count").replaceAll("<number>", "" + countRankedEsperando.size()).replaceAll("<max>", "2"));
                Extra.sonido(p, NOTE_BASS);
            }
        }
        LangItemsUpdater.updateLoreCountRankedMenu();
    }

    public static void empezarPreGame(List<Player> lista) {
        if (countLibres.isEmpty()) {
            for (Player o : lista) {
                o.sendMessage(noMapsAvailable);
                Extra.sonido(o, NOTE_BASS);
                Hotbar.ponerMain(o);
            }
            countRankedJugando.clear();
            countRankedEsperando.clear();
        } else {
            Player p1 = lista.get(0);
            Player p2 = lista.get(1);
            CountMap map = countLibres.get(0);
            map.getPlayers().clear();
            for (Player p : lista) {
                map.getPlayers().add(p);
                countRankedJugando.put(p, map);
            }
            countLibres.remove(map);
            countOcupadas.add(map);
            countStarting.add(map);
            countRankedEsperando.clear();
            map.iniciar();
            p1.sendMessage(playerRankedEloStart.replaceAll("<player1>", p1.getName()).replaceAll("<player2>", p2.getName()).replaceAll("<kit>", "Count").replaceAll("<elo1>", "" + pets.get(p1).getElo("count")).replaceAll("<elo2>", "" + pets.get(p2).getElo("count")));
            p2.sendMessage(playerRankedEloStart.replaceAll("<player1>", p1.getName()).replaceAll("<player2>", p2.getName()).replaceAll("<kit>", "Count").replaceAll("<elo1>", "" + pets.get(p1).getElo("count")).replaceAll("<elo2>", "" + pets.get(p2).getElo("count")));
        }
        LangItemsUpdater.updateLoreCountRankedMenu();
    }
}
