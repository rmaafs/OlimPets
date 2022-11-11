package me.Roy.OlimPets.Games.Antilover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.pets;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverLibres;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverOcupadas;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverStarting;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.NOTE_BASS;
import static me.Roy.OlimPets.Lang.noMapsAvailable;
import static me.Roy.OlimPets.Lang.playerRankedEloStart;
import static me.Roy.OlimPets.Lang.waitingGame;
import me.Roy.OlimPets.LangItemsUpdater;
import org.bukkit.entity.Player;

public class AntiloverControlRanked {
    public static List<Player> antiloverRankedEsperando = new ArrayList<>();
    public static HashMap<Player, AntiloverMap> antiloverRankedJugando = new HashMap<>();

    public static void ponerEspera(Player p) {
        p.closeInventory();
        if (!antiloverRankedEsperando.contains(p)) {
            antiloverRankedEsperando.add(p);
        }
        LangItemsUpdater.updateLoreAntiloverRankedMenu();
        for (Player o : antiloverRankedEsperando) {
            o.sendMessage(waitingGame.replaceAll("<game>", "AntiLover").replaceAll("<number>", "" + antiloverRankedEsperando.size()).replaceAll("<max>", "2"));
            Extra.sonido(p, NOTE_BASS);
        }
        Hotbar.ponerSalirWaiting(p);
        if (antiloverRankedEsperando.size() >= 2) {
            empezarPreGame(antiloverRankedEsperando);
        }
    }

    public static void salirEspera(Player p) {
        if (!antiloverRankedEsperando.isEmpty()) {
            for (Player o : antiloverRankedEsperando) {
                o.sendMessage(waitingGame.replaceAll("<game>", "AntiLover").replaceAll("<number>", "" + antiloverRankedEsperando.size()).replaceAll("<max>", "2"));
                Extra.sonido(p, NOTE_BASS);
            }
        }
        LangItemsUpdater.updateLoreAntiloverRankedMenu();
    }

    public static void empezarPreGame(List<Player> lista) {
        if (antiloverLibres.isEmpty()) {
            for (Player o : lista) {
                o.sendMessage(noMapsAvailable);
                Extra.sonido(o, NOTE_BASS);
                Hotbar.ponerMain(o);
            }
            antiloverRankedJugando.clear();
            antiloverRankedEsperando.clear();
        } else {
            Player p1 = lista.get(0);
            Player p2 = lista.get(1);
            AntiloverMap map = antiloverLibres.get(0);
            map.getPlayers().clear();
            for (Player p : lista) {
                map.getPlayers().add(p);
                antiloverRankedJugando.put(p, map);
            }
            antiloverLibres.remove(map);
            antiloverOcupadas.add(map);
            antiloverStarting.add(map);
            antiloverRankedEsperando.clear();
            map.iniciar();
            p1.sendMessage(playerRankedEloStart.replaceAll("<player1>", p1.getName()).replaceAll("<player2>", p2.getName()).replaceAll("<kit>", "AntiLover").replaceAll("<elo1>", "" + pets.get(p1).getElo("antilover")).replaceAll("<elo2>", "" + pets.get(p2).getElo("antilover")));
            p2.sendMessage(playerRankedEloStart.replaceAll("<player1>", p1.getName()).replaceAll("<player2>", p2.getName()).replaceAll("<kit>", "AntiLover").replaceAll("<elo1>", "" + pets.get(p1).getElo("antilover")).replaceAll("<elo2>", "" + pets.get(p2).getElo("antilover")));
        }
        LangItemsUpdater.updateLoreAntiloverRankedMenu();
    }
}
