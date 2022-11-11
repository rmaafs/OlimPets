package me.Roy.OlimPets.Games.Run;

import java.util.ArrayList;
import java.util.List;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.pets;
import static me.Roy.OlimPets.Extra.petsZombie;
import static me.Roy.OlimPets.Games.Run.RunControl.runClickeando;
import static me.Roy.OlimPets.Games.Run.RunControl.runJugando;
import static me.Roy.OlimPets.Games.Run.RunControl.runLibres;
import static me.Roy.OlimPets.Games.Run.RunControl.runOcupadas;
import static me.Roy.OlimPets.Games.Run.RunControl.runPartyJugando;
import static me.Roy.OlimPets.Games.Run.RunControl.runStarting;
import static me.Roy.OlimPets.Games.Run.RunControlRanked.runRankedJugando;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.SPLASH2;
import static me.Roy.OlimPets.Lang.playerExit;
import static me.Roy.OlimPets.Lang.playerRankedEloFinish;
import static me.Roy.OlimPets.Lang.runFinish;
import static me.Roy.OlimPets.Lang.runHow;
import static me.Roy.OlimPets.Lang.runWinner;
import static me.Roy.OlimPets.Lang.runZombieSpawnDistance;
import static me.Roy.OlimPets.Lang.spawnLobby;
import me.Roy.OlimPets.LangItemsUpdater;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class RunMap {

    Location player, zombie;
    int x = 0;
    int id = 0;
    int preTime = 5;
    boolean pregame = false;
    List<Player> players = new ArrayList<>();
    List<Player> ganadores = new ArrayList<>();

    public RunMap(Location pl, Location zo, int xx, int i) {
        player = pl;
        zombie = zo;
        x = xx;
        id = i;
    }

    public Location getSpawns(int i) {
        Location loc = zombie.clone();
        double r = i * runZombieSpawnDistance;
        loc.setZ(loc.getZ() + r);
        return loc;
    }

    public Integer getX() {
        return x;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPretime() {
        return preTime;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getGanadores() {
        return players;
    }

    public boolean isPreGame() {
        return pregame;
    }

    //--------------
    public void setPlayers(List<Player> i) {
        players = i;
    }

    public void addPlayer(Player i) {
        players.add(i);
    }

    int pos = 1;

    public void addGanador(Player p, Zombie z) {
        msg(runFinish.replaceAll("<petname>", z.getCustomName()).replaceAll("<player>", p.getName()).replaceAll("<position>", "" + pos));
        pos++;
        runClickeando.remove(p);
        ganadores.add(p);
        if ((runRankedJugando.containsKey(p) || runJugando.containsKey(p)) && ganadores.size() >= players.size() - 1) {
            acabar(ganadores.get(0));
        } else if (ganadores.size() == players.size()) {
            acabar(ganadores.get(0));
        }
        z.remove();
    }

    public void acabar(Player w) {
        RunMap map = null;
        Player loser = w;
        String type = "unranked";
        if (runJugando.containsKey(w)) {
            map = runJugando.get(w);
        } else if (runRankedJugando.containsKey(w)) {
            map = runRankedJugando.get(w);
            type = "ranked";
        } else if (runPartyJugando.containsKey(w)) {
            map = runPartyJugando.get(w);
            type = "party";
        }
        for (Player p : players) {
            p.teleport(spawnLobby);
            if (petsZombie.containsKey(p)) {
                petsZombie.get(p).remove();
            }
            if (runClickeando.containsKey(p)) {
                runClickeando.remove(p);
            }
            if (runJugando.containsKey(p)) {
                runJugando.remove(p);
                Hotbar.ponerMain(p);
            } else if (runRankedJugando.containsKey(p)) {
                runRankedJugando.remove(p);
                Hotbar.ponerMain(p);
                if (p != w) {
                    loser = p;
                }
            } else if (runPartyJugando.containsKey(p)) {
                runPartyJugando.remove(p);
                Hotbar.ponerHotbarParty(p);
            }
            if (pets.containsKey(p)) {
                if (pets.get(p).getSummon()) {
                    pets.get(p).summon(true, 0);
                }
            }
        }
        msg(runWinner.replaceAll("<petname>", pets.get(w).getName()).replaceAll("<player>", w.getName()));
        runOcupadas.remove(map);
        runLibres.add(map);
        if (runStarting.contains(map)) {
            runStarting.remove(map);
        }
        mostrarPlayers();
        regenerar();
        if (type.equals("ranked")) {
            LangItemsUpdater.updateLoreRunRankedMenu();
            int difelo = Extra.eloM(pets.get(w).getElo("run") - pets.get(loser).getElo("run"));
            pets.get(w).addWins("run", 1);
            pets.get(w).addPlayed("run", 1);
            pets.get(loser).addPlayed("run", 1);
            pets.get(w).addElo("run", difelo);
            pets.get(loser).removeElo("run", difelo);
            if (w.isOnline()) {
                w.sendMessage(playerRankedEloFinish.replaceAll("<player1>", w.getName()).replaceAll("<player2>", loser.getName()).replaceAll("<elo>", "" + difelo).replaceAll("<kit>", "Run").replaceAll("<elo1>", "" + pets.get(w).getElo("run")).replaceAll("<elo2>", "" + pets.get(loser).getElo("run")));
            }
            if (loser.isOnline()) {
                loser.sendMessage(playerRankedEloFinish.replaceAll("<player1>", w.getName()).replaceAll("<player2>", loser.getName()).replaceAll("<elo>", "" + difelo).replaceAll("<kit>", "Run").replaceAll("<elo1>", "" + pets.get(w).getElo("run")).replaceAll("<elo2>", "" + pets.get(loser).getElo("run")));
            }
        } else if (type.equals("unranked")) {
            LangItemsUpdater.updateLoreRunUnrankedMenu();
        } else if (type.equals("party")) {
            LangItemsUpdater.updateLoreRunPartyMenu();
        }
    }
    
    public void aparecerZombies(){
        int i = 0;
        for (Player p : players) {
            pets.get(p).summon(false, 1, getSpawns(i));
            i++;
        }
    }

    public void setPregame(boolean i) {
        pregame = i;
    }

    public void removePretime() {
        preTime--;
    }

    //---------------------
    public void iniciar() {
        pregame = true;
        teleportarSpawn();
        int i = 0;
        for (Player p : players) {
            p.setGameMode(GameMode.SURVIVAL);
            p.getInventory().clear();
            for (String s : runHow) {
                p.sendMessage(s);
            }
            if (pets.containsKey(p)) {
                if (pets.get(p).getSummon()) {
                    pets.get(p).kill();
                }
            }
            //pets.get(p).summon(false, 1, getSpawns(i));
            i++;
        }
    }

    public void iniciarJuego() {
        song(SPLASH2);
        for (Player p : players) {
            runClickeando.put(p, false);
        }
    }

    public void regenerar() {
        preTime = 5;
        pregame = false;
        players = new ArrayList<>();
        ganadores = new ArrayList<>();
        pos = 1;
    }

    public void teleportarSpawn() {
        for (Player p : players) {
            p.teleport(player);
        }
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
        for (Player o : this.players) {
            o.sendMessage(s);
        }
    }

    public void song(String s) {
        for (Player o : players) {
            Extra.sonido(o, s);
        }
    }

    public void exit(Player o) {
        if (runRankedJugando.containsKey(o)) {
            msg(playerExit.replaceAll("<player>", o.getName()));
            LangItemsUpdater.updateLoreRunRankedMenu();
            Player w = o;
            if (players.get(0) != o) {
                w = players.get(0);
            } else {
                w = players.get(1);
            }
            acabar(w);
//            int difelo = Extra.eloM(pets.get(w).getElo("run") - pets.get(o).getElo("run"));
//            pets.get(w).addWins("run", 1);
//            pets.get(o).addPlayed("run", 1);
//            pets.get(o).addPlayed("run", 1);
//            pets.get(w).addElo("run", difelo);
//            pets.get(o).removeElo("run", difelo);
//            if (w.isOnline()) {
//                w.sendMessage(playerRankedEloFinish.replaceAll("<player1>", w.getName()).replaceAll("<player2>", o.getName()).replaceAll("<elo>", "" + difelo).replaceAll("<kit>", "Run").replaceAll("<elo1>", "" + pets.get(w).getElo("run")).replaceAll("<elo2>", "" + pets.get(o).getElo("run")));
//            }
//            if (o.isOnline()) {
//                o.sendMessage(playerRankedEloFinish.replaceAll("<player1>", w.getName()).replaceAll("<player2>", o.getName()).replaceAll("<elo>", "" + difelo).replaceAll("<kit>", "Run").replaceAll("<elo1>", "" + pets.get(w).getElo("run")).replaceAll("<elo2>", "" + pets.get(o).getElo("run")));
//            }
        }
        players.remove(o);
    }
}
