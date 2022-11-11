package me.Roy.OlimPets.Games.Antilover;

import java.util.ArrayList;
import java.util.List;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.pets;
import static me.Roy.OlimPets.Extra.petsZombie;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverJugando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverLibres;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverPartyJugando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverPlayers;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverStarting;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControlRanked.antiloverRankedJugando;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.NOTE_STICKS;
import static me.Roy.OlimPets.Lang.SPLASH2;
import static me.Roy.OlimPets.Lang.VILLAGER_YES;
import static me.Roy.OlimPets.Lang.antiloverHow;
import static me.Roy.OlimPets.Lang.antiloverLose;
import static me.Roy.OlimPets.Lang.antiloverWinner;
import static me.Roy.OlimPets.Lang.antiloverZombieStep;
import static me.Roy.OlimPets.Lang.playerExit;
import static me.Roy.OlimPets.Lang.playerRankedEloFinish;
import static me.Roy.OlimPets.Lang.spawnLobby;
import me.Roy.OlimPets.LangItemsUpdater;
import static me.Roy.OlimPets.Main.version;
import me.Roy.OlimPets.Objetos.AntiloverZombie;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

public class AntiloverMap {

    Location spawnPlayer, spawnZombie, spawnLovers;
    List<Player> players = new ArrayList<>();
    List<Player> ganadores = new ArrayList<>();
    List<AntiloverZombie> zombies = new ArrayList<>();
    double firstB, secondB;
    int id;
    int preTime = 5;
    boolean pregame = false, terminada = false;

    public AntiloverMap(Location pla, Location zo, Location lov, int f, int s, int i) {
        spawnPlayer = pla;
        spawnZombie = zo;
        spawnLovers = lov;
        firstB = f;
        secondB = s;
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

    public boolean isPreGame() {
        return pregame;
    }
    
    public boolean isTerminada() {
        return terminada;
    }
    
    public void setTerminada(boolean i) {
        terminada = i;
    }

    public Integer getPretime() {
        return preTime;
    }

    public void teleportarSpawn() {
        for (Player p : players) {
            p.teleport(spawnPlayer);
        }
    }

    public void iniciar() {
        zombies.clear();
        pregame = true;
        for (Player p : players) {
            ganadores.add(p);
            //antiloverPlayers.add(p);
            p.setGameMode(GameMode.SURVIVAL);
            p.getInventory().clear();
            for (String s : antiloverHow) {
                p.sendMessage(s);
            }
            if (pets.containsKey(p)) {
                if (pets.get(p).getSummon()) {
                    pets.get(p).kill();
                }
            }
            //pets.get(p).summon(false, 1, spawnZombie);
            p.teleport(spawnPlayer);
        }
    }
    
    public void ponerZombies(){
        List<Zombie> pre = new ArrayList<>();
        for (Player p : players) {
            antiloverPlayers.add(p);
            pets.get(p).summon(false, 1, spawnZombie);
            p.getWorld().loadChunk(p.getLocation().getChunk());
        }
        Location loc = spawnLovers.clone();
        //loc.setX(Extra.rand((int) firstB, (int) secondB));
        pre.add(version.generar(loc));
        loc.setZ(loc.getZ() - 16);
        pre.add(version.generar(loc));
        loc.setZ(loc.getZ() - 16);
        pre.add(version.generar(loc));
        for (Zombie z: pre){
            z.getWorld().loadChunk(z.getLocation().getChunk());
            z.remove();
        }
    }

    public void iniciarJuego() {
        song(SPLASH2);
        pregame = false;
    }

    public void acabar(Player w) {
        for (AntiloverZombie z : zombies) {
            z.matar();
        }
        AntiloverMap map = null;
        Player loser = w;
        String type = "unranked";
        if (antiloverJugando.containsKey(w)) {
            map = antiloverJugando.get(w);
        } else if (antiloverRankedJugando.containsKey(w)) {
            map = antiloverRankedJugando.get(w);
            type = "ranked";
        } else if (antiloverPartyJugando.containsKey(w)) {
            map = antiloverPartyJugando.get(w);
            type = "party";
        }
        for (Player p : players) {
            p.teleport(spawnLobby);
            if (petsZombie.containsKey(p)) {
                petsZombie.get(p).remove();
            }
            if (antiloverJugando.containsKey(p)) {
                antiloverJugando.remove(p);
                Hotbar.ponerMain(p);
            } else if (antiloverRankedJugando.containsKey(p)) {
                antiloverRankedJugando.remove(p);
                Hotbar.ponerMain(p);
                if (p != w) {
                    loser = p;
                }
            } else if (antiloverPartyJugando.containsKey(p)) {
                antiloverPartyJugando.remove(p);
                Hotbar.ponerHotbarParty(p);
            }
            if (antiloverPlayers.contains(p)) {
                antiloverPlayers.remove(p);
            }
            if (pets.containsKey(p)) {
                if (pets.get(p).getSummon()) {
                    pets.get(p).summon(true, 0);
                }
            }
        }
        msg(antiloverWinner.replaceAll("<petname>", pets.get(w).getName()).replaceAll("<player>", w.getName()));
        //antiloverOcupadas.remove(map);
        terminada = true;
        antiloverLibres.add(map);
        if (antiloverStarting.contains(map)) {
            antiloverStarting.remove(map);
        }
        mostrarPlayers();
        regenerar();
        if (type.equals("ranked")) {
            LangItemsUpdater.updateLoreAntiloverRankedMenu();
            int difelo = Extra.eloM(pets.get(w).getElo("antilover") - pets.get(loser).getElo("antilover"));
            pets.get(w).addWins("antilover", 1);
            pets.get(w).addPlayed("antilover", 1);
            pets.get(loser).addPlayed("antilover", 1);
            pets.get(w).addElo("antilover", difelo);
            pets.get(loser).removeElo("antilover", difelo);
            if (w.isOnline()) {
                w.sendMessage(playerRankedEloFinish.replaceAll("<player1>", w.getName()).replaceAll("<player2>", loser.getName()).replaceAll("<elo>", "" + difelo).replaceAll("<kit>", "AntiLover").replaceAll("<elo1>", "" + pets.get(w).getElo("antilover")).replaceAll("<elo2>", "" + pets.get(loser).getElo("antilover")));
            }
            if (loser.isOnline()) {
                loser.sendMessage(playerRankedEloFinish.replaceAll("<player1>", w.getName()).replaceAll("<player2>", loser.getName()).replaceAll("<elo>", "" + difelo).replaceAll("<kit>", "AntiLover").replaceAll("<elo1>", "" + pets.get(w).getElo("antilover")).replaceAll("<elo2>", "" + pets.get(loser).getElo("antilover")));
            }
        } else if (type.equals("unranked")) {
            LangItemsUpdater.updateLoreAntiloverUnrankedMenu();
        } else if (type.equals("party")) {
            LangItemsUpdater.updateLoreAntiloverPartyMenu();
        }
    }

    public void exit(Player o) {
        if (antiloverRankedJugando.containsKey(o)) {
            msg(playerExit.replaceAll("<player>", o.getName()));
            LangItemsUpdater.updateLoreJumpRankedMenu();
            Player w = o;
            if (players.get(0) != o) {
                w = players.get(0);
            } else {
                w = players.get(1);
            }
            acabar(w);
        }
        players.remove(o);
        ganadores.remove(o);
    }

    public void moverZombie(Player p, boolean izq) {
        Location loc = petsZombie.get(p).getLocation().clone();
        if (izq) {
            if (loc.getX() < firstB) {
                loc.setX(loc.getX() + antiloverZombieStep);
            }
        } else {
            if (loc.getX() > secondB) {
                loc.setX(loc.getX() - antiloverZombieStep);
            }
        }
        petsZombie.get(p).teleport(loc);
        Extra.sonido(p, NOTE_STICKS);
    }

    public void ponerZombie() {
        Location loc = spawnLovers.clone();
        loc.setX(Extra.rand((int) firstB, (int) secondB));
        Zombie zombie = version.generar(loc);
        if (zombie.getPassenger() != null){
            Bukkit.broadcastMessage("REMOVI PATO");
            zombie.getPassenger().remove();
        }
        version.ponerMano(zombie, new ItemStack(38, 1));
        zombies.add(new AntiloverZombie(zombie, loc, spawnZombie.getBlockZ() - 2));
    }

    public void moverZombies() {
        List<AntiloverZombie> remover = new ArrayList<>();
        for (AntiloverZombie z : zombies) {
            if (!z.mover()) {
                remover.add(z);
            }
        }
        for (AntiloverZombie z : remover) {
            zombies.remove(z);
        }
    }

    public void caido(Player p) {
        msg(antiloverLose.replaceAll("<player>", p.getName()).replaceAll("<petname>", pets.get(p).getName()));
        song(VILLAGER_YES);
        if (ganadores.contains(p)) {
            ganadores.remove(p);
            if ((antiloverRankedJugando.containsKey(p) || antiloverJugando.containsKey(p)) && ganadores.size() == 1) {
                acabar(ganadores.get(0));
            } else if (ganadores.size() < 1) {
                acabar(p);
            } else {
                if (petsZombie.containsKey(p)) {
                    petsZombie.get(p).remove();
                    petsZombie.remove(p);
                    antiloverPlayers.remove(p);
                }
            }
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

    public void regenerar() {
        preTime = 5;
        pregame = true;
        players = new ArrayList<>();
//        for (AntiloverZombie z : zombies) {
//            z.matar();
//        }
        ganadores.clear();
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
}
