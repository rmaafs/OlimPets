package me.Roy.OlimPets;

import static me.Roy.OlimPets.Extra.partys;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.NOTE_BASS;
import static me.Roy.OlimPets.Lang.NOTE_PLING;
import static me.Roy.OlimPets.Lang.VILLAGER_NO;
import static me.Roy.OlimPets.Lang.VILLAGER_YES;
import static me.Roy.OlimPets.Lang.partyCreateMsg;
import static me.Roy.OlimPets.Lang.partyFull;
import static me.Roy.OlimPets.Lang.partyJoined;
import static me.Roy.OlimPets.Lang.partyKickPlayer;
import static me.Roy.OlimPets.Lang.partyKicked;
import static me.Roy.OlimPets.Lang.partyLeaved;
import static me.Roy.OlimPets.Lang.partyMaxPlayers;
import me.Roy.OlimPets.Objetos.Party;
import org.bukkit.entity.Player;

public class PartyControl {

    public static void createParty(Player p) {
        if (PlayerControl.hasPerm(p, "olimpets.party.create")) {
            if (!partys.containsKey(p)) {
                Party party = new Party(p);
                partys.put(p, party);
                for (String msg : partyCreateMsg) {
                    p.sendMessage(msg);
                }
                Hotbar.ponerHotbarParty(p);
                Extra.sonido(p, NOTE_PLING);
            } else {

            }
        }
    }

    public static void joinParty(Player p, Party party) {
        if (party.getPlayers().size() < partyMaxPlayers) {
            party.getPlayers().add(p);
            party.msg(p, partyJoined.replaceAll("<player>", p.getName()));
            party.song(NOTE_PLING);
            partys.put(p, party);
            Hotbar.ponerHotbarParty(p);
        } else {
            p.sendMessage(partyFull);
            party.getOwner().sendMessage(partyFull);
            Extra.sonido(p, NOTE_BASS);
            Extra.sonido(party.getOwner(), NOTE_BASS);
        }
    }

    public static void salirParty(Player p, boolean online) {
        Party party = partys.get(p);
        if (party.getOwner() == p) {
            party.setNewOwner(party);
            party.song(VILLAGER_YES);
        } else {
            partys.remove(p);
            party.getPlayers().remove(p);
            party.msg(p, partyLeaved.replaceAll("<player>", p.getName()));
            party.song(VILLAGER_NO);
            if (online) {
                p.sendMessage(partyLeaved.replaceAll("<player>", p.getName()));
                Extra.sonido(p, VILLAGER_NO);
            }
        }
    }

    public static void kickParty(Player p) {
        Party party = partys.get(p);
        if (PlayerControl.hasPerm(party.getOwner(), "olimpets.party.kick")) {
            if (party.getOwner() == p) {
                party.setNewOwner(party);
                party.song(VILLAGER_YES);
            } else {
                partys.remove(p);
                party.song(VILLAGER_NO);
                party.getPlayers().remove(p);
                party.msg(p, partyKickPlayer.replaceAll("<player>", p.getName()));
                Hotbar.ponerMain(p);
                p.sendMessage(partyKicked);
            }
        }
    }
}
