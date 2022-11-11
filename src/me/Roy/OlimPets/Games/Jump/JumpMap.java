package me.Roy.OlimPets.Games.Jump;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.jumpPlayers;
import static me.Roy.OlimPets.Extra.pets;
import static me.Roy.OlimPets.Extra.petsZombie;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpJugando;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpLibres;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpPartyJugando;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpStarting;
import static me.Roy.OlimPets.Games.Jump.JumpControlRanked.jumpRankedJugando;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.SPLASH2;
import static me.Roy.OlimPets.Lang.jumpHow;
import static me.Roy.OlimPets.Lang.jumpLose;
import static me.Roy.OlimPets.Lang.jumpWinner;
import static me.Roy.OlimPets.Lang.jumpZombieSpawnDistance;
import static me.Roy.OlimPets.Lang.playerExit;
import static me.Roy.OlimPets.Lang.playerRankedEloFinish;
import static me.Roy.OlimPets.Lang.spawnLobby;
import me.Roy.OlimPets.LangItemsUpdater;
import me.Roy.OlimPets.Objetos.Cuerda;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JumpMap {

    Location player, zombie;
    List<Player> players = new ArrayList<>();
    List<Player> ganadores = new ArrayList<>();
    List<Cuerda> cuerdas = new ArrayList<>();
    List<Player> caidos = new ArrayList<>();
    int id;
    int preTime = 5;
    double finalX = 0;
    boolean pregame = false, preSpawn = false, acabada = false;

    public JumpMap(Location l1, Location l2, int i) {
        id = i;
        player = l1;
        zombie = l2;
    }

    public List<Player> getPlayers() {
        return players;
    }
    
    public List<Cuerda> getCuerdas() {
        return cuerdas;
    }
    
    public List<Player> getCaidos() {
        return caidos;
    }

    public void setPlayers(List<Player> pl) {
        players = pl;
    }

    public void setFinalX(int i) {
        finalX = (i * jumpZombieSpawnDistance) + zombie.getX() + 2;
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
    
    public boolean isAcabada() {
        return acabada;
    }

    public Integer getPretime() {
        if (preTime == 4 && !preSpawn) {
            int i = 0;
            for (Player p : players) {
                pets.get(p).summon(false, 1, getSpawnZombie(i));
                jumpPlayers.add(p);
                i++;
            }
            preSpawn = true;
        }
        return preTime;
    }

    int space = 0;

    public void ponerCuerda() {
        Random r = new Random();
        int n = r.nextInt(2);
        if (n == 1) {
            if (space <= 0) {
                Location loc = zombie.clone();
                loc.setX(loc.getX() - 6);
                cuerdas.add(new Cuerda(loc, finalX));
                song(SPLASH2);
                space = 1;
            } else {
                space--;
            }
        }
    }
    
    public void moverCuerdas(){
        List<Cuerda> removeJump = new ArrayList<>();
        caidos.clear();
        for (Cuerda c : cuerdas) {
            if (c.moverCuerda()) {
                c.removerCuerda();
                removeJump.add(c);
            }
        }
        for (Cuerda c : removeJump) {
            cuerdas.remove(c);
        }
        for (Player p: caidos){
            caido(p);
        }
    }

    public Location getSpawnZombie(int i) {
        Location loc = zombie.clone();
        double r = i * jumpZombieSpawnDistance;
        loc.setX(loc.getX() + r);
        return loc;
    }

    public Location getSpawnPlayer(int i) {
        Location loc = player.clone();
        double r = i * jumpZombieSpawnDistance;
        loc.setX(loc.getX() + r);
        return loc;
    }

    public void teleportarSpawn() {
        int i = 0;
        for (Player p : players) {
            p.teleport(getSpawnPlayer(i));
            i++;
        }
    }

    public void iniciar() {
        pregame = true;
        acabada = false;
        //teleportarSpawn();
        int i = 0;
        for (Player p : players) {
            ganadores.add(p);
            p.setGameMode(GameMode.SURVIVAL);
            p.getInventory().clear();
            for (String s : jumpHow) {
                p.sendMessage(s);
            }
            if (pets.containsKey(p)) {
                if (pets.get(p).getSummon()) {
                    pets.get(p).kill();
                }
            }
            p.teleport(getSpawnPlayer(i));
            //pets.get(p).summon(false, 1, getSpawnZombie(i));
            //jumpPlayers.add(p);
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999, 1));
            i++;
        }
        setFinalX(i);
    }

    public void iniciarJuego() {
        song(SPLASH2);
        pregame = false;
    }

    public void caido(Player p) {
        msg(jumpLose.replaceAll("<player>", p.getName()).replaceAll("<petname>", pets.get(p).getName()));
        if (ganadores.contains(p)) {
            ganadores.remove(p);
            if ((jumpRankedJugando.containsKey(p) || jumpJugando.containsKey(p)) && ganadores.size() == 1) {
                acabar(ganadores.get(0));
            } else if (ganadores.size() < 1) {
                acabar(p);
            } else {
                if (petsZombie.containsKey(p)) {
                    petsZombie.get(p).remove();
                    petsZombie.remove(p);
                    jumpPlayers.remove(p);
                }
            }
        }
    }

    public void acabar(Player w) {
        for (Cuerda c : cuerdas) {
            c.removerCuerda();
        }
        JumpMap map = null;
        Player loser = w;
        String type = "unranked";
        if (jumpJugando.containsKey(w)) {
            map = jumpJugando.get(w);
        } else if (jumpRankedJugando.containsKey(w)) {
            map = jumpRankedJugando.get(w);
            type = "ranked";
        } else if (jumpPartyJugando.containsKey(w)) {
            map = jumpPartyJugando.get(w);
            type = "party";
        }
        for (Player p : players) {
            if (p.hasPotionEffect(PotionEffectType.JUMP)) {
                p.removePotionEffect(PotionEffectType.JUMP);
            }
            p.teleport(spawnLobby);
            if (petsZombie.containsKey(p)) {
                petsZombie.get(p).remove();
            }
            if (jumpPlayers.contains(p)) {
                jumpPlayers.remove(p);
            }
            if (jumpJugando.containsKey(p)) {
                jumpJugando.remove(p);
                Hotbar.ponerMain(p);
            } else if (jumpRankedJugando.containsKey(p)) {
                jumpRankedJugando.remove(p);
                Hotbar.ponerMain(p);
                if (p != w) {
                    loser = p;
                }
            } else if (jumpPartyJugando.containsKey(p)) {
                jumpPartyJugando.remove(p);
                Hotbar.ponerHotbarParty(p);
            }
            if (pets.containsKey(p)) {
                if (pets.get(p).getSummon()) {
                    pets.get(p).summon(true, 0);
                }
            }
        }
        msg(jumpWinner.replaceAll("<petname>", pets.get(w).getName()).replaceAll("<player>", w.getName()));
        //jumpOcupadas.remove(map);
        jumpLibres.add(map);
        if (jumpStarting.contains(map)) {
            jumpStarting.remove(map);
        }
        mostrarPlayers();
        regenerar();
        if (type.equals("ranked")) {
            LangItemsUpdater.updateLoreJumpRankedMenu();
            int difelo = Extra.eloM(pets.get(w).getElo("jump") - pets.get(loser).getElo("jump"));
            pets.get(w).addWins("jump", 1);
            pets.get(w).addPlayed("jump", 1);
            pets.get(loser).addPlayed("jump", 1);
            pets.get(w).addElo("jump", difelo);
            pets.get(loser).removeElo("jump", difelo);
            if (w.isOnline()) {
                w.sendMessage(playerRankedEloFinish.replaceAll("<player1>", w.getName()).replaceAll("<player2>", loser.getName()).replaceAll("<elo>", "" + difelo).replaceAll("<kit>", "Jumper").replaceAll("<elo1>", "" + pets.get(w).getElo("jump")).replaceAll("<elo2>", "" + pets.get(loser).getElo("jump")));
            }
            if (loser.isOnline()) {
                loser.sendMessage(playerRankedEloFinish.replaceAll("<player1>", w.getName()).replaceAll("<player2>", loser.getName()).replaceAll("<elo>", "" + difelo).replaceAll("<kit>", "Jumper").replaceAll("<elo1>", "" + pets.get(w).getElo("jump")).replaceAll("<elo2>", "" + pets.get(loser).getElo("jump")));
            }
        } else if (type.equals("unranked")) {
            LangItemsUpdater.updateLoreJumpUnrankedMenu();
        } else if (type.equals("party")) {
            LangItemsUpdater.updateLoreJumpPartyMenu();
        }
        acabada = true;
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
        finalX = 0;
        preTime = 5;
        pregame = true;
        preSpawn = false;
        players.clear();
        ganadores.clear();
        cuerdas.clear();
    }

    public void exit(Player o) {
        if (jumpRankedJugando.containsKey(o)) {
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
