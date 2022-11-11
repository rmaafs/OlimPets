package me.Roy.OlimPets.Games.Jump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.pets;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpLibres;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpOcupadas;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpStarting;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.NOTE_BASS;
import static me.Roy.OlimPets.Lang.noMapsAvailable;
import static me.Roy.OlimPets.Lang.playerRankedEloStart;
import static me.Roy.OlimPets.Lang.waitingGame;
import me.Roy.OlimPets.LangItemsUpdater;
import org.bukkit.entity.Player;

public class JumpControlRanked {

    public static List<Player> jumpRankedEsperando = new ArrayList<>();
    public static HashMap<Player, JumpMap> jumpRankedJugando = new HashMap<>();

    public static void ponerEspera(Player p) {
        p.closeInventory();
        if (!jumpRankedEsperando.contains(p)) {
            jumpRankedEsperando.add(p);
        }
        LangItemsUpdater.updateLoreJumpRankedMenu();
        for (Player o : jumpRankedEsperando) {
            o.sendMessage(waitingGame.replaceAll("<game>", "Jumper").replaceAll("<number>", "" + jumpRankedEsperando.size()).replaceAll("<max>", "2"));
            Extra.sonido(p, NOTE_BASS);
        }
        Hotbar.ponerSalirWaiting(p);
        if (jumpRankedEsperando.size() >= 2) {
            empezarPreGame(jumpRankedEsperando);
        }
    }

    public static void salirEspera(Player p) {
        if (!jumpRankedEsperando.isEmpty()) {
            for (Player o : jumpRankedEsperando) {
                o.sendMessage(waitingGame.replaceAll("<game>", "Jumper").replaceAll("<number>", "" + jumpRankedEsperando.size()).replaceAll("<max>", "2"));
                Extra.sonido(p, NOTE_BASS);
            }
        }
        LangItemsUpdater.updateLoreJumpRankedMenu();
    }

    public static void empezarPreGame(List<Player> lista) {
        if (jumpLibres.isEmpty()) {
            for (Player o : lista) {
                o.sendMessage(noMapsAvailable);
                Extra.sonido(o, NOTE_BASS);
                Hotbar.ponerMain(o);
            }
            jumpRankedJugando.clear();
            jumpRankedEsperando.clear();
        } else {
            Player p1 = lista.get(0);
            Player p2 = lista.get(1);
            JumpMap map = jumpLibres.get(0);
            map.getPlayers().clear();
            for (Player p : lista) {
                map.getPlayers().add(p);
                jumpRankedJugando.put(p, map);
            }
            jumpLibres.remove(map);
            jumpOcupadas.add(map);
            jumpStarting.add(map);
            jumpRankedEsperando.clear();
            map.iniciar();
            p1.sendMessage(playerRankedEloStart.replaceAll("<player1>", p1.getName()).replaceAll("<player2>", p2.getName()).replaceAll("<kit>", "Jumper").replaceAll("<elo1>", "" + pets.get(p1).getElo("jump")).replaceAll("<elo2>", "" + pets.get(p2).getElo("jump")));
            p2.sendMessage(playerRankedEloStart.replaceAll("<player1>", p1.getName()).replaceAll("<player2>", p2.getName()).replaceAll("<kit>", "Jumper").replaceAll("<elo1>", "" + pets.get(p1).getElo("jump")).replaceAll("<elo2>", "" + pets.get(p2).getElo("jump")));
        }
        LangItemsUpdater.updateLoreJumpRankedMenu();
    }
}
