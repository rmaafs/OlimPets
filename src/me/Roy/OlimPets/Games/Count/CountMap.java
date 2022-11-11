package me.Roy.OlimPets.Games.Count;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.pets;
import static me.Roy.OlimPets.Extra.petsZombie;
import static me.Roy.OlimPets.Games.Count.CountControl.countContando;
import static me.Roy.OlimPets.Games.Count.CountControl.countJugando;
import static me.Roy.OlimPets.Games.Count.CountControl.countLibres;
import static me.Roy.OlimPets.Games.Count.CountControl.countOcupadas;
import static me.Roy.OlimPets.Games.Count.CountControl.countPartyJugando;
import static me.Roy.OlimPets.Games.Count.CountControl.countStarting;
import static me.Roy.OlimPets.Games.Count.CountControl.countZombies;
import static me.Roy.OlimPets.Games.Count.CountControlRanked.countRankedJugando;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.SPLASH2;
import static me.Roy.OlimPets.Lang.countDraw;
import static me.Roy.OlimPets.Lang.countHow;
import static me.Roy.OlimPets.Lang.countResults;
import static me.Roy.OlimPets.Lang.countWinner;
import static me.Roy.OlimPets.Lang.playerExit;
import static me.Roy.OlimPets.Lang.playerRankedEloFinish;
import static me.Roy.OlimPets.Lang.spawnLobby;
import me.Roy.OlimPets.LangItemsUpdater;
import static me.Roy.OlimPets.Main.version;
import me.Roy.OlimPets.Objetos.CountZombie;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class CountMap {

    Location player, ini, fin;
    List<Player> players = new ArrayList<>();
    int id;
    int preTime = 5;
    int time = 20;
    boolean pregame = false;
    int total = 0;

    public CountMap(Location l1, Location l2, Location l3, int i) {
        player = l1;
        ini = l2;
        fin = l3;
        id = i;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPregame(boolean i) {
        pregame = i;
    }

    public void removePretime() {
        preTime--;
    }

    public void removeTime() {
        time--;
    }

    public boolean isPreGame() {
        return pregame;
    }

    public Integer getPretime() {
        return preTime;
    }

    public Integer getTime() {
        return time;
    }

    public void teleportarSpawn() {
        for (Player p : players) {
            p.teleport(player);
        }
    }

    public void iniciar() {
        pregame = true;
        for (Player p : players) {
            p.setGameMode(GameMode.SURVIVAL);
            p.getInventory().clear();
            for (String s : countHow) {
                p.sendMessage(s);
            }
            if (pets.containsKey(p)) {
                if (pets.get(p).getSummon()) {
                    pets.get(p).kill();
                }
            }
            p.setLevel(0);
            p.teleport(player);
        }
    }

    public void iniciarJuego() {
        song(SPLASH2);
        pregame = false;
        for (Player p : players) {
            countContando.add(p);
        }
        ponerZombie();
    }

    public void presentar() {
        for (Player p : players) {
            for (String s : countResults) {
                if (s.contains("<player>")) {
                    for (Player o : players) {
                        p.sendMessage(s.replaceAll("<player>", o.getName()).replaceAll("<number>", "" + o.getLevel()));
                    }
                } else {
                    p.sendMessage(s.replaceAll("<total>", "" + total));
                }
            }
        }
        acabar();
    }

    public void acabar() {
        CountMap map = null;
//        Player loser = players.get(0);
        String type = "unranked";
        if (countJugando.containsKey(players.get(0))) {
            map = countJugando.get(players.get(0));
        } else if (countRankedJugando.containsKey(players.get(0))) {
            map = countRankedJugando.get(players.get(0));
            type = "ranked";
        } else if (countPartyJugando.containsKey(players.get(0))) {
            map = countPartyJugando.get(players.get(0));
            type = "party";
        }
        for (Player p : players) {
            boolean rank = false;
            p.teleport(spawnLobby);
            if (petsZombie.containsKey(p)) {
                petsZombie.get(p).remove();
            }
            if (countContando.contains(p)) {
                countContando.remove(p);
            }
            if (countJugando.containsKey(p)) {
                countJugando.remove(p);
                Hotbar.ponerMain(p);
            } else if (countRankedJugando.containsKey(p)) {
                countRankedJugando.remove(p);
                Hotbar.ponerMain(p);
                rank = true;
//                if (p != w) {
//                    loser = p;
//                }
            } else if (countPartyJugando.containsKey(p)) {
                countPartyJugando.remove(p);
                Hotbar.ponerHotbarParty(p);
            }
            if (pets.containsKey(p)) {
                if (pets.get(p).getSummon()) {
                    pets.get(p).summon(true, 0);
                }
            }
            if (rank == false && p.getLevel() == total) {
                msg(countWinner.replaceAll("<petname>", pets.get(p).getName()).replaceAll("<player>", p.getName()));
            }
        }
        countOcupadas.remove(map);
        countLibres.add(map);
        if (countStarting.contains(map)) {
            countStarting.remove(map);
        }
        mostrarPlayers();
        if (type.equals("ranked")) {
            LangItemsUpdater.updateLoreCountRankedMenu();
            if (players.get(0).getLevel() == total && players.get(1).getLevel() == total) {
                if (players.get(0).isOnline()) {
                    players.get(0).sendMessage(countDraw);
                }
                if (players.get(1).isOnline()) {
                    players.get(1).sendMessage(countDraw);
                }
            } else if (players.get(0).getLevel() != total && players.get(1).getLevel() != total) {
                Player w = players.get(0);
                Player loser = players.get(1);
                int level1 = players.get(0).getLevel() - total;
                int level2 = players.get(1).getLevel() - total;
                if (level1 < 0) {
                    level1 = level1 * -1;
                }
                if (level2 < 0) {
                    level2 = level2 * -1;
                }
                if (level1 == level2) {
                    if (players.get(0).isOnline()) {
                        players.get(0).sendMessage(countDraw);
                    }
                    if (players.get(1).isOnline()) {
                        players.get(1).sendMessage(countDraw);
                    }
                } else {
                    if (level2 < level1) {
                        w = players.get(1);
                        loser = players.get(0);
                    }
                    int difelo = Extra.eloM(pets.get(w).getElo("count") - pets.get(loser).getElo("count"));
                    pets.get(w).addWins("count", 1);
                    pets.get(w).addPlayed("count", 1);
                    pets.get(loser).addPlayed("count", 1);
                    pets.get(w).addElo("count", difelo);
                    pets.get(loser).removeElo("count", difelo);
                    if (w.isOnline()) {
                        w.sendMessage(playerRankedEloFinish.replaceAll("<player1>", w.getName()).replaceAll("<player2>", loser.getName()).replaceAll("<elo>", "" + difelo).replaceAll("<kit>", "Count").replaceAll("<elo1>", "" + pets.get(w).getElo("count")).replaceAll("<elo2>", "" + pets.get(loser).getElo("count")));
                    }
                    if (loser.isOnline()) {
                        loser.sendMessage(playerRankedEloFinish.replaceAll("<player1>", w.getName()).replaceAll("<player2>", loser.getName()).replaceAll("<elo>", "" + difelo).replaceAll("<kit>", "Count").replaceAll("<elo1>", "" + pets.get(w).getElo("count")).replaceAll("<elo2>", "" + pets.get(loser).getElo("count")));
                    }
                }
            } else {
                Player w = players.get(0);
                Player loser = players.get(1);
                if (players.get(1).getLevel() == total) {
                    w = players.get(1);
                    loser = players.get(0);
                }
                int difelo = Extra.eloM(pets.get(w).getElo("count") - pets.get(loser).getElo("count"));
                pets.get(w).addWins("count", 1);
                pets.get(w).addPlayed("count", 1);
                pets.get(loser).addPlayed("count", 1);
                pets.get(w).addElo("count", difelo);
                pets.get(loser).removeElo("count", difelo);
                if (w.isOnline()) {
                    w.sendMessage(playerRankedEloFinish.replaceAll("<player1>", w.getName()).replaceAll("<player2>", loser.getName()).replaceAll("<elo>", "" + difelo).replaceAll("<kit>", "Count").replaceAll("<elo1>", "" + pets.get(w).getElo("count")).replaceAll("<elo2>", "" + pets.get(loser).getElo("count")));
                }
                if (loser.isOnline()) {
                    loser.sendMessage(playerRankedEloFinish.replaceAll("<player1>", w.getName()).replaceAll("<player2>", loser.getName()).replaceAll("<elo>", "" + difelo).replaceAll("<kit>", "Count").replaceAll("<elo1>", "" + pets.get(w).getElo("count")).replaceAll("<elo2>", "" + pets.get(loser).getElo("count")));
                }
            }
        } else if (type.equals("unranked")) {
            LangItemsUpdater.updateLoreCountUnrankedMenu();
        } else if (type.equals("party")) {
            LangItemsUpdater.updateLoreCountPartyMenu();
        }
        for (Player p : players) {
            p.setLevel(0);
        }
        regenerar();
    }

    public void ponerZombie() {
        Random r = new Random();
        int i = r.nextInt(2);
        if (i == 1) {
            Zombie zombie = version.generar(ini);
            for (Player p : players) {
                version.ponerFakeArmor(p, zombie.getEntityId());
            }
            countZombies.add(new CountZombie(zombie, true, ini, fin));
            total++;
        } else {
            Zombie zombie = version.generar(fin);
            for (Player p : players) {
                version.ponerFakeArmor(p, zombie.getEntityId());
            }
            countZombies.add(new CountZombie(zombie, false, ini, fin));
            total++;
        }
    }

    public void exit(Player o) {
        if (countRankedJugando.containsKey(o)) {
            msg(playerExit.replaceAll("<player>", o.getName()));
            LangItemsUpdater.updateLoreJumpRankedMenu();
            acabar();
        }
        players.remove(o);
    }

    public void regenerar() {
        preTime = 5;
        pregame = true;
        players = new ArrayList<>();
        time = 20;
        total = 0;
    }

    public void ocultarPlayers() {
        for (Player p : players) {
            for (Player o : players) {
                p.hidePlayer(o);
            }
        }
    }

    public void mostrarPlayers() {
        for (Player p : players) {
            for (Player o : players) {
                p.showPlayer(o);
            }
        }
    }

    public void msg(String s) {
        for (Player o : players) {
            o.sendMessage(s);
        }
    }

    public void song(String s) {
        for (Player o : players) {
            Extra.sonido(o, s);
        }
    }
}
