package me.Roy.OlimPets.Objetos;

import java.util.ArrayList;
import java.util.List;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.partys;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.partyNewOwner;
import me.Roy.OlimPets.PlayerControl;
import org.bukkit.entity.Player;

public class Party {

    Player owner;
    List<Player> players = new ArrayList<>();

    public Party(Player p) {
        owner = p;
        if (!players.contains(p)) {
            players.add(p);
        }
    }

    public Party(Player p, List<Player> lista) {
        owner = p;
        players = lista;
        if (!players.contains(p)) {
            players.add(p);
        }
    }

    public Player getOwner() {
        return owner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isOwner(Player p) {
        if (p == owner) {
            return true;
        }
        return false;
    }

    public void setNewOwner(Party party) {
        if (players.contains(owner)) {
            players.remove(owner);
        }
        if (players.size() >= 1) {
            partys.remove(owner);
            Hotbar.ponerMain(owner);
            owner = players.get(0);
            partys.put(owner, party);
            party.msg(owner, partyNewOwner.replaceAll("<player>", owner.getName()));
            if (!PlayerControl.isPlaying(owner)) {
                Hotbar.ponerHotbarParty(owner);
            }
        } else {
            partys.remove(owner);
        }
    }

    public void msg(Player o, String msg) {
        for (Player p : players) {
            p.sendMessage(msg);
        }
    }

    public void song(String s) {
        for (Player o : players) {
            Extra.sonido(o, s);
        }
    }
}
